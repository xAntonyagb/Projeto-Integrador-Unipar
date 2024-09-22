package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.RolesEntity;
import br.unipar.assetinsight.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {

    Optional<List<UsuarioEntity>> findByListRolesContaining(RolesEntity rolesEntity);

    Optional<UsuarioEntity> findByUsernameIgnoreCase(String username);
}