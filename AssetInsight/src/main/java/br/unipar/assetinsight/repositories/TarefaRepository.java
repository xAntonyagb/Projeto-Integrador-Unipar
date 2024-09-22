package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.TarefaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<TarefaEntity, Long> {
}