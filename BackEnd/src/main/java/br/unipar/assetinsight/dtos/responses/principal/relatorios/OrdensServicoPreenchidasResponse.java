package br.unipar.assetinsight.dtos.responses.principal.relatorios;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public record OrdensServicoPreenchidasResponse(
        @Schema(description = "Quantidade de Ordens de Serviço preenchidas.", example = "05")
        Long preenchidas,

        @Schema(description = "Quantidade total de Ordens de Serviço.", example = "07")
        Long total,

        @Schema(description = "Quantidade de Ordens de Serviço pendentes.", example = "02")
        Long pendentes

) implements Serializable { }

