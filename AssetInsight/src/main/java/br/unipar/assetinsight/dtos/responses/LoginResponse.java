package br.unipar.assetinsight.dtos.responses;

import br.unipar.assetinsight.entities.UsuarioEntity;
import br.unipar.assetinsight.enums.PermissoesEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.List;

/**
 * DTO para login no sistema. Record de {@link UsuarioEntity}
 */
public record LoginResponse(
        @Schema(description = "Token de acesso.")
        String acessToken,

        @Schema(description = "Tempo de expiração do token.")
        Instant expiresIn,

        @Schema(description = "Tempo que o token foi criado.")
        Instant createdAt,

        @Schema(description = "Permissões do usuário.")
        List<PermissoesEnum> permissoes
) { }
