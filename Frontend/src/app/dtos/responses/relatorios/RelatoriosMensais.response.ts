import {BlocoServicoResponse} from "./BlocoServico.response";
import {PatrimonioServicoResponse} from "./PatrimonioServico.response";
import {GraficoResponse} from "./Grafico.response";

export interface RelatoriosMensaisResponse {
  graficos: GraficoResponse[];
  topPatrimonioMes: PatrimonioServicoResponse;
  topBlocoMes: BlocoServicoResponse;
}
