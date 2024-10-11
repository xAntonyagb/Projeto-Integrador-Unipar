package br.unipar.assetinsight.entities.keys;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class NotificacaoUsuarioKey implements Serializable {
    private long idNotificacao;
    private UUID idUsuario;
}
