export class BlocoRequest {
  id?: number;
  descricao!: string;

  constructor() {}

  setValues(descricao: string, id?: number) {
    this.id = id;
    this.descricao = descricao;
  }
}
