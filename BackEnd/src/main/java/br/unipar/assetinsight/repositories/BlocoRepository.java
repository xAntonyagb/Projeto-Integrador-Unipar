package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.BlocoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlocoRepository extends JpaRepository<BlocoEntity, Long> {
    Page<BlocoEntity> findAll(Pageable pageable);
}