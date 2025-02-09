import { Component, EventEmitter, Output } from '@angular/core';
import { BlocoService } from '../../../services/bloco.service';
import { BlocoResponse } from '../../../dtos/responses/Bloco.response';
import { BlocoRequest } from '../../../dtos/requests/Bloco.request';
import { ApiGenericToasts } from '../../../infra/api/api.genericToasts';

@Component({
  selector: 'app-cadastrar-bloco',
  templateUrl: './cadastrar-bloco.component.html',
  styleUrl: './cadastrar-bloco.component.scss',
})
export class CadastrarBlocoComponent {
  blocosDescricao: string[] = [];
  id: number = 0;
  descricao: string = '';

  constructor(
    private blocoService: BlocoService,
    private genericToast: ApiGenericToasts,
  ) {
    this.loadBlocos();
  }

  @Output() blocoAdicionado = new EventEmitter<void>();
  onSubmit(event: Event): void {
    if (!this.descricao || this.blocosDescricao.includes(this.descricao)) {
      return;
    }
    this.adicionarBloco();
    this.closeModal();
  }

  loadBlocos() {
    this.blocoService.getAll().subscribe({
      next: (data) => {
        this.blocosDescricao = data.content.map((bloco) => bloco.descricao);
      },
      error: (e) => {
        this.genericToast.showErro(e);
      },
    });
  }

  adicionarBloco() {
    let bloco: BlocoRequest = new BlocoRequest();
    bloco.setValues(this.descricao, this.id);

    this.blocoService.save(bloco).subscribe({
      complete: () => {
        this.genericToast.showSalvoSucesso(`Bloco`);
      },
      error: (e) => {
        this.genericToast.showErro(e);
      },
    });

    this.descricao = '';
    this.blocoAdicionado.emit();
  }

  @Output() close = new EventEmitter<void>();
  closeModal() {
    this.close.emit();
  }
}
