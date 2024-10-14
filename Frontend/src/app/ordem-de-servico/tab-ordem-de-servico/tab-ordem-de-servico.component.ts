import { Component } from '@angular/core';
import { DatePipe } from '@angular/common';
import { OrdemRequest } from '../../dtos/requests/ordem.request';

@Component({
  selector: 'app-tab-ordem-de-servico',
  templateUrl: './tab-ordem-de-servico.component.html',
  styleUrls: ['./tab-ordem-de-servico.component.scss'],
  providers:[DatePipe]
})
export class TabOrdemDeServicoComponent {
  ordensServico: any[] = [];

  constructor(private ordem: OrdemRequest, private datePipe: DatePipe) {}

  ngOnInit() {
    this.getOrdensServico();
  }

  getOrdensServico() {
    this.ordem.getOrdensServico().subscribe(response => {
      this.ordensServico = response.content.map((ordem: any) => ({
        id: ordem.id,
        descricao: ordem.descricao,
        data: this.datePipe.transform(ordem.data, 'dd/MM/yyyy'),
        qtdServicos: ordem.qtdServicos,
        valorTotal: ordem.valorTotal,
        status: ordem.status
      }));
    });
  }
}
