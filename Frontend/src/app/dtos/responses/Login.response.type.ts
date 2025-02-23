import { UsuarioPermissoes } from '../enums/usuario-permissoes.enum';

export type LoginResponse = {
  acessToken: string;
  expiresIn: Date;
  createdAt: Date;
  permissoes: UsuarioPermissoes[];
  refreshToken: string;
};
