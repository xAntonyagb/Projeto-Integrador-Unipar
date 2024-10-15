import { AmbienteResponse } from "./ambiente.response";
import { CategoriaResponse } from "./categoria.response";

export interface ServicoResponse {
    id :number;
    patrimonio:string;
    descricao:string;
    quantidade:number;
    valorUnit:number;
    valorTotal:number
    categoria:CategoriaResponse;
    ambiente:AmbienteResponse;
  }