import { AmbienteResponse } from "./ambiente.response";
import { CategoriaResponse } from "./categoria.response";
import { UsuarioResponse } from "./usuario.response";

export interface TarefaResponse {
    id: string;
    titulo: AddTarefas;
    descricao: AddTarefas;
    previsao: AddTarefas;
    ambiente: AddTarefas;
    categoria: AddTarefas;
    prioridade: AddTarefas;
    status: AddTarefas;
    arquivado: boolean;
    lastChange: Date;
    lastChangedBy: UsuarioResponse;
  }
  export interface AddTarefas{
    titulo: string;
    descricao: string;
    previsao: Date;
    prioridade: string;
    status: StatusTarefa;
    categoria :CategoriaResponse;
    ambiente: AmbienteResponse;
  }
  export enum StatusTarefa {
    ABERTA = 'Pendente',
    ATRASADA = 'Atrasada',
    CONCLUIDA = 'Concluída'
  }
  export function getStatusDescricao(status: StatusTarefa): string {
    switch (status) {
      case StatusTarefa.ABERTA: return "Aberta";
      case StatusTarefa.ATRASADA: return "Atrasada";
      case StatusTarefa.CONCLUIDA: return "Concluída";
      default: return "Desconhecido";
    }
  }
