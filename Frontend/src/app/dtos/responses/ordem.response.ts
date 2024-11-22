import { AmbienteResponse } from "./ambiente.response";
import { ServicoResponse } from "./servico.response";
import { UsuarioResponse } from "./usuario.response";

export interface OrdemResponse {
  id: number;
  descricao: string;
  data: Date;
  servicos: ServicoResponse[];
  qtdServicos: number;
  valorTotal: number;
  status: StatusOrdem;
  LastChangedBy: UsuarioResponse;
}
export enum StatusOrdem {
  INCOMPLETA = 'Incompleta',
  PREENCHIDA = 'Preenchida',
}




