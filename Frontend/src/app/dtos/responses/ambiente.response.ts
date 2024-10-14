import { BlocoResponse } from "./bloco.response";


export interface AmbienteResponse {
  id: number;
  descricao: string;
  bloco: BlocoResponse;
  qtdPatrimonios:number;
}
