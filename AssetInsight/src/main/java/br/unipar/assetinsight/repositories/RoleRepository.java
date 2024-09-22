package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.RolesEntity;
import br.unipar.assetinsight.enums.PermissoesEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RolesEntity, Long> {
    Optional<RolesEntity> findByPermisao(PermissoesEnum permissoesEnum);
}