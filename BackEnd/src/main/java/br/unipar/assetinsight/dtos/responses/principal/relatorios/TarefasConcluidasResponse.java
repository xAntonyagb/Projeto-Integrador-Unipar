package br.unipar.assetinsight.dtos.responses.principal.relatorios;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public record TarefasConcluidasResponse(
        @Schema(description = "Quantidade de Tarefas já concluídas.", example = "03")
        Long concluidas,

        @Schema(description = "Quantidade total de Tarefas.", example = "04")
        Long total,

        @Schema(description = "Quantidade de Tarefas ainda não concluídas.", example = "01")
        Long restantes

) implements Serializable { }

