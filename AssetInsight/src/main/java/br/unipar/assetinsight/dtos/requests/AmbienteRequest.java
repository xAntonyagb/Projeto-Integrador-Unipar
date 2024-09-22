package br.unipar.assetinsight.dtos.requests;

import br.unipar.assetinsight.entities.AmbienteEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO para requests post de {@link AmbienteEntity}
 */
public record AmbienteRequest(
        long id,
        @NotNull @NotEmpty @NotBlank String descricao,
        @NotNull BlocoRequest bloco
) implements Serializable { }