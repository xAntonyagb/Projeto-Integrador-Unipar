package br.unipar.assetinsight.dtos.requests;

import br.unipar.assetinsight.entities.BlocoEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO para requests post de {@link BlocoEntity}
 */
public record BlocoRequest(
        @Schema(description = "Id do bloco", example = "1", required = false)
        Long id,

        @Schema(description = "Descrição / Nome identificador do bloco", example = "Exemplo", required = true)
        @NotNull @NotEmpty @NotBlank String descricao
) implements Serializable { }