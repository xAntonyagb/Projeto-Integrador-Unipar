package br.unipar.assetinsight.dtos.responses;

import br.unipar.assetinsight.enums.PermissoesEnum;

import java.time.Instant;
import java.util.List;

public record CadastroResponse(
        String username,
        Instant createdAt,
        List<PermissoesEnum> permissoes
) { }
