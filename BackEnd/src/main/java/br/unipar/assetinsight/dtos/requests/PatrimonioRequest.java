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
public record PatrimonioRequest(
        @Schema(description = "Id do patrimonio", example = "1", required = true)
        @NotNull Long patrimonio,

        @Schema(description = "Descrição / Nome identificador do patrimonio", example = "Exemplo", required = true)
        @NotNull @NotEmpty @NotBlank String descricao,

        @Schema(description = "Id ambiente que este patrimonio pertence", example = "1", required = false)
        Long ambiente
) implements Serializable { }