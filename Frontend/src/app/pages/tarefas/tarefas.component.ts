import { Component } from '@angular/core';

@Component({
  selector: 'app-tarefas',
  templateUrl: './tarefas.component.html',
  styleUrls: ['./tarefas.component.scss'],
})
export class TarefasComponent {
  isModalOpen = false;

  openModal() {
    this.isModalOpen = true;
  }
  closeModal() {
    this.isModalOpen = false;
  }
}
