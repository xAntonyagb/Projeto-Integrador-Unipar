import {List} from "@material/web/list/internal/list";

export interface UsuarioResponse {
    id: number;
    username: AddUsuario;
    password: AddUsuario;
    dtCriacao: Date;
    lastLogin: Date;
    permissoes: AddUsuario;
  }
export interface UsuarioPermissoes {
  ADMINISTRADOR: 'ADMINISTRADOR';
  SUPER: 'SUPER';
  OPERADOR: 'OPERADOR';
}
  export interface AddUsuario{
    username: string;
    password : string;
    permissoes: Array<string>;
  }
