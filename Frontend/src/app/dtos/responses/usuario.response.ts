export interface UsuarioResponse {
    id: number;
    username: AddUsuario;
    password: AddUsuario;
    dtCriacao: Date;
    lastLogin: Date;
    permissoes: AddUsuario; 
  }
  export enum UsuarioPermissoes{
    SUPER=1,
    ADMINISTRADOR=2,
    OPERADOR=3
  }
  export interface AddUsuario{
    username: string;
    password : string;
    permissoes: UsuarioPermissoes[];
  }