package br.unipar.assetinsight.dtos.responses.principal.relatorios;

import br.unipar.assetinsight.enums.TipoGraficosEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Map;

public record GraficoResponse (
        @Schema(description = "Título do gráfico.", example = "Gastos do mês em ambientes")
        String titulo,

        @Schema(description = "Tipo do gráfico.", example = "PIZZA")
        TipoGraficosEnum tipo,

        @Schema(description = "Dados do gráfico.", example = "{\"Bloco 01\": 1000.0, \"Bloco 02\": 2000.0}")
        Map<String, Double> dados
) implements Serializable { }