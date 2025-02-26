package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.AmbienteEntity;
import br.unipar.assetinsight.repositories.custom.interfaces.ICustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AmbienteRepository extends JpaRepository<AmbienteEntity, Long>, ICustomRepository<AmbienteEntity> {
    Optional<List<AmbienteEntity>> findByBlocoEntityId(long id);

    Page<AmbienteEntity> findAll(Pageable pageable);

    long countByBlocoEntityId(long id);
}