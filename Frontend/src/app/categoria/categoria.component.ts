import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {AddCategoria, CategoriaResponse} from "../dtos/responses/categoria.response";
import {CategoriaRequest} from "../dtos/requests/categoria.request";

@Component({
  selector: 'app-categoria',
  templateUrl: './categoria.component.html',
  styleUrl: './categoria.component.scss'
})
export class CategoriaComponent  implements OnInit {
  isModalOpen = false;
  isEditModalOpen = false;
  dataSource = new MatTableDataSource<CategoriaResponse>([]);
  categoriaParaEditar: AddCategoria | null = null;

  constructor(private categoria: CategoriaRequest) {}

  ngOnInit() {
    this.loadCategorias();
  }

  loadCategorias() {
    this.categoria.getCategorias().subscribe((response: any) => {
      const data = this.extractContent(response);
      this.dataSource.data = data;
    });
  }

  extractContent(response: any): CategoriaResponse[] {
    return response.content || [];
  }
  openModal() {
    this.isModalOpen = true;
  }
  closeModal() {
    this.isModalOpen = false;
  }
  atualizarLista() {
    this.loadCategorias();
  }
  openEditModal(categoria: CategoriaResponse) {
    this.categoriaParaEditar = this.convertCategoriaResponseToAddCategoria(categoria);
    this.isEditModalOpen = true;
  }
  convertCategoriaResponseToAddCategoria(categoria: CategoriaResponse): AddCategoria {
    return {
      id: categoria.id,
      descricao: categoria.descricao
    };
  }

  closeEditModal() {
    this.isEditModalOpen = false;
    this.categoriaParaEditar = null;
  }

  calcularProgressoTarefas(categoria: CategoriaResponse): number {
    return (categoria.qtdTarefas / categoria.qtdTotalTarefas) * 100;
  }


  calcularProgressoServicos(categoria: CategoriaResponse): number {
    return (categoria.qtdServicos / categoria.qtdTotalServicos) * 100;
  }
}
