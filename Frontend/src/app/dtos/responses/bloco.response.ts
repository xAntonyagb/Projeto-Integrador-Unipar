import { UsuarioResponse } from "./usuario.response";

export interface BlocoResponse {
    id: number
    descricao: AddBloco;
    qtdAmbientes:number;
    lastChange: Date;
    lastChangedBy : UsuarioResponse;
  }
export interface AddBloco{
  descricao: string;
}