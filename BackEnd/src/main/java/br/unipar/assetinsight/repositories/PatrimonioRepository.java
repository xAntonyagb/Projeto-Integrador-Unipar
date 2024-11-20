package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.BlocoEntity;
import br.unipar.assetinsight.entities.PatrimonioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PatrimonioRepository extends JpaRepository<PatrimonioEntity, Long> {
    Page<PatrimonioEntity> findAll(Pageable pageable);

    long countByAmbienteEntity_Id(long id);

    Optional<List<PatrimonioEntity>> findAllByAmbienteEntity_Id(long ambienteOrigemId);
}