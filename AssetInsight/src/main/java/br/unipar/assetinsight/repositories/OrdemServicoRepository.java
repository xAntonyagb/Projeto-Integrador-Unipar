package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.OrdemServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrdemServicoRepository extends JpaRepository<OrdemServicoEntity, Long> {

}