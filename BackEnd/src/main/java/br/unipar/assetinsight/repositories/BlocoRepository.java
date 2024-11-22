package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.BlocoEntity;
import br.unipar.assetinsight.repositories.custom.interfaces.ICustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BlocoRepository extends JpaRepository<BlocoEntity, Long>, ICustomRepository<BlocoEntity> {
    Page<BlocoEntity> findAll(Pageable pageable);

    @Query(value = "SELECT b.* FROM SERVICO s JOIN ORDEM_SERVICO os ON os.ID_ORDEM_SERVICO = s.ID_ORDEM_SERVICO JOIN AMBIENTE a ON a.ID_AMBIENTE = s.ID_AMBIENTE JOIN BLOCO b ON b.ID_BLOCO = a.ID_BLOCO WHERE EXTRACT(MONTH FROM os.DT_ORDEM_SERVICO) = :mes AND os.DS_STATUS = 'PREENCHIDA' GROUP BY b.ID_BLOCO ORDER BY COUNT(s.ID_SERVICO) DESC LIMIT 1", nativeQuery = true)
    Optional<BlocoEntity> findTopBlocoByMonth(@Param("mes") int mes);

    @Query(value = "SELECT b.* FROM SERVICO s JOIN ORDEM_SERVICO os ON os.ID_ORDEM_SERVICO = s.ID_ORDEM_SERVICO JOIN AMBIENTE a ON a.ID_AMBIENTE = s.ID_AMBIENTE JOIN BLOCO b ON b.ID_BLOCO = a.ID_BLOCO WHERE EXTRACT(YEAR FROM os.DT_ORDEM_SERVICO) = :ano AND os.DS_STATUS = 'PREENCHIDA' GROUP BY b.ID_BLOCO ORDER BY COUNT(s.ID_SERVICO) DESC LIMIT 1", nativeQuery = true)
    Optional<BlocoEntity> findTopBlocoByYear(@Param("ano") int ano);
}