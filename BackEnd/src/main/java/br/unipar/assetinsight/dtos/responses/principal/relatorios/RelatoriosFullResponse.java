package br.unipar.assetinsight.dtos.responses.principal.relatorios;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public record RelatoriosFullResponse(

        @Schema(description = "Relatórios gerais", example = "{\"ordensServicoPreenchidas\": {\"preenchidas\": \"03\", \"total\": \"05\", \"pendentes\": \"02\"}, \"tarefasConcluidas\": {\"concluidas\": \"03\", \"total\": \"04\", \"restantes\": \"01\"}, \"mediaGastosMes\": 1000.0, \"gastosMes\": {\"valorGasto\": \"1000.0\", \"mediaGastosMes\": \"16.7\"}}")
        RelatoriosGeraisResponse relatoriosGerais,

        @Schema(description = "Relatórios mensais", example = "{\"graficos\": [{\"titulo\": \"Gastos mensais em ambientes\", \"dados\": {\"Ambiente 01\": 1000, \"Ambiente 02\": 2000}}], \"topPatrimonioMes\": {\"patrimonio\": 123456, \"qtdServicos\": \"10\"}, \"topBlocoMes\": {\"bloco\": \"Bloco 01\", \"qtdServicos\": \"10\"}}")
        RelatoriosMensaisResponse relatoriosMensais,

        @Schema(description = "Relatórios anuais", example = "{\"graficos\": [{\"titulo\": \"Gastos anuais em ambientes\", \"dados\": {\"Ambiente 01\": 1000, \"Ambiente 02\": 2000}}], \"topPatrimonioAno\": {\"patrimonio\": 123456, \"qtdServicos\": \"10\"}, \"topBlocoAno\": {\"bloco\": \"Bloco 01\", \"qtdServicos\": \"10\"}, \"qtdServicosAno\": 338, \"totalGastoOS\": 10000.0}")
        RelatoriosAnoResponse relatoriosAnuais

) implements Serializable { }
