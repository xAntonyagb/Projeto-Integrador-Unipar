import { Component } from '@angular/core';
import { OrdemService } from '../../services/ordem.service';
import { ModalService } from '../../services/modal.service';

@Component({
  selector: 'app-ordem-de-servico',
  templateUrl: './ordem-de-servico.component.html',
  styleUrls: ['./ordem-de-servico.component.scss'],
})
export class OrdemDeServicoComponent {
  pageTitles: string[] = ['ORDEM DE SERVIÇO', 'TODOS OS SERVIÇOS'];
  currentPageTitle: string = this.pageTitles[0];
  isModalOpen = false;

  constructor (
    private modalService: ModalService
  ) {}

  ngOnInit() {
    this.modalService.isOpen$.subscribe((state) => {
      this.isModalOpen = state;
    });
  }

  updatePageTitle(index: number) {
    this.currentPageTitle = this.pageTitles[index];
  }

  openModal() {
    this.modalService.openModal();
  }
  closeModal() {
    this.modalService.closeModal();
  }

}
