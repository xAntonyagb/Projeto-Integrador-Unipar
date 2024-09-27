package br.unipar.assetinsight.infra.security;

import br.unipar.assetinsight.exceptions.SecurityException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.logging.Logger;

@Service
public class TokenService {
    @Autowired
    private RSAKeys rsaKeys;

    @Value("${jwt.expiration.hours}")
    private long expiration;

    @Value("${jwt.refreshToken.expiration.hours}")
    private long refreshTokenExpiration;

    public Instant expirationDate;
    public Instant IssuedAt;
    public static final Logger LOGGER = Logger.getLogger(TokenService.class.getName());


    public String generateToken(UserDetails usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(rsaKeys.getPrivateKey());
            String token = JWT.create()
                    .withIssuer("AssetInsight")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(getExpirationDate(expiration))
                    .withIssuedAt(getIssuedAt())
                    .sign(algorithm);

            return token;
        } catch (Exception e) {
            throw new SecurityException("Erro ao gerar token");
        }
    }

    public String generateRefreshToken(UserDetails usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(rsaKeys.getPrivateKey());
            String token = JWT.create()
                    .withIssuer("AssetInsight")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(getExpirationDate(refreshTokenExpiration))
                    .withIssuedAt(getIssuedAt())
                    .sign(algorithm);

            return token;
        } catch (Exception e) {
            throw new SecurityException("Erro ao gerar RefreshToken");
        }
    }

    public String getSubjectByToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(rsaKeys.getPrivateKey());

            return JWT.require(algorithm)
                    .withIssuer("AssetInsight")
                    .build()
                    .verify(token).getSubject();
        } catch (Exception e) {
            LOGGER.warning("Erro ao retornar o usuario do token: " + e.getMessage());
            return null; //Retorna vazio caso o token seja inválido (não lançar exceção pois será tratada pelo Repository)
        }
    }


    private Instant getExpirationDate(long horas){
        expirationDate = Instant.now().plusSeconds(horas * 3600);
        return expirationDate;
    }

    private Instant getIssuedAt(){
        IssuedAt = Instant.now();
        return IssuedAt;
    }
}
