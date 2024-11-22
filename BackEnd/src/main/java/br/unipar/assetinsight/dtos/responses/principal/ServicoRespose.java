package br.unipar.assetinsight.dtos.responses.principal;

import br.unipar.assetinsight.dtos.responses.simple.PatrimonioSimpleResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO de {@link br.unipar.assetinsight.entities.ServicoEntity}
 */
public record ServicoRespose(
        @Schema(description = "Id do serviço.", example = "1")
        long id,

        @Schema(description = "Patrimonio em que foi prestada manutenção.", example = "{\"id\": 1, \"descricao\": \"Patrimonio Exemplo\"}")
        PatrimonioSimpleResponse patrimonio,

        @Schema(description = "Quantidade de vezes que este serviço foi prestado.", example = "1")
        long quantidade,

        @Schema(description = "Valor unitário do serviço.", example = "100.0")
        double valorUnit,

        @Schema(description = "Valor total do serviço.", example = "100.0")
        double valorTotal,

        @Schema(description = "Categoria do serviço.")
        CategoriaResponse categoria,

        @Schema(description = "Ambiente em que o serviço foi prestado.")
        AmbienteResponse ambiente,

        @Schema(description = "Ultima alteração feita nesse registro.", example = "2021-10-01T00:00:00Z")
        Timestamp lastChange,

        @Schema(description = "Usuário que realizou a última alteração.")
        UsuarioResponse lastChangedBy
) implements Serializable { }