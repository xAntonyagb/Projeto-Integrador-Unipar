package br.unipar.assetinsight.dtos.responses;

import br.unipar.assetinsight.entities.UsuarioEntity;
import br.unipar.assetinsight.enums.PermissoesEnum;

import java.util.Date;
import java.util.List;

/**
 * DTO para login no sistema. Record de {@link UsuarioEntity}
 */
public record LoginResponse(
        String acessToken,
        Date expiresIn,
        Date createdAt,
        List<PermissoesEnum> permissoes
) { }
