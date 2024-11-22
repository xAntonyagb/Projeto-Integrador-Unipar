package br.unipar.assetinsight.dtos.responses.principal.relatorios;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

public record RelatoriosAnoResponse(
        @Schema(description = "Gráficos relacionados aos gastos do ano", example = "[{\"titulo\": \"Gastos por bloco\", \"dados\": {\"Bloco 01\": 1000, \"Bloco 02\": 2000}}]")
        List<GraficoResponse> graficos,

        @Schema(description = "Patrimonio com maior número de serviços do ano", example = "{\"patrimonio\": 123456, \"qtdServicos\": \"10\"}")
        PatrimonioServicoResponse topPatrimonioAno,

        @Schema(description = "Bloco com maior número de serviços do ano", example = "{\"bloco\": \"Bloco 01\", \"qtdServicos\": \"10\"}")
        BlocoServicoResponse topBlocoAno,

        @Schema(description = "Quantidade de serviços realizados no ano", example = "338")
        Double qtdServicosAno,

        @Schema(description = "Total gasto com Ordens de Serviço no ano", example = "10000.0")
        Double totalGastoOS

) implements Serializable { }

