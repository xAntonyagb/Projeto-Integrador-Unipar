package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.TarefaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface TarefaRepository extends JpaRepository<TarefaEntity, Long> {
    Optional<List<TarefaEntity>> findAllByAmbienteEntity_Id(long id);

    Optional<List<TarefaEntity>> findAllByCategoriaEntity_Id(long id);

    Page<TarefaEntity> findAll(Pageable pageable);

    Optional<List<TarefaEntity>> findAllByDtPrevisaoBefore(Timestamp dataHora);

    long countByCategoriaEntity_Id(long id);
}