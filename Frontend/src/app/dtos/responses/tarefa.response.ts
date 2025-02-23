import { UsuarioResponse } from './usuario.response';
import { AmbienteResponse } from './ambiente.response';
import { CategoriaResponse } from './categoria.response';
import { PrioridadeTarefa } from '../enums/prioridade-tarefa.enum';
import { StatusTarefa } from '../enums/status-tarefa.enum';

export interface TarefaResponse {
  id: Number;
  titulo: String;
  descricao: String;
  previsao: Date;
  ambiente: AmbienteResponse;
  categoria: CategoriaResponse;
  prioridade: PrioridadeTarefa;
  status: StatusTarefa;
  arquivado: Boolean;
  lastChange: Date;
  lastChangedBy: UsuarioResponse;
}
