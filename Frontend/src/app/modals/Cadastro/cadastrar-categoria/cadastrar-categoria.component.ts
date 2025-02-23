import { Component, EventEmitter, Output } from '@angular/core';
import { CategoriaService } from '../../../services/categoria.service';
 
import { CategoriaRequest } from '../../../dtos/requests/categoria.request';
import { ApiGenericToasts } from '../../../infra/api/api.generic-toasts';

@Component({
  selector: 'app-cadastrar-categoria',
  templateUrl: './cadastrar-categoria.component.html',
  styleUrl: './cadastrar-categoria.component.scss',
})
export class CadastrarCategoriaComponent {
  descricaoCategoria: string[] = [];
  id: number = 0;
  descricao: string = '';

  constructor(
    private categoriaService: CategoriaService,
    private genericToast: ApiGenericToasts,
  ) {
    this.loadCategorias();
  }
  onSubmit(event: Event): void {
    if (!this.descricao || this.descricaoCategoria.includes(this.descricao)) {
      return;
    }
    this.adicionarCategoria();
    this.closeModal();
    this.reload();
  }

  loadCategorias() {
    this.categoriaService.getAll().subscribe({
      next: (data) => {
        this.descricaoCategoria = data.content.map((bloco) => bloco.descricao);
      },
      error: (e) => {
        this.genericToast.showErro(e);
      },
    });
  }

  adicionarCategoria() {
    let categoria: CategoriaRequest = new CategoriaRequest();
    categoria.setValues(this.descricao, this.id);

    this.categoriaService.save(categoria).subscribe({
      complete: () => {
        this.genericToast.showSalvoSucesso(`Categoria`);
      },
      error: (e) => {
        this.genericToast.showErro(e);
      },
    });
  }

  @Output() close = new EventEmitter<void>();
  closeModal() {
    this.close.emit();
  }
  reload() {
    this.loadCategorias();
  }
}
