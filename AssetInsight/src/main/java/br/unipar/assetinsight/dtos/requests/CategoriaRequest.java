package br.unipar.assetinsight.dtos.requests;

import br.unipar.assetinsight.entities.CategoriaEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO para requests post de {@link CategoriaEntity}
 */
public record CategoriaRequest(
        @Schema(description = "Id da categoria", example = "1", required = false)
        Long id,

        @Schema(description = "Descrição / Nome identificador da categoria", example = "Exemplo", required = true)
        @NotNull @NotEmpty @NotBlank String descricao
) implements Serializable { }