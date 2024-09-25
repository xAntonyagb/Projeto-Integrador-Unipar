package br.unipar.assetinsight.dtos.responses;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link br.unipar.assetinsight.entities.BlocoEntity}
 */
public record BlocoResponse(
        Long id,
        String descricao,
        Timestamp lastChange,
        UsuarioResponse lastChangedBy
) implements Serializable { }