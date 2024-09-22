package br.unipar.assetinsight.dtos.responses;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link br.unipar.assetinsight.entities.AmbienteEntity}
 */
public record AmbienteResponse(
        long id,
        String descricao,
        BlocoResponse bloco,
        Timestamp lastChange,
        UsuarioResponse lastChangedBy
) implements Serializable { }