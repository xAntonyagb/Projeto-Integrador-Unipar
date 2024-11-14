import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {BlocoResponse} from "../dtos/responses/bloco.response";
import {CategoriaResponse} from "../dtos/responses/categoria.response";
import {CategoriaRequest} from "../dtos/requests/categoria.request";

@Component({
  selector: 'app-categoria',
  templateUrl: './categoria.component.html',
  styleUrl: './categoria.component.scss'
})
export class CategoriaComponent  implements OnInit {
  isModalOpen = false;
  dataSource = new MatTableDataSource<CategoriaResponse>([]);
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
  atualizarLista() {
    this.loadCategorias();
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

  calcularProgressoTarefas(categoria: CategoriaResponse): number {
    return (categoria.qtdTarefas / categoria.qtdTotalTarefas) * 100;
  }


  calcularProgressoServicos(categoria: CategoriaResponse): number {
    return (categoria.qtdServicos / categoria.qtdTotalServicos) * 100;
  }
}
