import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { MatTableDataSource } from '@angular/material/table';
import { BlocoRequest } from '../../dtos/requests/bloco.request';

export interface Bloco {
  id: number;
  descricao: string;
  qtdAmbientes: number;
}

@Component({
  selector: 'app-tab-bloco',
  templateUrl: './tab-bloco.component.html',
  styleUrl: './tab-bloco.component.scss'
})
export class TabBlocoComponent implements OnInit {
  items: MenuItem[] | undefined;
  constructor(private bloco: BlocoRequest) {}
  dataSource = new MatTableDataSource<Bloco>([]);
  ngOnInit() {
    this.getBlocosData();
  }
  getBlocosData() {
    this.bloco.getBlocosData().subscribe((response: any) => {
      const data = this.extractContent(response);
      this.dataSource.data = data;
    });
  }
  extractContent(response: any): Bloco[] {
    return response.content || [];
  }
}
