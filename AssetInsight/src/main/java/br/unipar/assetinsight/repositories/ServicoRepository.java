package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.ServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<ServicoEntity, Long> {
}