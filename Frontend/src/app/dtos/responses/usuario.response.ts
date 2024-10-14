export interface UsuarioResponse {
    id: number;
    username: string;
    dtCriacao: Date;
    lastLogin: Date;
    permissoes: string[]; // Ajuste conforme a estrutura de permissoes que tiver
  }