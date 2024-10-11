package br.unipar.assetinsight.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Prioridades de tarefas")
public enum PrioridadeEnum {
    @Schema(description = "Prioridade alta")
    ALTA(1, "Alta"),

    @Schema(description = "Prioridade média")
    MEDIA(2, "Média"),

    @Schema(description = "Prioridade baixa")
    BAIXA(3, "Baixa");

    private final int prioridade;
    private final String descricao;
}
