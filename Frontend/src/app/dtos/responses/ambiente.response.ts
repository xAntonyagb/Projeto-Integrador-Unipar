import { BlocoResponse } from "./bloco.response";
import { ServicoResponse } from "./servico.response";
import {PatrimonioResponse} from "./patrimonio.response";


export interface AmbienteResponse {
  id?: number;
  descricao?: string;
  bloco?: BlocoResponse[];
  patrimonios?:PatrimonioResponse[];
  qtdPatrimonios?:number;
  servicos? :ServicoResponse[];
}
