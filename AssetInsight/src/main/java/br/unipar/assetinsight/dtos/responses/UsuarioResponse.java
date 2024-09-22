package br.unipar.assetinsight.dtos.responses;

import br.unipar.assetinsight.enums.PermissoesEnum;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link br.unipar.assetinsight.entities.UsuarioEntity}
 */
public record UsuarioResponse(
        UUID id,
        String username,
        Timestamp dtCriacao,
        Timestamp lastLogin,
        List<PermissoesEnum> permissoes
) implements Serializable { }