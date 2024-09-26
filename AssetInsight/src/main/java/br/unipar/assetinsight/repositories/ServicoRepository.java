package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.ServicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServicoRepository extends JpaRepository<ServicoEntity, Long> {
    Optional<List<ServicoEntity>> findAllByAmbienteEntity_Id (long id);

    Optional<List<ServicoEntity>> findAllByCategoriaEntity_Id (long id);

    Page<ServicoEntity> findAll(Pageable pageable);

    Page<ServicoEntity> findAllByIdOrdemServico(long id, Pageable pageable);
}