import { BlocoServicoResponse } from './bloco-servico.response';
import { PatrimonioServicoResponse } from './patrimonio-servico.response';
import { GraficoResponse } from './grafico.response';

export interface RelatoriosAnoResponse {
  graficos: GraficoResponse[];
  topPatrimonioAno: PatrimonioServicoResponse;
  topBlocoAno: BlocoServicoResponse;
  qtdServicosAno: number;
  totalGastoOS: number;
}
