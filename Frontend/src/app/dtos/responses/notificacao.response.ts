import { TipoNotificacao } from '../enums/TipoNotificacao.enum';

export interface NotificacaoResponse {
  id: Number;
  tipo: TipoNotificacao;
  titulo: String;
  descricao: String;
  dtEnvio: Date;
}
