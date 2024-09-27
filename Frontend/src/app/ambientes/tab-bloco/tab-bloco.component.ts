import { Component, OnInit } from '@angular/core';
import { BlocoService } from './bloco-service/bloco.service';
import { MenuItem } from 'primeng/api';

interface Bloco {
  id: number;
  nome: string;
  quantidadeAmbientes: number;
}

@Component({
  selector: 'app-tab-bloco',
  templateUrl: './tab-bloco.component.html',
  styleUrl: './tab-bloco.component.scss'
})
export class TabBlocoComponent implements OnInit {
  blocos: Bloco[] = [];
  items: MenuItem[] | undefined;
  constructor(private blocoService: BlocoService) {}

  ngOnInit() {
    this.items = [
      {
          label: 'Options',
          items: [
              {
                  label: 'Refresh',
                  icon: 'pi pi-refresh'
              },
              {
                  label: 'Export',
                  icon: 'pi pi-upload'
              }
          ]
      }
  ];
    this.blocoService.getBlocosData().subscribe(data => {
      this.blocos = data;
    });
  }
}
