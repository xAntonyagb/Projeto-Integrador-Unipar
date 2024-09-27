package br.unipar.assetinsight.dtos.requests;
import br.unipar.assetinsight.entities.OrdemServicoEntity;
import br.unipar.assetinsight.entities.ServicoEntity;

import java.io.Serializable;

/**
 * DTO para requests post de {@link ServicoEntity} em {@link OrdemServicoEntity}
 */
public record ServicoRequest(
        Long id,
        String patrimonio,
        String descricao,
        long quantidade,
        double valorUnit,
        double valorTotal,
        Long categoria,
        Long ambiente
) implements Serializable { }