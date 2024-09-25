package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.AmbienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AmbienteRepository extends JpaRepository<AmbienteEntity, Long> {
    Optional<List<AmbienteEntity>> findByBlocoEntityId(long id);
}