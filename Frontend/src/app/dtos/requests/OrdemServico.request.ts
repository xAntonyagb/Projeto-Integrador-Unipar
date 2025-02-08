import {ServicoRequest} from "./Servico.request";

export class OrdemServicoRequest {
  id?: number;
  descricao?: string;
  data?: Date | string;
  servicos?: ServicoRequest[];

  constructor() {}

  setValues(id?: number, descricao?: string, data?: Date, servicos?: ServicoRequest[]) {
    this.id = id;
    this.descricao = descricao;
    this.data = data;
    this.servicos = servicos;
  }
}