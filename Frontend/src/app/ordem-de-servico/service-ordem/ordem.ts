export interface Ordem {
  id: number;
  descricao: string;
  data : Date;
  servicos:Servicos;
  qtdServicos:number;
  valorTotal: number;
  status:string;
}
export interface Servicos {
  id :number;
  patrimonio:string;
  descricao:string;
  quantidade:number;
  valorUnit:number;
  categoria:number;
  ambiente:number;
}
