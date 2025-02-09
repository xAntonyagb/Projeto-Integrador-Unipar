import { RelatoriosAnoResponse } from './RelatoriosAno.response';
import { RelatoriosMensaisResponse } from './RelatoriosMensais.response';
import { RelatoriosGeraisResponse } from './RelatoriosGerais.response';

export interface RelatoriosFullResponse {
  relatoriosGerais: RelatoriosGeraisResponse;
  relatoriosMensais: RelatoriosMensaisResponse;
  relatoriosAnuais: RelatoriosAnoResponse;
}
