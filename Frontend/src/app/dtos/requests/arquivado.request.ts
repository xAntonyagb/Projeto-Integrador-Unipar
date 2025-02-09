import { TipoArquivado } from '../enums/TipoArquivado.enum';

export class ArquivadoRequest {
  id?: number;
  tipo!: TipoArquivado;
  ordemServico?: number;
  tarefa?: number;

  constructor() {}

  setValues(
    tipo: TipoArquivado,
    id?: number,
    ordemServico?: number,
    tarefa?: number,
  ) {
    this.id = id;
    this.tipo = tipo;
    this.ordemServico = ordemServico;
    this.tarefa = tarefa;
  }
}
