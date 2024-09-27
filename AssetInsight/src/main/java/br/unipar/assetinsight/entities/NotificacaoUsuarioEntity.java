package br.unipar.assetinsight.entities;

import br.unipar.assetinsight.entities.keys.NotificacaoUsuarioKey;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "NOTIFICACAO_USUARIO")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NotificacaoUsuarioEntity {

    @EmbeddedId
    private NotificacaoUsuarioKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idUsuario")
    @JoinColumn(name = "ID_USUARIO")
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idNotificacao")
    @JoinColumn(name = "ID_NOTIFICACAO")
    private NotificacaoEntity notificacao;

    @Column(name = "IS_READ")
    @ColumnDefault("false")
    boolean isLida;

}
