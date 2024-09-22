package br.unipar.assetinsight.dtos.responses;

import br.unipar.assetinsight.entities.UsuarioEntity;
import br.unipar.assetinsight.enums.TipoArquivadoEnum;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link br.unipar.assetinsight.entities.ArquivadoEntity}
 */
public record ArquivadoResponse(
        long id,
        TipoArquivadoEnum tipo,
        OrdemServicoResponse ordemServico,
        TarefaResponse tarefa,
        Timestamp dtExcluir,
        Timestamp dtArquivado,
        UsuarioEntity arquivadoBy
) implements Serializable { }