import {UsuarioPermissoes} from "../enums/UsuarioPermissoes.enum";

export class UsuarioRequest {
  username!: string;
  password!: string;
  permissoes!: UsuarioPermissoes[];

  constructor() {}

  setValues(username: string, password: string, permissoes: UsuarioPermissoes[]) {
    this.username = username;
    this.password = password;
    this.permissoes = permissoes;
  }
}