import {List} from "@material/web/list/internal/list";

export interface UsuarioResponse {
    id: number;
    username: string;
    password : string;
    dtCriacao: Date;
    lastLogin: Date;
    permissoes: Array<string>;
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
