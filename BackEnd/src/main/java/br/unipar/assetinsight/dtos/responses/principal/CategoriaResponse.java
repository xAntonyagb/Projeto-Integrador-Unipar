package br.unipar.assetinsight.dtos.responses.principal;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO de {@link br.unipar.assetinsight.entities.CategoriaEntity}
 */
public record CategoriaResponse(
        @Schema(description = "Id da categoria.", example = "1")
        long id,

        @Schema(description = "Nome da categoria.", example = "Categoria Exemplo")
        String descricao,

        @Schema(description = "Quantidade de tarefas que essa categoria está vinculada", example = "1")
        long qtdTarefas,

        @Schema(description = "Quantidade total de categorias no sistema", example = "10")
        long qtdTotalTarefas,

        @Schema(description = "Quantidade de serviços que essa categoria está vinculada", example = "1")
        long qtdServicos,

        @Schema(description = "Quantidade total de serviços no sistema", example = "10")
        long qtdTotalServicos,

        @Schema(description = "Ultima alteração feita nesse registro.", example = "2021-10-01T00:00:00Z")
        Timestamp lastChange,

        @Schema(description = "Usuário que realizou a última alteração.")
        UsuarioResponse lastChangedBy
) implements Serializable { }