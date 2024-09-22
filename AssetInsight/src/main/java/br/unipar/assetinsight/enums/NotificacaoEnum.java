package br.unipar.assetinsight.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificacaoEnum {
    TAREFA(1, "Tarefa"),
    ORDEM_SERVICO(2, "Ordem de Serviço");

    private final int notificacao;
    private final String descricao;
}
