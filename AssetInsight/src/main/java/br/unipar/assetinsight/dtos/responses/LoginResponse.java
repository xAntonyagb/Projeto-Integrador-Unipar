package br.unipar.assetinsight.dtos.responses;

import br.unipar.assetinsight.entities.UsuarioEntity;

import java.time.Instant;

/**
 * DTO para login no sistema. Record de {@link UsuarioEntity}
 */
public record LoginResponse(
        String acessToken,
        Long expiresIn,
        Instant createdAt
) { }
