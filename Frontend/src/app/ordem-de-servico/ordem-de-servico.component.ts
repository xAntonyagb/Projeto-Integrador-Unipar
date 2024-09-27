import { Component } from '@angular/core';

@Component({
  selector: 'app-ordem-de-servico',
  templateUrl: './ordem-de-servico.component.html',
  styleUrls: ['./ordem-de-servico.component.scss']
})
export class OrdemDeServicoComponent {
  pageTitles: string[] = ['ORDEM DE SERVIÇO', 'TODOS OS SERVIÇOS'];
  currentPageTitle: string = this.pageTitles[0];

  updatePageTitle(index: number) {
    this.currentPageTitle = this.pageTitles[index];
  }
}
