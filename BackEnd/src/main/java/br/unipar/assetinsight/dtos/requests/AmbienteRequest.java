package br.unipar.assetinsight.dtos.requests;

import br.unipar.assetinsight.entities.AmbienteEntity;
import br.unipar.assetinsight.entities.PatrimonioEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

/**
 * DTO para requests post de {@link AmbienteEntity}
 */
public record AmbienteRequest(
        @Schema(description = "Id do ambiente", example = "1", required = false)
        Long id,

        @Schema(description = "Lista de patrimonios que pertencem a este ambiente", example = "[1, 2, 3]", required = false)
        List<Long> patrimonios,

        @Schema(description = "Nome do ambiente", example = "Sala 1", required = true)
        @NotBlank String descricao,

        @Schema(description = "Id do bloco que este ambiente pertence", example = "1", required = true)
        @NotNull Long bloco
) implements Serializable { }