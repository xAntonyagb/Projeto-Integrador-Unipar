package br.unipar.assetinsight.dtos.responses;

import br.unipar.assetinsight.enums.PermissoesEnum;

import java.util.List;

public record CadastroResponse(
        String username,
        String createdAt,
        List<PermissoesEnum> permissoes
) { }
