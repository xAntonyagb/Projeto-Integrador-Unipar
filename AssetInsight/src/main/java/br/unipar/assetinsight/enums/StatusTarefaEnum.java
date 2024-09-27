package br.unipar.assetinsight.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Status de tarefas")
public enum StatusTarefaEnum {
    @Schema(description = "Tarefa aberta - Em andamento")
    ABERTA(1, "Aberta"),

    @Schema(description = "Tarefa atrasada - Passou do prazo")
    ATRASADA(2, "Atrasada"),

    @Schema(description = "Tarefa concluída - Finalizada")
    CONCLUIDA(3, "Concluída"),;

    private final int statusTarefa;
    private final String descricao;
}
