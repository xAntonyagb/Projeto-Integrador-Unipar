import { UsuarioResponse } from './Usuario.response';

export interface CategoriaResponse {
  id: number | any;
  descricao: string | any;
  qtdTarefas: number | any;
  qtdTotalTarefas: number | any;
  qtdServicos: number | any;
  qtdTotalServicos: number | any;
  lastChange: Date | any;
  usuarioResponse: UsuarioResponse | any;
}
