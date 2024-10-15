import { Component } from '@angular/core';
import { DatePipe } from '@angular/common';
import { OrdemRequest } from '../../dtos/requests/ordem.request';
import { CadastrarOrdemModule } from '../../modals/cadastrar-ordem/cadastrar-ordem.module';
import { CadastrarOrdemComponent } from '../../modals/cadastrar-ordem/cadastrar-ordem.component';
import { ModalRequest } from '../../dtos/requests/modal.request';

@Component({
  selector: 'app-tab-ordem-de-servico',
  templateUrl: './tab-ordem-de-servico.component.html',
  styleUrls: ['./tab-ordem-de-servico.component.scss'],
  providers:[DatePipe]
})
export class TabOrdemDeServicoComponent {
  isModalOpen = false;
  ordensServico: any[] = [];

  constructor(
    private ordem: OrdemRequest,
    private datePipe: DatePipe,
    private modalRequest: ModalRequest
  ) {}
openModal(){
  this.isModalOpen = true;
}
closeModal(){
  this.isModalOpen = false;
}

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
