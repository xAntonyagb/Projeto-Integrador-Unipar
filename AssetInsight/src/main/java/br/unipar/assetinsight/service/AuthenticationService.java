package br.unipar.assetinsight.service;

import br.unipar.assetinsight.dtos.requests.CadastroRequest;
import br.unipar.assetinsight.dtos.responses.CadastroResponse;
import br.unipar.assetinsight.entities.RolesEntity;
import br.unipar.assetinsight.entities.UsuarioEntity;
import br.unipar.assetinsight.enums.PermissoesEnum;
import br.unipar.assetinsight.exceptions.SecurityException;
import br.unipar.assetinsight.exceptions.TokenException;
import br.unipar.assetinsight.exceptions.ValidationException;
import br.unipar.assetinsight.mappers.AuthenticationMapper;
import br.unipar.assetinsight.mappers.RoleMapper;
import br.unipar.assetinsight.repositories.RoleRepository;
import br.unipar.assetinsight.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleMapper roleMapper;


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
