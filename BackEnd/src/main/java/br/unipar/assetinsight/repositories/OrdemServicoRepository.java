package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.OrdemServicoEntity;
import br.unipar.assetinsight.entities.ServicoEntity;
import br.unipar.assetinsight.repositories.custom.interfaces.ICustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Month;
import java.util.List;

public interface OrdemServicoRepository extends JpaRepository<OrdemServicoEntity, Long>, ICustomRepository<OrdemServicoEntity> {
    Page<OrdemServicoEntity> findAll(Pageable pageable);

    @Query("SELECT o FROM OrdemServicoEntity o WHERE MONTH(o.data) = :month")
    List<OrdemServicoEntity> findAllByMonth(Month month);

    Page<ServicoEntity> findAllById(long id, Pageable pageable);
}