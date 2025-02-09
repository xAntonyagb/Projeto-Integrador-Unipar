import { StatusTarefa } from '../enums/StatusTarefa.enum';

export class TarefaRequest {
  id?: number;
  titulo!: string;
  descricao!: string;
  ambiente!: number;
  categoria!: number;
  previsao!: Date;
  prioridade!: string;
  status!: StatusTarefa;

  constructor() {}

  setValues(
    titulo: string,
    descricao: string,
    ambiente: number,
    categoria: number,
    previsao: Date,
    prioridade: string,
    status: StatusTarefa,
    id?: number,
  ) {
    this.id = id;
    this.titulo = titulo;
    this.descricao = descricao;
    this.ambiente = ambiente;
    this.categoria = categoria;
    this.previsao = previsao;
    this.prioridade = prioridade;
    this.status = status;
  }
}
