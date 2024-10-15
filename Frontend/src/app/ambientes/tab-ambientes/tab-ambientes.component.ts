import { AfterViewInit, Component, inject, OnInit, ViewChild, viewChild } from '@angular/core';
import {LiveAnnouncer} from '@angular/cdk/a11y';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort, Sort } from '@angular/material/sort';
import { AmbienteResponse } from '../../dtos/responses/ambiente.response';
import { AmbienteRequest } from '../../dtos/requests/ambiente.request';



export interface Element {
  id: number;
  nome: string;
  bloco: string;
  qtdPatrimonios: number;
}
@Component({
  selector: 'app-tab-ambientes',
  templateUrl: './tab-ambientes.component.html',
  styleUrls: ['./tab-ambientes.component.scss']
})
export class TabAmbientesComponent  implements AfterViewInit, OnInit{
  private _liveAnnouncer = inject(LiveAnnouncer);

  constructor(private ambiente: AmbienteRequest) { }
  displayedColumns: string[] = ['id', 'nome', 'bloco', 'patrimonios'];
  dataSource = new MatTableDataSource<AmbienteResponse>([]);
  @ViewChild(MatSort) sort!: MatSort;
  ngOnInit() {
    this.getAmbientes();
    
  }
  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }
  announceSortChange(sortState: Sort) {
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }
  getAmbientes() {
    this.ambiente.getAmbientes().subscribe((response: any) => {
      const data = this.extractContent(response);
      this.dataSource.data = data;
    });
  }

  extractContent(response: any): AmbienteResponse[] {
    return response.content || [];
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
 
}
