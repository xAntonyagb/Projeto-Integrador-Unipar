package br.unipar.assetinsight.dtos.requests;

import br.unipar.assetinsight.entities.TarefaEntity;
import br.unipar.assetinsight.enums.PrioridadeEnum;
import br.unipar.assetinsight.enums.StatusTarefaEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO para requests post de {@link TarefaEntity}
 */
public record TarefaRequest(
        @Schema(description = "Id da tarefa", example = "1", required = false)
        Long id,

        @Schema(description = "Título / Identificador da tarefa", example = "Tarefa1", required = true)
        @NotNull @NotEmpty @NotBlank String titulo,

        @Schema(description = "Descrição da tarefa", example = "Descrição da tarefa", required = true)
        @NotNull @NotEmpty @NotBlank String descricao,

        @Schema(description = "Id do ambiente da tarefa", example = "1", required = true)
        @NotNull Long ambiente,

        @Schema(description = "Id da categoria da tarefa", example = "1", required = true)
        @NotNull Long categoria,

        @Schema(description = "Data de previsão de conclusão da tarefa", example = "2021-12-31 23:59:59", required = true)
        @NotNull Timestamp previsao,

        @Schema(description = "Prioridade da tarefa", example = "ALTA", required = true)
        @NotNull PrioridadeEnum prioridade,

        @Schema(description = "Status da tarefa", example = "PENDENTE", required = true)
        @NotNull StatusTarefaEnum status
) implements Serializable { }