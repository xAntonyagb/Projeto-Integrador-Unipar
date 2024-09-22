package br.unipar.assetinsight.dtos.requests;

import br.unipar.assetinsight.entities.RolesEntity;
import br.unipar.assetinsight.entities.UsuarioEntity;
import br.unipar.assetinsight.enums.PermissoesEnum;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO para requests post de {@link UsuarioEntity} em {@link RolesEntity}
 */
public record RolesRequest(
        long id,
        @NotNull PermissoesEnum permissao
) implements Serializable { }