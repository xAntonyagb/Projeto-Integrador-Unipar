package br.unipar.assetinsight.dtos.requests;

import java.io.Serializable;

import br.unipar.assetinsight.entities.OrdemServicoEntity;
import br.unipar.assetinsight.enums.StatusOrdemServicoEnum;
import jakarta.validation.constraints.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * DTO para requests post de {@link OrdemServicoEntity}
 */
public record OrdemServicoRequest(
        Long id,
        String descricao,
        Timestamp data,
        List<ServicoRequest> servicos,
        @Positive double valorTotal
) implements Serializable {}