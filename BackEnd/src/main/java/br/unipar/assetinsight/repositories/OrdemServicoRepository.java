package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.OrdemServicoEntity;
import br.unipar.assetinsight.entities.ServicoEntity;
import br.unipar.assetinsight.enums.StatusOrdemServicoEnum;
import br.unipar.assetinsight.repositories.custom.interfaces.ICustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Month;
import java.util.List;
import java.util.Map;

public interface OrdemServicoRepository extends JpaRepository<OrdemServicoEntity, Long>, ICustomRepository<OrdemServicoEntity> {
    Page<OrdemServicoEntity> findAll(Pageable pageable);

    @Query("SELECT o FROM OrdemServicoEntity o WHERE MONTH(o.data) = :month")
    List<OrdemServicoEntity> findAllByMonth(Month month);

    Page<ServicoEntity> findAllById(long id, Pageable pageable);

    @Query("SELECT COUNT(o) FROM OrdemServicoEntity o WHERE o.status = :status")
    long countByStatus(@Param("status") StatusOrdemServicoEnum status);

    @Query("SELECT SUM(o.valorTotal) FROM OrdemServicoEntity o WHERE EXTRACT(MONTH FROM o.data) = :month")
    Double sumValorGastoByMonth(@Param("month") int month);

    @Query("SELECT DISTINCT EXTRACT(MONTH FROM o.data) FROM OrdemServicoEntity o")
    List<Integer> findAllMeses();

    @Query(value = "SELECT a.DS_DESCRICAO AS param1, SUM(s.VL_TOTAL) AS totalValor FROM ORDEM_SERVICO o JOIN SERVICO s ON o.ID_ORDEM_SERVICO = s.ID_ORDEM_SERVICO JOIN AMBIENTE a ON s.ID_AMBIENTE = a.ID_AMBIENTE WHERE o.DS_STATUS = 'PREENCHIDA' AND EXTRACT(MONTH FROM o.DT_ORDEM_SERVICO) = :mes GROUP BY a.DS_DESCRICAO", nativeQuery = true)
    List<Map<Object, Object>> findSumValorTotalByAmbienteAndMonth(@Param("mes") int mes);

    @Query(value = "SELECT c.DS_DESCRICAO AS param1, SUM(s.VL_TOTAL) AS totalValor FROM ORDEM_SERVICO o JOIN SERVICO s ON o.ID_ORDEM_SERVICO = s.ID_ORDEM_SERVICO JOIN CATEGORIA c ON s.ID_CATEGORIA = c.ID_CATEGORIA WHERE o.DS_STATUS = 'PREENCHIDA' AND EXTRACT(MONTH FROM o.DT_ORDEM_SERVICO) = :mes GROUP BY c.DS_DESCRICAO", nativeQuery = true)
    List<Map<Object, Object>> findSumValorTotalByCategoriaAndMonth(@Param("mes") int mes);

    @Query(value = "SELECT p.ID_PATRIMONIO AS param1, SUM(s.VL_TOTAL) AS totalValor FROM ORDEM_SERVICO o JOIN SERVICO s ON o.ID_ORDEM_SERVICO = s.ID_ORDEM_SERVICO JOIN PATRIMONIO p ON s.ID_PATRIMONIO = p.ID_PATRIMONIO WHERE o.DS_STATUS = 'PREENCHIDA' AND EXTRACT(MONTH FROM o.DT_ORDEM_SERVICO) = :mes GROUP BY p.ID_PATRIMONIO", nativeQuery = true)
    List<Map<Object, Object>> findSumValorTotalByPatrimonioAndMonth(@Param("mes") int mes);

    @Query(value = "SELECT a.DS_DESCRICAO AS param1, SUM(s.VL_TOTAL) AS totalValor FROM ORDEM_SERVICO o JOIN SERVICO s ON o.ID_ORDEM_SERVICO = s.ID_ORDEM_SERVICO JOIN AMBIENTE a ON s.ID_AMBIENTE = a.ID_AMBIENTE WHERE o.DS_STATUS = 'PREENCHIDA' AND EXTRACT(YEAR FROM o.DT_ORDEM_SERVICO) = :ano GROUP BY a.DS_DESCRICAO", nativeQuery = true)
    List<Map<Object, Object>> findSumValorTotalByAmbienteAndYear(@Param("ano") int ano);

    @Query(value = "SELECT c.DS_DESCRICAO AS param1, SUM(s.VL_TOTAL) AS totalValor FROM ORDEM_SERVICO o JOIN SERVICO s ON o.ID_ORDEM_SERVICO = s.ID_ORDEM_SERVICO JOIN CATEGORIA c ON s.ID_CATEGORIA = c.ID_CATEGORIA WHERE o.DS_STATUS = 'PREENCHIDA' AND EXTRACT(YEAR FROM o.DT_ORDEM_SERVICO) = :ano GROUP BY c.DS_DESCRICAO", nativeQuery = true)
    List<Map<Object, Object>> findSumValorTotalByCategoriaAndYear(@Param("ano") int ano);

    @Query(value = "SELECT p.ID_PATRIMONIO AS param1, SUM(s.VL_TOTAL) AS totalValor FROM ORDEM_SERVICO o JOIN SERVICO s ON o.ID_ORDEM_SERVICO = s.ID_ORDEM_SERVICO JOIN PATRIMONIO p ON s.ID_PATRIMONIO = p.ID_PATRIMONIO WHERE o.DS_STATUS = 'PREENCHIDA' AND EXTRACT(YEAR FROM o.DT_ORDEM_SERVICO) = :ano GROUP BY p.ID_PATRIMONIO", nativeQuery = true)
    List<Map<Object, Object>> findSumValorTotalByPatrimonioAndYear(@Param("ano") int ano);

    @Query(value = "SELECT TO_CHAR(o.DT_ORDEM_SERVICO, 'Month') AS param1, SUM(o.VL_TOTAL) AS totalValor FROM ORDEM_SERVICO o WHERE EXTRACT(YEAR FROM o.DT_ORDEM_SERVICO) = :ano AND o.DS_STATUS = 'PREENCHIDA' GROUP BY TO_CHAR(o.DT_ORDEM_SERVICO, 'Month')", nativeQuery = true)
    List<Map<Object, Object>> findSumValorTotalByMonthAndYear(@Param("ano") int ano);

    @Query(value = "SELECT SUM(o.VL_TOTAL) AS totalGasto FROM ORDEM_SERVICO o WHERE EXTRACT(YEAR FROM o.DT_ORDEM_SERVICO) = :ano AND o.DS_STATUS = 'PREENCHIDA'", nativeQuery = true)
    Double findTotalGastoByYear(@Param("ano") int ano);
}