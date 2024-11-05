import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { MatTableDataSource } from '@angular/material/table';
import { BlocoRequest } from '../../dtos/requests/bloco.request';
import { BlocoResponse } from '../../dtos/responses/bloco.response';

@Component({
  selector: 'app-tab-bloco',
  templateUrl: './tab-bloco.component.html',
  styleUrl: './tab-bloco.component.scss'
})
export class TabBlocoComponent implements OnInit {
  items: MenuItem[] | undefined;
  constructor(private bloco: BlocoRequest) {}
  dataSource = new MatTableDataSource<BlocoResponse>([]);
  isModalOpen = false;
  ngOnInit() {
    this.loadBlocos();
  }
  openModal(){
    this.isModalOpen = true;
  }
  closeModal(){
    this.isModalOpen = false;
  }
  loadBlocos() {
    this.bloco.getBlocosData().subscribe((response: any) => {
      const data = this.extractContent(response);
      this.dataSource.data = data;
    });
  }
  atualizarLista() {
    this.loadBlocos();
  }
  extractContent(response: any): BlocoResponse[] {
    return response.content || [];
  }
}
