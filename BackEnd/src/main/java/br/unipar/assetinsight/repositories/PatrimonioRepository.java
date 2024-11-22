package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.BlocoEntity;
import br.unipar.assetinsight.entities.PatrimonioEntity;
import br.unipar.assetinsight.repositories.custom.interfaces.ICustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PatrimonioRepository extends JpaRepository<PatrimonioEntity, Long>, ICustomRepository<PatrimonioEntity> {
    Page<PatrimonioEntity> findAll(Pageable pageable);

    Optional<List<PatrimonioEntity>> findAllByAmbienteEntity_Id(long ambienteOrigemId);

    @Query(value = "SELECT p.* FROM SERVICO s JOIN ORDEM_SERVICO os ON os.ID_ORDEM_SERVICO = s.ID_ORDEM_SERVICO JOIN PATRIMONIO p ON p.ID_PATRIMONIO = s.ID_PATRIMONIO WHERE EXTRACT(MONTH FROM os.DT_ORDEM_SERVICO) = :mes AND os.DS_STATUS = 'PREENCHIDA' GROUP BY p.ID_PATRIMONIO ORDER BY COUNT(s.ID_SERVICO) DESC LIMIT 1", nativeQuery = true)
    Optional<PatrimonioEntity> findTopPatrimonioByMonth(@Param("mes") int mes);

    @Query(value = "SELECT p.* FROM SERVICO s JOIN ORDEM_SERVICO os ON os.ID_ORDEM_SERVICO = s.ID_ORDEM_SERVICO JOIN PATRIMONIO p ON p.ID_PATRIMONIO = s.ID_PATRIMONIO WHERE EXTRACT(YEAR FROM os.DT_ORDEM_SERVICO) = :ano AND os.DS_STATUS = 'PREENCHIDA' GROUP BY p.ID_PATRIMONIO ORDER BY COUNT(s.ID_SERVICO) DESC LIMIT 1", nativeQuery = true)
    Optional<PatrimonioEntity> findTopPatrimonioByYear(@Param("ano") int ano);
}