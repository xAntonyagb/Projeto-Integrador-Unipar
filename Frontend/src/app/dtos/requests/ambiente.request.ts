export class AmbienteRequest {
  id?: number;
  descricao!: string;
  patrimonios?: number[];
  bloco!: number;

  constructor() {}

  setValues(
    descricao: string,
    bloco: number,
    id?: number,
    patrimonios?: number[],
  ) {
    this.id = id;
    this.descricao = descricao;
    this.patrimonios = patrimonios;
    this.bloco = bloco;
  }
}
