package br.unipar.assetinsight.dtos.responses.main;

import br.unipar.assetinsight.enums.NotificacaoEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO de {@link br.unipar.assetinsight.entities.NotificacaoEntity}
 */
public record NotificacaoResponse(
        @Schema(description = "Id da notificação.", example = "1")
        long id,

        @Schema(description = "Tipo da notificação.")
        NotificacaoEnum tipo,

        @Schema(description = "Descrição da notificação.", example = "Notificação Exemplo")
        String titulo,

        @Schema(description = "Descrição da notificação.", example = "Descrição da notificação exemplo.")
        String descricao,

        @Schema(description = "Data de envio da notificação.", example = "2021-10-01T00:00:00Z")
        Timestamp dtEnvio
) implements Serializable { }