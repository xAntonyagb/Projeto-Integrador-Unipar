import { Component } from '@angular/core';
import { OrdemServicoService } from '../service-ordem/ordem.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-tab-ordem-de-servico',
  templateUrl: './tab-ordem-de-servico.component.html',
  styleUrls: ['./tab-ordem-de-servico.component.scss'],
  providers:[DatePipe]
})
export class TabOrdemDeServicoComponent {
  ordensServico: any[] = [];

  constructor(private ordemServicoService: OrdemServicoService, private datePipe: DatePipe) {}

  ngOnInit() {
    this.getOrdensServico();
  }

  getOrdensServico() {
    this.ordemServicoService.getOrdensServico().subscribe(response => {
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
