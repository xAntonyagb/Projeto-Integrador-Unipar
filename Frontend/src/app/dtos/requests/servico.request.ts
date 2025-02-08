export class ServicoRequest {
  id?: number | any;
  patrimonio?: number | any;
  descricao?: string | any;
  quantidade?: number | any;
  valorUnit?: number | any;
  categoria?: number | any;
  ambiente?: number | any;

  constructor() {}

  setValues(
    id?: number | any,
    patrimonio?: number | any,
    descricao?: string | any,
    quantidade?: number | any,
    valorUnit?: number | any,
    categoria?: number | any,
    ambiente?: number | any
  ) {
    this.id = id;
    this.patrimonio = patrimonio;
    this.descricao = descricao;
    this.quantidade = quantidade;
    this.valorUnit = valorUnit;
    this.categoria = categoria;
    this.ambiente = ambiente;
  }
}