import { AfterViewInit, Component, inject, OnInit, ViewChild, viewChild } from '@angular/core';
import { Ambiente } from './service-ambiente/ambiente';
import {LiveAnnouncer} from '@angular/cdk/a11y';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort, Sort } from '@angular/material/sort';

const AMBIENTE_DATA: Ambiente[] = [
]
export interface Element {
  id: number;
  nome: string;
  bloco: string;
}
@Component({
  selector: 'app-tab-ambientes',
  templateUrl: './tab-ambientes.component.html',
  styleUrls: ['./tab-ambientes.component.scss']
})
export class TabAmbientesComponent  implements AfterViewInit{

  private _liveAnnouncer = inject(LiveAnnouncer);

  displayedColumns: string[] = ['id', 'nome', 'bloco', 'patrimonios'];
  dataSource = new MatTableDataSource(AMBIENTE_DATA);
  @ViewChild(MatSort) sort!: MatSort;

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
}
