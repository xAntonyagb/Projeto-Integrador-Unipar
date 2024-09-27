package br.unipar.assetinsight.dtos.requests;

import br.unipar.assetinsight.entities.UsuarioEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO para login no sistema. Record de {@link UsuarioEntity}
 * @param password
 * @param username
 */
public record LoginRequest(
        @Schema(description = "Nome de usuário", example = "admin", required = true)
        @NotNull @NotEmpty @NotBlank String username,

        @Schema(description = "Senha do usuário", example = "123456", required = true)
        @NotNull @NotEmpty @NotBlank String password
) implements Serializable { }