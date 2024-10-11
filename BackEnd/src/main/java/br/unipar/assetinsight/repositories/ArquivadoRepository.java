package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.ArquivadoEntity;
import br.unipar.assetinsight.entities.BlocoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArquivadoRepository extends JpaRepository<ArquivadoEntity, Long> {
    Page<ArquivadoEntity> findAll(Pageable pageable);
}