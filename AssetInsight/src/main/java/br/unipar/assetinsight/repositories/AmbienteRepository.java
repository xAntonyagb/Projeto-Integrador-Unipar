package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.AmbienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmbienteRepository extends JpaRepository<AmbienteEntity, Long> {
}