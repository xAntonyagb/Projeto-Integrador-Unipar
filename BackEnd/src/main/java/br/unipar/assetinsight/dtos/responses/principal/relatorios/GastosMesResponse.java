package br.unipar.assetinsight.dtos.responses.principal.relatorios;

import br.unipar.assetinsight.enums.TipoGraficosEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Map;

public record GastosMesResponse(
        @Schema(description = "Valor total gasto este mês.", example = "1000.0")
        Double valorGasto,

        @Schema(description = "Porcentagem de valor gasto este mês em comparação com o anterior.", example = "16.7")
        Double mediaGastosMes
) implements Serializable { }