package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.ServicoEntity;
import br.unipar.assetinsight.repositories.custom.interfaces.ICustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ServicoRepository extends JpaRepository<ServicoEntity, Long> {
    Optional<List<ServicoEntity>> findAllByAmbienteEntity_Id (long id);

    Optional<List<ServicoEntity>> findAllByCategoriaEntity_Id (long id);

    Page<ServicoEntity> findAll(Pageable pageable);

    long countByCategoriaEntity_Id(long id);

    long countByPatrimonioEntity_Id(long patrimonio);

    long countByAmbienteEntity_BlocoEntity_Id(long blocoId);

    @Query(value = "SELECT COUNT(s.ID_SERVICO) FROM SERVICO s JOIN ORDEM_SERVICO os ON os.ID_ORDEM_SERVICO = s.ID_ORDEM_SERVICO WHERE EXTRACT(YEAR FROM os.DT_ORDEM_SERVICO) = :ano AND os.DS_STATUS = 'PREENCHIDA'", nativeQuery = true)
    long countServicosByYear(@Param("ano") int ano);

}