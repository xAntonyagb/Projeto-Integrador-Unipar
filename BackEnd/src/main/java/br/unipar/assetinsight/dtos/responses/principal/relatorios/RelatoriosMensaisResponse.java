package br.unipar.assetinsight.dtos.responses.principal.relatorios;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public record RelatoriosMensaisResponse(
        @Schema(description = "Gráficos relacionados aos gastos do mês", example = "[{\"titulo\": \"Gastos por bloco\", \"dados\": {\"Bloco 01\": 1000, \"Bloco 02\": 2000}}]")
        List<GraficoResponse> graficos,

        @Schema(description = "Patrimonio com maior número de serviços do mês", example = "{\"patrimonio\": 123456, \"qtdServicos\": \"10\"}")
        PatrimonioServicoResponse topPatrimonioMes,

        @Schema(description = "Bloco com maior número de serviços do mês", example = "{\"bloco\": \"Bloco 01\", \"qtdServicos\": \"10\"}")
        BlocoServicoResponse topBlocoMes

) implements Serializable { }

