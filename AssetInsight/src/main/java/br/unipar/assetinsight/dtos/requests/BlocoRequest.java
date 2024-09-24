package br.unipar.assetinsight.dtos.requests;

import br.unipar.assetinsight.entities.BlocoEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO para requests post de {@link BlocoEntity}
 */
public record BlocoRequest(
        Long id,
        @NotNull @NotEmpty @NotBlank String descricao
) implements Serializable { }