package br.unipar.assetinsight.dtos.requests;
import br.unipar.assetinsight.entities.OrdemServicoEntity;
import br.unipar.assetinsight.entities.ServicoEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO para requests post de {@link ServicoEntity} em {@link OrdemServicoEntity}
 */
public record ServicoRequest(
        long id,
        @NotNull @NotEmpty @NotBlank String patrimonio,
        @NotNull @NotEmpty @NotBlank String descricao,
        @Positive long quantidade,
        @Positive double valorUnit,
        @Positive double valorTotal,
        @NotNull CategoriaRequest categoria,
        @NotNull AmbienteRequest ambiente
) implements Serializable { }