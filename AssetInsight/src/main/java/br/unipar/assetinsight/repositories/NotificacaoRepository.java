package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.NotificacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoRepository extends JpaRepository<NotificacaoEntity, Long> {
}