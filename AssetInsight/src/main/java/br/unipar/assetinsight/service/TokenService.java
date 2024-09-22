package br.unipar.assetinsight.service;

import br.unipar.assetinsight.dtos.requests.LoginRequest;
import br.unipar.assetinsight.dtos.responses.LoginResponse;
import br.unipar.assetinsight.entities.RolesEntity;
import br.unipar.assetinsight.exceptions.UnauthorizedException;
import br.unipar.assetinsight.repositories.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class TokenService {
    private final JwtEncoder jwtEncoder;
    private final UsuarioRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public TokenService(JwtEncoder jwtEncoder,
                        UsuarioRepository userRepository,
                        BCryptPasswordEncoder passwordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest loginRequest)  throws UnauthorizedException {

        var user = userRepository.findByUsernameIgnoreCase(loginRequest.username());

        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequest, passwordEncoder)) {
            throw new UnauthorizedException("Usuario ou senha invalidos.");
        }

        var now = Instant.now();
        var expiresIn = 30000000000L;

        var scopes = user.get().getListRoles()
                .stream()
                .map(role -> role.getPermisao().getDescricao())
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("assetinsight")
                .subject(user.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new LoginResponse(jwtValue, expiresIn, now );
    }
}
