package br.unipar.assetinsight.dtos.responses.principal.relatorios;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public record BlocoServicoResponse(
        @Schema(description = "Bloco.", example = "Bloco 01")
        String bloco,

        @Schema(description = "Quantidade de serviços.", example = "10")
        Long qtdServicos

) implements Serializable { }

