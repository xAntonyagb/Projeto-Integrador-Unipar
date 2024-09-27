export interface Usuario {
  username: string;
  dtCriacao: string;
  lastLogin: string;
  permissoes: string[]; // Ajuste conforme a estrutura de permissoes que tiver
}

export interface Bloco {
  id: number;
  descricao: string;
  lastChange: string;
  lastChangedBy: Usuario;
}

export interface Ambiente {
  id: number;
  descricao: string;
  bloco: Bloco;
}
