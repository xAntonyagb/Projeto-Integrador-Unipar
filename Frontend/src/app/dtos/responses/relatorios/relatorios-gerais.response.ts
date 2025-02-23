import { GastosMesResponse } from './gastos-mes.response';
import { OrdensPreenchidasResponse } from './ordens-preenchidas.response';
import { TarefasConcluidasResponse } from './tarefas-concluidas.response';

export interface RelatoriosGeraisResponse {
  ordensServicoPreenchidas: OrdensPreenchidasResponse;
  tarefasConcluidas: TarefasConcluidasResponse;
  mediaGastosMes: number;
  gastosMes: GastosMesResponse;
}
