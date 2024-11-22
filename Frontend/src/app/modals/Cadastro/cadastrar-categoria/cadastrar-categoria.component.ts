import { Component, EventEmitter, Output } from '@angular/core';
import { CategoriaRequest } from '../../../dtos/requests/categoria.request';
import { AddCategoria } from '../../../dtos/responses/categoria.response';

@Component({
  selector: 'app-cadastrar-categoria',
  templateUrl: './cadastrar-categoria.component.html',
  styleUrl: './cadastrar-categoria.component.scss'
})
export class CadastrarCategoriaComponent {
  descricaoCategoria: string[] = [];
  id:number = 0;
  descricao: string = '';

  constructor(private categoriaService: CategoriaRequest) {
    this.loadCategorias();
  }
  onSubmit( event: Event): void {
    if (!this.descricao || this.descricaoCategoria.includes(this.descricao)) {
      return;
    }
    this.adicionarCategoria();
    this.closeModal();
    this.reload();
  }
  loadCategorias() {
    this.categoriaService.getCategorias().subscribe((data: any[]) => {
      this.descricaoCategoria = data.map(bloco => bloco.descricao);  // Extrai apenas as descrições
    });
  }

  adicionarCategoria() {
    const categoria: AddCategoria = {id:this.id, descricao: this.descricao };
    this.categoriaService.setCategoria(categoria).subscribe(() => {
      this.descricao = '';
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

