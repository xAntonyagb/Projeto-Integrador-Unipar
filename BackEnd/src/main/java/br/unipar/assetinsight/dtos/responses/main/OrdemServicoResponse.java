package br.unipar.assetinsight.dtos.responses.main;

import br.unipar.assetinsight.enums.StatusOrdemServicoEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * DTO de {@link br.unipar.assetinsight.entities.OrdemServicoEntity}
 */
public record OrdemServicoResponse(
        @Schema(description = "Id da ordem de serviço.", example = "1")
        long id,

        @Schema(description = "Descrição / Nome identificador da ordem de serviço.", example = "Exemplo")
        String descricao,

        @Schema(description = "Data de criação da ordem de serviço.", example = "2021-10-01T00:00:00Z")
        Timestamp data,

        @Schema(description = "Lista de serviços desta Ordem de serviço.", example = "[{\"id\": 1, \"patrimonio\": \"Patrimonio Exemplo\", \"descricao\": \"Descrição Exemplo\", \"quantidade\": 1, \"valorUnit\": 100.0, \"categoria\": 1, \"ambiente\": 1}]")
        List<ServicoRespose> servicos,

        @Schema(description = "Quantidade de serviços que essa ordem de serviço possui.", example = "1")
        long qtdServicos,

        @Schema(description = "Valor total da ordem de serviço.", example = "100.0")
        double valorTotal,

        @Schema(description = "Status da ordem de serviço.")
        StatusOrdemServicoEnum status,

        @Schema(description = "Usuário")
        UsuarioResponse lastChangedBy,

        @Schema(description = "Ultima alteração feita nesse registro.", example = "2021-10-01T00:00:00Z")
        Timestamp lastChange,

        @Schema(description = "Se a ordem de serviço está arquivada.")
        boolean arquivada
) implements Serializable { }