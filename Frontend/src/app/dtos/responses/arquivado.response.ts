import { NotificacaoResponse } from "./notificacao.response";
import { OrdemResponse } from "./ordem.response";
import { TarefaResponse } from "./tarefa.response";

export interface ArquivadoResponse{
    id: number;
    tipo: string;
    ordemServico: OrdemResponse;
    tarefa: TarefaResponse;
    dtExcluir: Date;
    dtArquivado: Date;
    arquivadoBy: ArquivadoBy;
  }

  interface ArquivadoBy {
    id: string;
    username: string;
    password: string;
    dtRecord: string;
    dtLogin: string;
    notificacoes: NotificacaoResponse[];
    listRoles: ListRole[];
    enabled: string;
    authorities: Authority[];
    accountNonExpired: boolean;
    accountNonLocked: boolean;
    credentialsNonExpired: boolean;
  }

  interface Authority {
    authority: string;
  }

  interface ListRole {
    id: number;
    permissao: string;
  }

