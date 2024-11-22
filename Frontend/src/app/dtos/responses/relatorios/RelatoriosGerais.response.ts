import {GastosMesResponse} from "./GastosMes.response";
import {OrdensPreenchidasResponse} from "./OrdensPreenchidas.response";
import {TarefasConcluidasResponse} from "./TarefasConcluidas.response";

export interface RelatoriosGeraisResponse {
  ordensServicoPreenchidas: OrdensPreenchidasResponse;
  tarefasConcluidas: TarefasConcluidasResponse;
  mediaGastosMes: number;
  gastosMes: GastosMesResponse;
}
