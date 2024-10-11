package br.unipar.assetinsight.dtos.requests;

import br.unipar.assetinsight.entities.RolesEntity;
import br.unipar.assetinsight.entities.UsuarioEntity;
import br.unipar.assetinsight.enums.PermissoesEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO para requests post de {@link UsuarioEntity} em {@link RolesEntity}
 */
public record RolesRequest(
        @Schema(description = "Id do cargo", example = "1", required = true)
        Long id,

        @Schema(description = "Permissão atribuída", example = "OPERADOR", required = true)
        @NotNull PermissoesEnum permissao
) implements Serializable { }