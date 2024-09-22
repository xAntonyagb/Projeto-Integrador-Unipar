package br.unipar.assetinsight.dtos.requests;

import br.unipar.assetinsight.entities.UsuarioEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO para login no sistema. Record de {@link UsuarioEntity}
 */
public record LoginRequest(
        @NotNull @NotEmpty @NotBlank String username,
        @NotNull @NotEmpty @NotBlank String password
) implements Serializable { }