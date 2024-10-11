package br.unipar.assetinsight.dtos.requests;

import br.unipar.assetinsight.entities.ArquivadoEntity;
import br.unipar.assetinsight.enums.TipoArquivadoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO para requests post de {@link ArquivadoEntity}
 */
public record ArquivadoRequest(
        @Schema(description = "Id do arquivada", example = "1", required = false)
        Long id,

        @Schema(description = "Tipo do arquivada", example = "TAREFA", required = true)
        @NotNull TipoArquivadoEnum tipo,

        @Schema(description = "Id da ordem de serviço", example = "1", required = false)
        Long ordemServico,

        @Schema(description = "Id da tarefa", example = "1", required = false)
        Long tarefa
) implements Serializable { }