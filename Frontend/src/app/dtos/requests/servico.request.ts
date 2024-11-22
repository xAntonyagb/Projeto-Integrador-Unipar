export class ServicoRequest {
  id?: number;
  patrimonio?: number;
  quantidade?: number;
  valorUnit?: number;
  categoria?: number;
  ambiente?: number;

  constructor() {}

  setValues(
    id?: number,
    patrimonio?: number,
    quantidade?: number,
    valorUnit?: number,
    categoria?: number,
    ambiente?: number
  ) {
    this.id = id;
    this.patrimonio = patrimonio;
    this.quantidade = quantidade;
    this.valorUnit = valorUnit;
    this.categoria = categoria;
    this.ambiente = ambiente;
  }
}