package br.unipar.assetinsight.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PrioridadeEnum {
    ALTA(1, "Alta"),
    MEDIA(2, "Média"),
    BAIXA(3, "Baixa");

    private final int prioridade;
    private final String descricao;
}
