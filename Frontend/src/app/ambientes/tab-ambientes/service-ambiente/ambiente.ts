export interface Usuario {
  username: string;
  dtCriacao: string;
  lastLogin: string;
  permissoes: string[]; // Ajuste conforme a estrutura de permissoes que tiver
}

export interface Bloco {
  descricao: string;
  qtdAmbientes:number;
}

export interface Ambiente {
  id: number;
  descricao: string;
  bloco: Bloco;
  qtdPatrimonios:number;
}
