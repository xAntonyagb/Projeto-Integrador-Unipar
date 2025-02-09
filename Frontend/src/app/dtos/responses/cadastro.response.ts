import { UsuarioPermissoes } from '../enums/UsuarioPermissoes.enum';

export interface UsuarioResponse {
  username: String;
  createdAt: Number;
  permissoes: UsuarioPermissoes[];
}
