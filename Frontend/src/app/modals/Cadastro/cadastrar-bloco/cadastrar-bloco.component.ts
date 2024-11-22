import { Component, EventEmitter, Output } from '@angular/core';
import { BlocoRequest } from '../../../dtos/requests/bloco.request';
import { AddBloco, BlocoResponse } from '../../../dtos/responses/bloco.response';

@Component({
  selector: 'app-cadastrar-bloco',
  templateUrl: './cadastrar-bloco.component.html',
  styleUrl: './cadastrar-bloco.component.scss'
})
export class CadastrarBlocoComponent {
  blocosDescricao: string[] = [];
  id:number = 0;
  descricao: string = '';

  constructor(private blocoService: BlocoRequest) {
    this.loadBlocos();
  }
  @Output() blocoAdicionado = new EventEmitter<void>();
  onSubmit( event: Event): void {
    if (!this.descricao || this.blocosDescricao.includes(this.descricao)) {
      return;
    }
    this.adicionarBloco();
    this.closeModal();
  }
  loadBlocos() {
    this.blocoService.getBlocosData().subscribe((data: any[]) => {
      this.blocosDescricao = data.map(bloco => bloco.descricao);  // Extrai apenas as descrições
    });
  }

  adicionarBloco() {
    const bloco: AddBloco = {id:this.id, descricao: this.descricao };
    this.blocoService.setBloco(bloco).subscribe(() => {
      this.descricao = '';
      this.blocoAdicionado.emit();
    });
  }
  @Output() close = new EventEmitter<void>();
  closeModal() {
    this.close.emit();
  }
}
