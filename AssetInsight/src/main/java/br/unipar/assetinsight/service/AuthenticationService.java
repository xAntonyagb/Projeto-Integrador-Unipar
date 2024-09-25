package br.unipar.assetinsight.service;

import br.unipar.assetinsight.dtos.requests.CadastroRequest;
import br.unipar.assetinsight.dtos.requests.LoginRequest;
import br.unipar.assetinsight.dtos.responses.CadastroResponse;
import br.unipar.assetinsight.dtos.responses.LoginResponse;
import br.unipar.assetinsight.entities.RolesEntity;
import br.unipar.assetinsight.entities.UsuarioEntity;
import br.unipar.assetinsight.exceptions.ValidationException;
import br.unipar.assetinsight.infra.security.TokenService;
import br.unipar.assetinsight.mappers.AuthenticationMapper;
import br.unipar.assetinsight.mappers.RoleMapper;
import br.unipar.assetinsight.repositories.UsuarioRepository;
import br.unipar.assetinsight.utils.DataUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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

        var token = tokenService.generateToken(usuarioAutenticado); //Gera token a partir do usuario que já foi autenticado

        LoginResponse loginResponse = new LoginResponse(
                token,
                tokenService.expirationDate,
                tokenService.IssuedAt,
                roleMapper.toPermissoesEnumList(usuarioAutenticado.getListRoles())
        );

        return loginResponse;
    }

    public CadastroResponse cadastrarUsuario(CadastroRequest request) throws ValidationException {
        if (usuarioRepository.findByUsernameIgnoreCase(request.username()).isPresent()) {
            throw new ValidationException("Usuário já cadastrado.");
        }

        String senhaCriptografada = new BCryptPasswordEncoder().encode(request.password());

        List<RolesEntity> roles = roleMapper.toRolesEntityList(request.permissoes());
        UsuarioEntity usuario = new UsuarioEntity(request.username(), senhaCriptografada, roles);

        UsuarioEntity usuarioEntity = usuarioRepository.save(usuario);
        return AuthenticationMapper.INSTANCE.toCadastroResponse(usuarioEntity);
    }
}
