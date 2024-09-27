import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { AmbienteService } from './service-ambiente/ambiente-service';
import { Ambiente } from './service-ambiente/ambiente';

@Component({
  selector: 'app-tab-ambientes',
  templateUrl: './tab-ambientes.component.html',
  styleUrl: './tab-ambientes.component.scss'
})
export class TabAmbientesComponent  implements OnInit{
  items: MenuItem[] | undefined;
  ambientes : Ambiente[] = []

  constructor(private ambienteService: AmbienteService) {}
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
      this.ambienteService.getAmbientes().subscribe(
        data => {
          this.ambientes = data;
      },
      error => {
          console.log('Erro ao consultar Ambientes'
            ,error);
      }

    );
  }
}
