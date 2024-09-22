package br.unipar.assetinsight.dtos.requests;

import br.unipar.assetinsight.entities.ArquivadoEntity;
import br.unipar.assetinsight.enums.TipoArquivadoEnum;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO para requests post de {@link ArquivadoEntity}
 */
public record ArquivadoRequest(
        long id,
        @NotNull TipoArquivadoEnum tipo,
        @NotNull OrdemServicoRequest ordemServico,
        @NotNull TarefaRequest tarefa
) implements Serializable { }