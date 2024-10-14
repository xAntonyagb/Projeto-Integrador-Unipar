import { AmbienteResponse } from "./ambiente.response";
import { CategoriaResponse } from "./categoria.response";
import { UsuarioResponse } from "./usuario.response";

export interface TarefaResponse {
    id: string;
    titulo: string;
    descricao: string;
    previsao: string;
    ambiente: AmbienteResponse;
    categoria: CategoriaResponse;
    prioridade: string;
    status: string;
    arquivado: boolean;
    lastChange: Date;
    lastChangedBy: UsuarioResponse;
  }
