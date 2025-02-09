export class PatrimonioRequest {
  patrimonio!: number;
  descricao?: string;
  ambiente?: number;

  constructor() {}

  setValues(patrimonio: number, descricao?: string, ambiente?: number) {
    this.patrimonio = patrimonio;
    this.descricao = descricao;
    this.ambiente = ambiente;
  }
}
