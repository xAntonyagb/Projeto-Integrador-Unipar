import { BlocoServicoResponse } from './bloco-servico.response';
import { PatrimonioServicoResponse } from './patrimonio-servico.response';
import { GraficoResponse } from './grafico.response';

export interface RelatoriosMensaisResponse {
  graficos: GraficoResponse[];
  topPatrimonioMes: PatrimonioServicoResponse;
  topBlocoMes: BlocoServicoResponse;
}
