import { BlocoServicoResponse } from './BlocoServico.response';
import { PatrimonioServicoResponse } from './PatrimonioServico.response';
import { GraficoResponse } from './Grafico.response';

export interface RelatoriosAnoResponse {
  graficos: GraficoResponse[];
  topPatrimonioAno: PatrimonioServicoResponse;
  topBlocoAno: BlocoServicoResponse;
  qtdServicosAno: number;
  totalGastoOS: number;
}
