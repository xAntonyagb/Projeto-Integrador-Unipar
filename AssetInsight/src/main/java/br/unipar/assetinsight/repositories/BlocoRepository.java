package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.BlocoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlocoRepository extends JpaRepository<BlocoEntity, Long> {
}