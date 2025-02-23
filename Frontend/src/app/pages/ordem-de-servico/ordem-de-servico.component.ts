import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-ordem-de-servico',
  templateUrl: './ordem-de-servico.component.html',
  styleUrls: ['./ordem-de-servico.component.scss'],
})
export class OrdemDeServicoComponent {
  pageTitles: string[] = ['ORDEM DE SERVIÇO', 'TODOS OS SERVIÇOS'];
  currentPageTitle: string = this.pageTitles[0];
  isModalOpen = false;

  @Output() close = new EventEmitter<void>();

  updatePageTitle(index: number) {
    this.currentPageTitle = this.pageTitles[index];
  }

  onActivate(componentInstance: any) {
    if (componentInstance.open) {
      componentInstance.open.subscribe(() => this.openModal());
    }
    if (componentInstance.close) {
      componentInstance.close.subscribe(() => this.closeModal());
    }
  }

  openModal() {
    this.isModalOpen = true;
  }
  closeModal() {
    this.isModalOpen = false;
    this.close.emit();
  }

}
