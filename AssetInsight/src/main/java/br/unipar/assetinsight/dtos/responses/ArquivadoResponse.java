package br.unipar.assetinsight.dtos.responses;

import br.unipar.assetinsight.entities.UsuarioEntity;
import br.unipar.assetinsight.enums.TipoArquivadoEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link br.unipar.assetinsight.entities.ArquivadoEntity}
 */
public record ArquivadoResponse(
        @Schema(description = "Id do arquivamento.", example = "1")
        long id,

        @Schema(description = "Tipo de arquivamento.")
        TipoArquivadoEnum tipo,

        @Schema(description = "Ordem de serviço arquivada.")
        OrdemServicoResponse ordemServico,

        @Schema(description = "Tarefa arquivada.")
        TarefaResponse tarefa,

        @Schema(description = "Data em que será excluído permanentemente do sistema.", example = "2021-10-01T00:00:00Z")
        Timestamp dtExcluir,

        @Schema(description = "Data em que foi arquivada.")
        Timestamp dtArquivado,

        @Schema(description = "Usuário que arquivou.")
        UsuarioEntity arquivadoBy
) implements Serializable { }