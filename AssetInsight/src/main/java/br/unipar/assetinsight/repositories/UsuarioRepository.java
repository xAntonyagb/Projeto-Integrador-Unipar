package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.RolesEntity;
import br.unipar.assetinsight.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {

    Optional<List<UsuarioEntity>> findByListRolesContaining(RolesEntity rolesEntity);

    Optional<UserDetails> findByUsernameIgnoreCase(String username);
}