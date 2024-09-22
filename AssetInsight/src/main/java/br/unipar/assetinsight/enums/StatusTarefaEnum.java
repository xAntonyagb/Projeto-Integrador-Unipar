package br.unipar.assetinsight.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusTarefaEnum {
    ABERTA(1, "Aberta"),
    ATRASADA(2, "Atrasada"),
    CONCLUIDA(3, "Concluída"),;

    private final int statusTarefa;
    private final String descricao;
}
