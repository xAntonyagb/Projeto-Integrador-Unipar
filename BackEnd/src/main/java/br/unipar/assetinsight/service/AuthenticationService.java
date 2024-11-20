package br.unipar.assetinsight.service;

import br.unipar.assetinsight.dtos.requests.CadastroRequest;
import br.unipar.assetinsight.dtos.requests.LoginRequest;
import br.unipar.assetinsight.dtos.responses.main.LoginResponse;
import br.unipar.assetinsight.entities.RolesEntity;
import br.unipar.assetinsight.entities.UsuarioEntity;
import br.unipar.assetinsight.exceptions.ValidationException;
import br.unipar.assetinsight.infra.security.TokenService;
import br.unipar.assetinsight.mappers.RoleMapper;
import br.unipar.assetinsight.repositories.UsuarioRepository;
import br.unipar.assetinsight.utils.DataUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private UsuarioRepository usuarioRepository;
    private RoleMapper roleMapper;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;


    public LoginResponse doLogin(LoginRequest request) {
        var usuarioSenha = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        var auth = authenticationManager.authenticate(usuarioSenha); // 401 se der ruim

        var usuarioAutenticado = (UsuarioEntity) auth.getPrincipal();
        usuarioAutenticado.setDtLogin(DataUtils.getNow());
        usuarioRepository.save(usuarioAutenticado); //Atualizar data de login do usuario

        var userToken = (UserDetails) usuarioAutenticado;


        var token = tokenService.generateToken(userToken); //Gera token a partir do usuario que já foi autenticado
        Instant expirationDate = tokenService.expirationDate;
        Instant IssuedAt = tokenService.IssuedAt;

        var refreshToken = tokenService.generateRefreshToken(userToken);

        LoginResponse loginResponse = new LoginResponse(
                token,
                expirationDate,
                IssuedAt,
                roleMapper.toPermissoesEnumList(usuarioAutenticado.getListRoles()),
                refreshToken
        );

        return loginResponse;
    }

    public LoginResponse doLogin(String refreshToken) {
        String username = tokenService.getSubjectByToken(refreshToken);

        if (username == null) {
            throw new SecurityException("O token informado é inválido. Por favor, realize o login novamente em auth/login.");
        }

        UsuarioEntity usuario = usuarioRepository.findEntityByUsername(username)
                .orElseThrow(() -> new SecurityException("Usuário não encontrado."));

        usuario.setDtLogin(DataUtils.getNow());
        usuarioRepository.save(usuario);

        String token = tokenService.generateToken(usuario);
        refreshToken = tokenService.generateRefreshToken(usuario);

        return new LoginResponse(
                token,
                tokenService.expirationDate,
                tokenService.IssuedAt,
                roleMapper.toPermissoesEnumList(usuario.getListRoles()),
                refreshToken
        );
    }

    public UsuarioEntity cadastrarUsuario(CadastroRequest request) throws ValidationException {
        if (usuarioRepository.findByUsernameIgnoreCase(request.username()).isPresent()) {
            throw new ValidationException("Usuário já cadastrado.");
        }

        String senhaCriptografada = new BCryptPasswordEncoder().encode(request.password());

        List<RolesEntity> roles = roleMapper.toRolesEntityList(request.permissoes());
        UsuarioEntity usuario = new UsuarioEntity(request.username(), senhaCriptografada, roles);

        return usuarioRepository.save(usuario);
    }
}
