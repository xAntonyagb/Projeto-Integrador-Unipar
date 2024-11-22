package br.unipar.assetinsight.dtos.responses.principal.relatorios;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Map;

public record PatrimonioServicoResponse(
        @Schema(description = "Patrimonio.", example = "123456")
        Long patrimonio,

        @Schema(description = "Quantidade de serviços.", example = "10")
        Long qtdServicos

) implements Serializable { }

