import { AmbienteResponse } from "./ambiente.response";
import { ServicoResponse } from "./servico.response";
import { UsuarioResponse } from "./usuario.response";

export interface OrdemResponse {
  descricao: string;
  data: Date;
  servicos: ServicoResponse[];
}

