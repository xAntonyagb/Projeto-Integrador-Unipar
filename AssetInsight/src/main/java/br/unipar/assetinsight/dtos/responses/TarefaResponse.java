package br.unipar.assetinsight.dtos.responses;

import br.unipar.assetinsight.enums.PrioridadeEnum;
import br.unipar.assetinsight.enums.StatusTarefaEnum;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link br.unipar.assetinsight.entities.TarefaEntity}
 */
public record TarefaResponse(
        long id,
        String titulo,
        String descricao,
        Timestamp previsao,
        AmbienteResponse ambiente,
        Timestamp lastChange,
        CategoriaResponse categoria,
        PrioridadeEnum prioridade,
        StatusTarefaEnum status,
        UsuarioResponse lastChangedBy,
        boolean arquivado
) implements Serializable { }