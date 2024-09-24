package br.unipar.assetinsight.infra.security;

import br.unipar.assetinsight.entities.UsuarioEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Autowired
    private RSAKeys rsaKeys;

    @Value("${jwt.expiration.hours}")
    private long expiration;


    public String generateToken(UsuarioEntity usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(rsaKeys.getPrivateKey());
            String token = JWT.create()
                    .withIssuer("AssetInsight")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(getExpirationDate())
                    .withIssuedAt(Instant.now())
                    .sign(algorithm);

            return token;
        } catch (Exception e) {
            throw new TokenException("Erro ao gerar token");
        }
    }


    public DecodedJWT decodeToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(rsaKeys.getPrivateKey());

        return JWT.require(algorithm)
                .withIssuer("AssetInsight")
                .build()
                .verify(token);
    }


    public String getSubjectByToken(String token){
        try {
            return decodeToken(token).getSubject();
        } catch (Exception e) {
            return null; //Retorna vazio caso o token seja inválido (não lançar exceção pois será tratada pelo Repository)
        }
    }


    private Instant getExpirationDate(){
        return LocalDateTime.now().plusHours(expiration).toInstant(ZoneOffset.of("-03:00"));
    }
}
