package br.unipar.assetinsight.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PermissoesEnum {
    SUPER(1, "Super"),
    ADMINISTRADOR(2, "Administrador"),
    OPERADOR(3, "Operador"),;

    private final long id;
    private final String descricao;

}
