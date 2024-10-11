package br.unipar.assetinsight.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link br.unipar.assetinsight.entities.AmbienteEntity}
 */
@Builder
public record AmbienteResponse(
        @Schema(description = "Id do ambiente.", example = "1")
        long id,

        @Schema(description = "Nome do ambiente.", example = "Sala 01")
        String descricao,

        @Schema(description = "Bloco ao qual o ambiente pertence.")
        BlocoResponse bloco,

        @Schema(description = "Quantidade de patrimônios cadastrados nesse ambiente.", example = "10")
        long qtdPatrimonios,

        @Schema(description = "Ultima alteração feita nesse registro.", example = "2021-10-01T00:00:00Z")
        Timestamp lastChange,

        @Schema(description = "Usuário que realizou a última alteração.")
        UsuarioResponse lastChangedBy
) implements Serializable { }