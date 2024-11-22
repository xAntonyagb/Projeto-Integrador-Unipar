import {UsuarioResponse} from "./Usuario.response";
import {AmbienteResponse} from "./Ambiente.response";
import {CategoriaResponse} from "./Categoria.response";
import {PrioridadeTarefa} from "../enums/PrioridadeTarefa.enum";
import {StatusTarefa} from "../enums/StatusTarefa.enum";

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

