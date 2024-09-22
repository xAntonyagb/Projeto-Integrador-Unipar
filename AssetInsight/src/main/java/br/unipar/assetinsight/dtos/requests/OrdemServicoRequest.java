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
        long id,
        @NotNull @NotEmpty @NotBlank String descricao,
        @NotNull Timestamp data,
        @NotNull @Size(min = 1) List<ServicoRequest> listServicos,
        @Positive double valorTotal,
        @NotNull StatusOrdemServicoEnum status
) implements Serializable {}