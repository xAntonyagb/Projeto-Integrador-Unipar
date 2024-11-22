package br.unipar.assetinsight.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Tipos de registros arquivados")
public enum TipoGraficosEnum {

    @Schema(description = "Gráfico do tipo Pizza")
    PIZZA,

    @Schema(description = "Gráfico do tipo Barra")
    LINHA,

    @Schema(description = "Gráfico do tipo Barra")
    BARRA

}
