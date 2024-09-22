package br.unipar.assetinsight.dtos.responses;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link br.unipar.assetinsight.entities.CategoriaEntity}
 */
public record CategoriaResponse(
        long id,
        String descricao,
        Timestamp lastChange,
        UsuarioResponse lastChangedBy
) implements Serializable { }