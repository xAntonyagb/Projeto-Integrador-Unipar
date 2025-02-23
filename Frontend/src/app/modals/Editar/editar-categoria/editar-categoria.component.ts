import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  Output,
} from '@angular/core';
import { CategoriaResponse } from '../../../dtos/responses/categoria.response';
import { CategoriaService } from '../../../services/categoria.service';
import { MatTableDataSource } from '@angular/material/table';
import { CategoriaRequest } from '../../../dtos/requests/categoria.request';
import { ApiGenericToasts } from '../../../infra/api/api.generic-toasts';

@Component({
  selector: 'app-editar-categoria',
  templateUrl: './editar-categoria.component.html',
  styleUrl: './editar-categoria.component.scss',
})
export class EditarCategoriaComponent implements OnChanges {
  @Input() categoriaParaEditar: CategoriaRequest | null = null;
  descricao: string = '';
  dataSource = new MatTableDataSource<CategoriaResponse>([]);

  constructor(
    private categoriaService: CategoriaService,
    private genericToast: ApiGenericToasts,
  ) {}

  @Output() categoriaEditada = new EventEmitter<void>();
  @Output() close = new EventEmitter<void>();

  ngOnChanges() {
    if (this.categoriaParaEditar) {
      this.descricao = this.categoriaParaEditar.descricao;
    }
  }

  onSubmit(event: Event): void {
    if (
      !this.descricao ||
      this.descricao === this.categoriaParaEditar?.descricao
    ) {
      return;
    }
    this.editarCategoria();
    this.closeModal();
  }

  editarCategoria() {
    if (!this.categoriaParaEditar) {
      return;
    }

    let categoria: CategoriaRequest = new CategoriaRequest();
    categoria.id = this.categoriaParaEditar.id;
    categoria.descricao = this.descricao;

    this.categoriaService.save(categoria).subscribe({
      complete: () => {
        this.genericToast.showSalvoSucesso(`Categoria`);
      },
      error: (e) => {
        this.genericToast.showErro(e);
      },
    });
  }

  closeModal() {
    this.close.emit();
  }

  loadCategorias() {
    this.categoriaService.getAll().subscribe((response: any) => {
      const data = this.extractContent(response);
      this.dataSource.data = data;
    });
  }

  extractContent(response: any): CategoriaResponse[] {
    return response.content || [];
  }
}
