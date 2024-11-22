import {ServicoResponse} from "./Servico.response";
import {UsuarioResponse} from "./Usuario.response";
import {StatusOrdem} from "../enums/StatusOrdem.enum";

export interface OrdemServicoResponse {
  id: Number;
  descricao: String;
  data: Date;
  servicos: ServicoResponse[];
  qtdServicos: Number;
  valorTotal: Number;
  status: StatusOrdem;
  lastChangedBy: UsuarioResponse;
  lastChange: Date;
  arquivada: Boolean;
}




