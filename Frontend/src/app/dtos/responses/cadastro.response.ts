import { UsuarioPermissoes } from '../enums/usuario-permissoes.enum';

export interface UsuarioResponse {
  username: String;
  createdAt: Number;
  permissoes: UsuarioPermissoes[];
}
