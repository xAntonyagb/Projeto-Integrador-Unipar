package br.unipar.assetinsight.dtos.responses;

import br.unipar.assetinsight.enums.NotificacaoEnum;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link br.unipar.assetinsight.entities.NotificacaoEntity}
 */
public record NotificacaoResponse(
        long id,
        NotificacaoEnum tipo,
        String titulo,
        String descricao,
        Timestamp dtEnvio
) implements Serializable { }