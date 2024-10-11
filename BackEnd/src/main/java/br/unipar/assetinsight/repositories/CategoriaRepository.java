package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.CategoriaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
    Page<CategoriaEntity> findAll(Pageable pageable);

    boolean existsByDescricaoIgnoreCase(String descricao);
}