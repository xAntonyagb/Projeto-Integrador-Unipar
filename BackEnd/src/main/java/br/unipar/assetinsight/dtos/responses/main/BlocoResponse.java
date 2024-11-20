package br.unipar.assetinsight.dtos.responses.main;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO de {@link br.unipar.assetinsight.entities.BlocoEntity}
 */
public record BlocoResponse(
        @Schema(description = "Id do bloco.", example = "1")
        Long id,

        @Schema(description = "Nome do bloco.", example = "Bloco A")
        String descricao,

        @Schema(description = "Quantidade de ambientes no bloco.", example = "10")
        long qtdAmbientes,

        @Schema(description = "Ultima alteração feita nesse registro.", example = "2021-10-01T00:00:00Z")
        Timestamp lastChange,

        @Schema(description = "Usuário que realizou a última alteração.")
        UsuarioResponse lastChangedBy
) implements Serializable { }