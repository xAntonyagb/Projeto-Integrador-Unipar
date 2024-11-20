import { BlocoResponse } from "./bloco.response";
import { ServicoResponse } from "./servico.response";


export interface AmbienteResponse {
  id: number;
  descricao: string;
  bloco: BlocoResponse;
  qtdPatrimonios:number;
  servicos? :ServicoResponse[];
}
