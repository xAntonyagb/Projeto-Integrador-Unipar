import {UsuarioResponse} from "./Usuario.response";

export interface CategoriaResponse {
    id: number;
    descricao: string;
    qtdTarefas: number;
    qtdTotalTarefas: number;
    qtdServicos: number;
    qtdTotalServicos: number;
    lastChange: Date;
    usuarioResponse: UsuarioResponse;
  }
