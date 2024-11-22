import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {CategoriaResponse} from "../../dtos/responses/Categoria.response";
import {CategoriaService} from "../../services/categoria.service";
import {CategoriaRequest} from "../../dtos/requests/Categoria.request";
import {ApiGenericToasts} from "../../infra/api/api.genericToasts";

@Component({
  selector: 'app-categoria',
  templateUrl: './categoria.component.html',
  styleUrl: './categoria.component.scss'
})
export class CategoriaComponent  implements OnInit {
  isModalOpen = false;
  isEditModalOpen = false;
  dataSource = new MatTableDataSource<CategoriaResponse>([]);
  categoriaParaEditar: CategoriaRequest | null = null;

  constructor(
    private categoria: CategoriaService,
    private genericToast: ApiGenericToasts
  ) {}

  ngOnInit() {
    this.loadCategorias();
  }

  loadCategorias() {
    this.categoria.getAll().subscribe({
      next: (data) => {
        this.dataSource.data = data.content;
      },
      error: (e) => {
        if(e.status === 404) {
          this.dataSource.data = [];
        } else {
          this.genericToast.showErro(e)
        }
      },
    });
  }

  openModal() {
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
    this.loadCategorias();
  }

  openEditModal(categoria: CategoriaResponse) {
    this.categoriaParaEditar = this.toRequest(categoria);
    this.isEditModalOpen = true;
  }

  closeEditModal() {
    this.isEditModalOpen = false;
    this.categoriaParaEditar = null;
    this.loadCategorias();
  }

  toRequest(categoria: CategoriaResponse): CategoriaRequest {
    let retorno: CategoriaRequest = new CategoriaRequest();
    retorno.descricao = categoria.descricao;
    retorno.id = categoria.id;

    return retorno;
  }

  calcularProgressoTarefas(categoria: CategoriaResponse): number {
    return (categoria.qtdTarefas / categoria.qtdTotalTarefas) * 100;
  }


  calcularProgressoServicos(categoria: CategoriaResponse): number {
    return (categoria.qtdServicos / categoria.qtdTotalServicos) * 100;
  }
}
