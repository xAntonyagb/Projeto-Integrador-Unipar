package br.unipar.assetinsight.dtos.requests;

import br.unipar.assetinsight.entities.TarefaEntity;
import br.unipar.assetinsight.enums.PrioridadeEnum;
import br.unipar.assetinsight.enums.StatusTarefaEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO para requests post de {@link TarefaEntity}
 */
public record TarefaRequest(
        Long id,
        @NotNull @NotEmpty @NotBlank String titulo,
        @NotNull @NotEmpty @NotBlank String descricao,
        @NotNull Timestamp previsao,
        @NotNull Long ambiente,
        @NotNull Long categoria,
        @NotNull PrioridadeEnum prioridade,
        @NotNull StatusTarefaEnum status
) implements Serializable { }