package br.unipar.assetinsight.dtos.requests;

import br.unipar.assetinsight.entities.ArquivadoEntity;
import br.unipar.assetinsight.enums.TipoArquivadoEnum;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO para requests post de {@link ArquivadoEntity}
 */
public record ArquivadoRequest(
        Long id,
        @NotNull TipoArquivadoEnum tipo,
        @NotNull Long ordemServico,
        @NotNull Long tarefa
) implements Serializable { }