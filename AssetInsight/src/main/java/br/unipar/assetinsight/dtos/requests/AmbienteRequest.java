package br.unipar.assetinsight.dtos.requests;

import br.unipar.assetinsight.entities.AmbienteEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO para requests post de {@link AmbienteEntity}
 */
public record AmbienteRequest(
        @Schema(description = "Id do ambiente", example = "1", required = false)
        Long id,

        @Schema(description = "Descrição / Nome identificador do ambiente", example = "Exemplo", required = true)
        @NotNull @NotEmpty @NotBlank String descricao,

        @Schema(description = "Id do bloco que este ambiente pertence", example = "1", required = true)
        @NotNull Long bloco
) implements Serializable { }