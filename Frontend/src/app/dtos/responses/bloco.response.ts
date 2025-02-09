import { UsuarioResponse } from './Usuario.response';

export interface BlocoResponse {
  id: number;
  descricao: string;
  qtdAmbientes: number;
  lastChange: Date;
  lastChangedBy: UsuarioResponse;
}
