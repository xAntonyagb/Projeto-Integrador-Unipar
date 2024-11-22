import {Component, EventEmitter, Input, OnChanges, Output} from '@angular/core';
import {AddCategoria, CategoriaResponse} from "../../../dtos/responses/categoria.response";
import {CategoriaRequest} from "../../../dtos/requests/categoria.request";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-editar-categoria',
  templateUrl: './editar-categoria.component.html',
  styleUrl: './editar-categoria.component.scss'
})
export class EditarCategoriaComponent implements OnChanges {
  @Input() categoriaParaEditar: AddCategoria | null = null;
  descricao: string = '';
  dataSource = new MatTableDataSource<CategoriaResponse>([]);

  constructor(private categoriaService: CategoriaRequest) {}

  @Output() categoriaEditada = new EventEmitter<void>();
  @Output() close = new EventEmitter<void>();

  ngOnChanges() {
    if (this.categoriaParaEditar) {
      this.descricao = this.categoriaParaEditar.descricao;
    }
  }

  onSubmit(event: Event): void {
    if (!this.descricao || this.descricao === this.categoriaParaEditar?.descricao) {
      return;
    }
    this.editarCategoria();
    this.closeModal();
  }

  editarCategoria() {
    if (this.categoriaParaEditar) {
      const categoriaEditada: AddCategoria = {id:this.categoriaParaEditar.id, descricao: this.descricao };
      this.categoriaService.updateCategoria(this.categoriaParaEditar.id, categoriaEditada).subscribe(() => {
        this.descricao = '';
        this.categoriaEditada.emit();
      });
    }
  }
  closeModal() {
    this.close.emit();
  }
  loadCategorias() {
    this.categoriaService.getCategorias().subscribe((response: any) => {
      const data = this.extractContent(response);
      this.dataSource.data = data;
    });
  }

  extractContent(response: any): CategoriaResponse[] {
    return response.content || [];
  }
}
