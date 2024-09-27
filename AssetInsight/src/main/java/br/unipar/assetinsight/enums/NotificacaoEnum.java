package br.unipar.assetinsight.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Tipo de notificações")
public enum NotificacaoEnum {
    @Schema(description = "Notificação de tarefa")
    TAREFA(1, "Tarefa"),

    @Schema(description = "Notificação de ordem de serviço")
    ORDEM_SERVICO(2, "Ordem de Serviço");

    private final int notificacao;
    private final String descricao;
}
