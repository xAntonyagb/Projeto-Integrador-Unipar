import { TipoArquivado } from '../enums/tipo-arquivado.enum';
import { OrdemServicoResponse } from './ordem-servico.response';
import { TarefaResponse } from './tarefa.response';
import { UsuarioResponse } from './usuario.response';

export interface ArquivadoResponse {
  id: number;
  tipo: TipoArquivado;
  ordemServico: OrdemServicoResponse;
  tarefa: TarefaResponse;
  dtExcluir: Date;
  dtArquivado: Date;
  arquivadoBy: UsuarioResponse;
}
