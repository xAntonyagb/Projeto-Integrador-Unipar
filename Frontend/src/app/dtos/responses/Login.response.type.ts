import { UsuarioPermissoes } from '../enums/UsuarioPermissoes.enum';

export type LoginResponse = {
  acessToken: string;
  expiresIn: Date;
  createdAt: Date;
  permissoes: UsuarioPermissoes[];
  refreshToken: string;
};
