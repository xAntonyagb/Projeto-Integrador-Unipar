package br.unipar.assetinsight.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Tipos de registros arquivados")
public enum TipoArquivadoEnum {

    @Schema(description = "Tarefa")
    TAREFA(1, "Tarefa"),

    @Schema(description = "Ordem de serviço")
    ORDEM_SERVICO(2, "Ordem de Serviço");

    private final int tipoArquivado;
    private final String descricao;
}

