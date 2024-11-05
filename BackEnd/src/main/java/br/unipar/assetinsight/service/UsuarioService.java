package br.unipar.assetinsight.service;

import br.unipar.assetinsight.entities.UsuarioEntity;
import br.unipar.assetinsight.exceptions.NotFoundException;
import br.unipar.assetinsight.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioEntity getById(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow( () -> new NotFoundException("username", "Usuário não encontrado"));
    }

    public Page<UsuarioEntity> getAll(Pageable pageable) {
        Page<UsuarioEntity> usuarios = usuarioRepository.findAll(pageable);

        if (usuarios.isEmpty()) {
            throw new NotFoundException("username", "Nenhum usuário encontrado");
        }

        return usuarios;
    }

    public void deleteById(UUID id) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("username", "Usuário não encontrado"));
    }
}
