import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { MatTableDataSource } from '@angular/material/table';
import { BlocoRequest } from '../../dtos/requests/bloco.request';
import {AddBloco, BlocoResponse} from '../../dtos/responses/bloco.response';

@Component({
  selector: 'app-tab-bloco',
  templateUrl: './tab-bloco.component.html',
  styleUrl: './tab-bloco.component.scss'
})
export class TabBlocoComponent implements OnInit {
  items: MenuItem[] | undefined;
  dataSource = new MatTableDataSource<BlocoResponse>([]);
  isModalOpen = false;
  isEditModalOpen = false;
  blocoParaEditar: AddBloco | null = null;

  constructor(
    private bloco: BlocoRequest
  ) {
  }

  ngOnInit() {
    this.loadBlocos();
  }

  openModal() {
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
  }

  loadBlocos() {
    this.bloco.getBlocosData().subscribe((response: any) => {
      const data = this.extractContent(response);
      this.dataSource.data = data;
    });
  }

  openEditModal(bloco: BlocoResponse) {
    this.blocoParaEditar = this.convertBlocoResponseToAddBloco(bloco);
    this.isEditModalOpen = true;
  }

  closeEditModal() {
    this.isEditModalOpen = false;
    this.blocoParaEditar = null;
    this.loadBlocos();
  }

  atualizarLista() {
    this.loadBlocos();
  }

  extractContent(response: any): BlocoResponse[] {
    return response.content || [];
  }

  convertBlocoResponseToAddBloco(bloco: BlocoResponse): AddBloco {
    return {
      id: bloco.id,
      descricao: bloco.descricao
    };
  }
}
