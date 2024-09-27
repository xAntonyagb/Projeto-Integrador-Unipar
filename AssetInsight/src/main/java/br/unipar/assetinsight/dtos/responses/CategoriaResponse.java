package br.unipar.assetinsight.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link br.unipar.assetinsight.entities.CategoriaEntity}
 */
public record CategoriaResponse(
        @Schema(description = "Id da categoria.", example = "1")
        long id,

        @Schema(description = "Nome da categoria.", example = "Categoria Exemplo")
        String descricao,

        @Schema(description = "Ultima alteração feita nesse registro.", example = "2021-10-01T00:00:00Z")
        Timestamp lastChange,

        @Schema(description = "Usuário que realizou a última alteração.")
        UsuarioResponse lastChangedBy
) implements Serializable { }