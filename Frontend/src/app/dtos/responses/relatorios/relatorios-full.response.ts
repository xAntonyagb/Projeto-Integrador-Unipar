import { RelatoriosAnoResponse } from './relatorios-ano.response';
import { RelatoriosMensaisResponse } from './relatorios-mensais.response';
import { RelatoriosGeraisResponse } from './relatorios-gerais.response';

export interface RelatoriosFullResponse {
  relatoriosGerais: RelatoriosGeraisResponse;
  relatoriosMensais: RelatoriosMensaisResponse;
  relatoriosAnuais: RelatoriosAnoResponse;
}
