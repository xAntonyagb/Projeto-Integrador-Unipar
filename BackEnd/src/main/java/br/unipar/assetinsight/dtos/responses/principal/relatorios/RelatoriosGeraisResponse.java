package br.unipar.assetinsight.dtos.responses.principal.relatorios;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public record RelatoriosGeraisResponse(
        @Schema(description = "Relatório de Ordens de Serviço preenchidas", example = "{\"preenchidas\": \"03\", \"total\": \"05\", \"pendentes\": \"02\"}" )
        OrdensServicoPreenchidasResponse ordensServicoPreenchidas,

        @Schema(description = "Relatório de Tarefas concluídas", example = "{\"concluidas\": \"03\", \"total\": \"04\", \"restantes\": \"01\"}")
        TarefasConcluidasResponse tarefasConcluidas,

        @Schema(description = "Valor médio gasto por mês em Ordens de Serviço", example = "1000.0")
        Double mediaGastosMes,

        @Schema(description = "Valor gasto atualmente no mês", example = "{\"valorGasto\": \"1000.0\", \"mediaGastosMes\": \"16.7\"}")
        GastosMesResponse gastosMes

) implements Serializable { }

