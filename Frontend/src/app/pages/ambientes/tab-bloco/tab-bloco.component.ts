import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { MatTableDataSource } from '@angular/material/table';
import { BlocoService } from '../../../services/bloco.service';
import { BlocoResponse } from '../../../dtos/responses/bloco.response';
import { BlocoRequest } from '../../../dtos/requests/bloco.request';
import { ApiGenericToasts } from '../../../infra/api/api.generic-toasts';

@Component({
  selector: 'app-tab-bloco',
  templateUrl: './tab-bloco.component.html',
  styleUrl: './tab-bloco.component.scss',
})
export class TabBlocoComponent implements OnInit {
  items: MenuItem[] | undefined;
  dataSource = new MatTableDataSource<BlocoResponse>([]);
  isModalOpen = false;
  isEditModalOpen = false;
  blocoParaEditar: BlocoRequest | null = null;

  constructor(
    private bloco: BlocoService,
    private genericToast: ApiGenericToasts,
  ) {}

  ngOnInit() {
    this.loadBlocos();
  }

  openModal() {
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
    this.loadBlocos();
  }

  loadBlocos() {
    this.bloco.getAll().subscribe({
      next: (data) => {
        this.dataSource.data = data.content;
      },
      error: (e) => {
        if (e.status === 404) {
          this.dataSource.data = [];
        } else {
          if (e.status === 404) {
            this.dataSource.data = [];
          } else {
            this.genericToast.showErro(e);
          }
        }
      },
    });
  }

  deleteBloco(id: number) {
    if (confirm('Tem certeza que deseja excluir este bloco?')) {
      this.bloco.deleteById(id).subscribe({
        next: () => {
          this.genericToast.showSalvoSucesso(`Bloco`);
          this.loadBlocos();
        },
        error: (e) => {
          this.genericToast.showErro(e);
        },
      });
    }
  }

  openEditModal(bloco: BlocoResponse) {
    this.blocoParaEditar = this.toRequest(bloco);
    this.isEditModalOpen = true;
  }

  closeEditModal() {
    this.isEditModalOpen = false;
    this.blocoParaEditar = null;
    this.loadBlocos();
  }

  toRequest(bloco: BlocoResponse): BlocoRequest {
    let retorno: BlocoRequest = new BlocoRequest();
    retorno.id = bloco.id;
    retorno.descricao = bloco.descricao;

    return retorno;
  }
}
