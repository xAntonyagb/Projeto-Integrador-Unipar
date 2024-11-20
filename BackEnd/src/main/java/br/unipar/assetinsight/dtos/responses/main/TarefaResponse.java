package br.unipar.assetinsight.dtos.responses.main;

import br.unipar.assetinsight.enums.PrioridadeEnum;
import br.unipar.assetinsight.enums.StatusTarefaEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO de {@link br.unipar.assetinsight.entities.TarefaEntity}
 */
public record TarefaResponse(
        @Schema(description = "Id da tarefa.", example = "1")
        long id,

        @Schema(description = "Título da tarefa.", example = "Título da tarefa exemplo.")
        String titulo,

        @Schema(description = "Descrição da tarefa.", example = "Descrição da tarefa exemplo.")
        String descricao,

        @Schema(description = "Data de previsão para conclusão da tarefa.", example = "2021-10-01T00:00:00Z")
        Timestamp previsao,

        @Schema(description = "Ambiente da tarefa.")
        AmbienteResponse ambiente,

        @Schema(description = "Categoria da tarefa.")
        CategoriaResponse categoria,

        @Schema(description = "Prioridade da tarefa.")
        PrioridadeEnum prioridade,

        @Schema(description = "Status da tarefa.")
        StatusTarefaEnum status,

        @Schema(description = "Se a tarefa está arquivada.")
        boolean arquivado,

        @Schema(description = "Ultima alteração feita nesse registro.", example = "2021-10-01T00:00:00Z")
        Timestamp lastChange,

        @Schema(description = "Usuário que realizou a última alteração.")
        UsuarioResponse lastChangedBy

) implements Serializable { }