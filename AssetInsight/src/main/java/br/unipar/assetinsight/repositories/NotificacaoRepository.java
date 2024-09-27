package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.NotificacaoEntity;
import br.unipar.assetinsight.entities.NotificacaoUsuarioEntity;
import br.unipar.assetinsight.entities.keys.NotificacaoUsuarioKey;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotificacaoRepository extends JpaRepository<NotificacaoEntity, Long> {

}