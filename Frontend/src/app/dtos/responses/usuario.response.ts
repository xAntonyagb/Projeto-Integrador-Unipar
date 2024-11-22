export interface UsuarioResponse {
    id: number;
    username: string;
    password : string;
    dtCriacao: Date;
    lastLogin: Date;
    permissoes: UsuarioPermissoes[] ;
  }
export enum UsuarioPermissoes {
  ADMINISTRADOR = 'ADMINISTRADOR',
  SUPER = 'SUPER',
  OPERADOR = 'OPERADOR'
}
  export interface AddUsuario{
    username: string;
    password : string;
    permissoes: UsuarioPermissoes[];
  }
