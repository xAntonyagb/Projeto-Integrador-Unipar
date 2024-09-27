package br.unipar.assetinsight.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Status de ordem de serviço")
public enum StatusOrdemServicoEnum {

    @Schema(description = "Ordem de serviço preenchida")
    PREENCHIDA(1, "Preenchida"),

    @Schema(description = "Ordem de serviço incompleta (falta preencher todos os campos)")
    INCOMPLETA(2, "Incompleta"),;

    private final int statusTarefa;
    private final String descricao;
}
