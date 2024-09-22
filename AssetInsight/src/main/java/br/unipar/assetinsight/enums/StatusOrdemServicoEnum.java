package br.unipar.assetinsight.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusOrdemServicoEnum {
    PREENCHIDA(1, "Preenchida"),
    INCOMPLETA(2, "Incompleta"),;

    private final int statusTarefa;
    private final String descricao;
}
