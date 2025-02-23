import { TipoNotificacao } from '../enums/tipo-notificacao.enum';

export interface NotificacaoResponse {
  id: Number;
  tipo: TipoNotificacao;
  titulo: String;
  descricao: String;
  dtEnvio: Date;
}
