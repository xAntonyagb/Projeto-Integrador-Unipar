import { UsuarioResponse } from "./usuario.response";

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
  export interface AddCategoria{
    id: number
    descricao: string;
  }
