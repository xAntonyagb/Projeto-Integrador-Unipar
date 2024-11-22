import {TipoArquivado} from "../enums/TipoArquivado.enum";
import { OrdemServicoResponse } from "./OrdemServico.response";
import { TarefaResponse } from "./Tarefa.response";
import {UsuarioResponse} from "./Usuario.response";

export interface ArquivadoResponse{
    id: number;
    tipo: TipoArquivado;
    ordemServico: OrdemServicoResponse;
    tarefa: TarefaResponse;
    dtExcluir: Date;
    dtArquivado: Date;
    arquivadoBy: UsuarioResponse;
  }

