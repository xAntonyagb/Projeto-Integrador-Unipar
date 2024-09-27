package br.unipar.assetinsight.dtos.responses;

import br.unipar.assetinsight.enums.PermissoesEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link br.unipar.assetinsight.entities.UsuarioEntity}
 */
public record UsuarioResponse(
        @Schema(description = "Nome do usuário", example = "Usuário Exemplo")
        String username,

        @Schema(description = "Data de criação do usuário", example = "2021-10-01T00:00:00Z")
        Timestamp dtCriacao,

        @Schema(description = "Data do ultimo acesso do usuário", example = "2023-10-01T00:00:00Z")
        Timestamp lastLogin,

        @Schema(description = "Permissões do usuário")
        List<PermissoesEnum> permissoes
) implements Serializable { }