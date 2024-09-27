package br.unipar.assetinsight.dtos.requests;

import java.io.Serializable;

import br.unipar.assetinsight.entities.OrdemServicoEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Timestamp;
import java.util.List;

/**
 * DTO para requests post de {@link OrdemServicoEntity}
 */
public record OrdemServicoRequest(
        @Schema(description = "Id da ordem de serviço", example = "1", required = false)
        Long id,

        @Schema(description = "Descrição / Nome identificador da ordem de serviço", example = "Exemplo", required = false)
        String descricao,

        @Schema(description = "Data de criação da ordem de serviço", example = "2021-10-01T00:00:00Z", required = false)
        Timestamp data,

        @Schema(description = "Lista de serviços desta Ordem de serviço", example = "[{\"id\": 1, \"patrimonio\": \"Patrimonio Exemplo\", \"descricao\": \"Descrição Exemplo\", \"quantidade\": 1, \"valorUnit\": 100.0, \"categoria\": 1, \"ambiente\": 1}]", required = false)
        List<ServicoRequest> servicos
) implements Serializable {}