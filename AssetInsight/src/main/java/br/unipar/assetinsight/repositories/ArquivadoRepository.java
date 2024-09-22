package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.ArquivadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArquivadoRepository extends JpaRepository<ArquivadoEntity, Long> {
}