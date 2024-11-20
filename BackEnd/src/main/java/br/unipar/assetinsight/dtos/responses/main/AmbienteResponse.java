package br.unipar.assetinsight.dtos.responses.main;

import br.unipar.assetinsight.dtos.responses.simple.PatrimonioSimpleResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * DTO de {@link br.unipar.assetinsight.entities.AmbienteEntity}
 */
@Builder
public record AmbienteResponse(
        @Schema(description = "Id do ambiente.", example = "1")
        long id,

        @Schema(description = "Nome do ambiente.", example = "Sala 01")
        String descricao,

        @Schema(description = "Bloco ao qual o ambiente pertence.")
        BlocoResponse bloco,

        @Schema(description = "Lista de patrimonios que pertencem a este ambiente", example = "[{\"id\": 1, \"descricao\": \"Patrimonio Exemplo\"}]")
        List<PatrimonioSimpleResponse> patrimonios,

        @Schema(description = "Quantidade de patrimônios cadastrados nesse ambiente.", example = "10")
        long qtdPatrimonios,

        @Schema(description = "Ultima alteração feita nesse registro.", example = "2021-10-01T00:00:00Z")
        Timestamp lastChange,

        @Schema(description = "Usuário que realizou a última alteração.")
        UsuarioResponse lastChangedBy
) implements Serializable { }