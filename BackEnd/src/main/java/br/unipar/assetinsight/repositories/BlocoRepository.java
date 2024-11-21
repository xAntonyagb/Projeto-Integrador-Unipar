package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.BlocoEntity;
import br.unipar.assetinsight.repositories.custom.interfaces.ICustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlocoRepository extends JpaRepository<BlocoEntity, Long>, ICustomRepository<BlocoEntity> {
    Page<BlocoEntity> findAll(Pageable pageable);
}