package br.unipar.assetinsight.dtos.responses;

import br.unipar.assetinsight.enums.StatusOrdemServicoEnum;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * DTO for {@link br.unipar.assetinsight.entities.OrdemServicoEntity}
 */
public record OrdemServicoResponse(
        long id,
        String descricao,
        Timestamp data,
        List<ServicoRespose> servicos,
        double valorTotal,
        StatusOrdemServicoEnum status,
        UsuarioResponse lastChangedBy,
        Timestamp lastChange,
        boolean arquivado
) implements Serializable { }