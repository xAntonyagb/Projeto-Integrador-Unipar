package br.unipar.assetinsight.dtos.requests;
import br.unipar.assetinsight.entities.OrdemServicoEntity;
import br.unipar.assetinsight.entities.ServicoEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * DTO para requests post de {@link ServicoEntity} em {@link OrdemServicoEntity}
 */
public record ServicoRequest(
        @Schema(description = "Id do serviço.", example = "1", required = false)
        Long id,

        @Schema(description = "Patrimonio em que foi prestada manutenção.", example = "1", required = false)
        Long patrimonio,

        @Schema(description = "Quantidade de vezes que este serviço foi prestado.", example = "1", required = false)
        long quantidade,

        @Schema(description = "Valor unitário do serviço.", example = "100.0", required = false)
        double valorUnit,

        @Schema(description = "Id da categoria do serviço.", example = "1", required = false)
        Long categoria,

        @Schema(description = "Id do ambiente em que o serviço foi prestado.", example = "1", required = false)
        Long ambiente
) implements Serializable { }