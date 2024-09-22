package br.unipar.assetinsight.dtos.responses;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link br.unipar.assetinsight.entities.ServicoEntity}
 */
public record ServicoRespose(
        long id,
        String patrimonio,
        String descricao,
        long quantidade,
        double valorUnit,
        double valorTotal,
        CategoriaResponse categoria,
        AmbienteResponse ambiente,
        Timestamp lastChange,
        UsuarioResponse lastChangedBy
) implements Serializable { }