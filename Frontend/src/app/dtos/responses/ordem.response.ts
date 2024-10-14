import { ServicoResponse } from "./servico.response";

export interface OrdemResponse {
  id: number;
  descricao: string;
  data : Date;
  servicos:ServicoResponse;
  qtdServicos:number;
  valorTotal: number;
  status:string;
}

