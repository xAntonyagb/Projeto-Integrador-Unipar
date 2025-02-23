import { UsuarioPermissoes } from '../enums/usuario-permissoes.enum';

export class CadastroRequest {
  username!: string;
  password!: string;
  permissoes!: UsuarioPermissoes[];

  constructor() {}

  setValues(
    username: string,
    password: string,
    permissoes: UsuarioPermissoes[],
  ) {
    this.username = username;
    this.password = password;
    this.permissoes = permissoes;
  }
}
