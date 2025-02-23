import { UsuarioPermissoes } from '../enums/usuario-permissoes.enum';

export interface UsuarioResponse {
  id: String;
  username: String;
  password: String;
  dtCriacao: Date;
  lastLogin: Date;
  permissoes: UsuarioPermissoes[];
}
