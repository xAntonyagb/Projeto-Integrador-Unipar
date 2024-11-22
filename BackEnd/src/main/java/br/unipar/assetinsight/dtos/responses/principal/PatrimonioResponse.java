package br.unipar.assetinsight.dtos.responses.principal;

import br.unipar.assetinsight.dtos.responses.simple.AmbienteSimpleResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO de {@link br.unipar.assetinsight.entities.BlocoEntity}
 */
public record PatrimonioResponse(
        @Schema(description = "Id do patrimonio.", example = "1")
        Long patrimonio,

        @Schema(description = "Descrição do patrimonio.", example = "Patrimonio 1")
        String descricao,

        @Schema(description = "Ambiente do patrimonio.")
        AmbienteSimpleResponse ambiente,

        @Schema(description = "Ultima alteração feita nesse registro.", example = "2021-10-01T00:00:00Z")
        Timestamp lastChange,

        @Schema(description = "Usuário que realizou a última alteração.")
        UsuarioResponse lastChangedBy
) implements Serializable { }