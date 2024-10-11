package br.unipar.assetinsight.repositories;

import br.unipar.assetinsight.entities.NotificacaoEntity;
import br.unipar.assetinsight.entities.NotificacaoUsuarioEntity;
import br.unipar.assetinsight.entities.keys.NotificacaoUsuarioKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


public interface NotificacaoUsuarioRepository extends JpaRepository<NotificacaoUsuarioEntity, NotificacaoUsuarioKey> {

    @Query("SELECT nu FROM NotificacaoUsuarioEntity nu WHERE nu.id.idUsuario = :userId AND nu.isLida = false")
    List<NotificacaoUsuarioEntity> findAllNaoLidaByUser(UUID userId);

    @Transactional
    @Modifying
    @Query("UPDATE NotificacaoUsuarioEntity nu SET nu.isLida = true WHERE nu.id.idNotificacao = :notificationId AND nu.id.idUsuario = :userId")
    void markAsLida(UUID userId, long notificationId);

    @Transactional
    @Modifying
    @Query("UPDATE NotificacaoUsuarioEntity nu SET nu.isLida = true WHERE nu.id.idUsuario = :userId")
    void markAllAsLida(UUID userId);

    @Query("SELECT nu.notificacao FROM NotificacaoUsuarioEntity nu WHERE nu.id.idUsuario = :userId")
    Page<NotificacaoEntity> findAllByUsuario(UUID userId, Pageable pageable);

    @Transactional
    @Modifying
    @Query("DELETE FROM NotificacaoUsuarioEntity nu WHERE nu.id.idNotificacao = :notificationId")
    void deleteByIdNotificacao(long notificationId);

}