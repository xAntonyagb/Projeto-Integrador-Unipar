import {
  AfterViewInit,
  Component,
  inject,
  OnInit,
  ViewChild,
} from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { PatrimonioResponse } from '../../dtos/responses/Patrimonio.response';
import { PatrimonioService } from '../../services/patrimonio.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { ToastrService } from 'ngx-toastr';
import { LiveAnnouncer } from '@angular/cdk/a11y';
import { AmbienteService } from '../../services/ambiente.service';
import { AmbienteResponse } from '../../dtos/responses/Ambiente.response';
import { ApiGenericToasts } from '../../infra/api/api.genericToasts';

@Component({
  selector: 'app-patrimonio',
  templateUrl: './patrimonio.component.html',
  styleUrl: './patrimonio.component.scss',
})
export class PatrimonioComponent implements OnInit, AfterViewInit {
  private _liveAnnouncer = inject(LiveAnnouncer);

  isModalOpen = false;
  totalItems = 0;
  pageSize = 10;
  currentPage = 0;
  displayedColumns: string[] = ['patrimonio', 'descricao', 'ambiente'];
  dataSource = new MatTableDataSource<PatrimonioResponse>([]);
  ambientesDisponiveis: AmbienteResponse[] = [];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private patrimonioRequest: PatrimonioService,
    private toastr: ToastrService,
    private ambienteRequest: AmbienteService,
    private genericToast: ApiGenericToasts,
  ) {}

  ngOnInit() {
    this.loadPatrimonios();
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  announceSortChange(sortState: Sort) {
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }

  loadPatrimonios() {
    this.patrimonioRequest.getAll(this.currentPage, this.pageSize).subscribe({
      next: (response) => {
        this.totalItems = response.totalElements;
        this.dataSource.data = response.content;
      },

      error: (e) => {
        if (e.status === 404) {
          this.dataSource.data = [];
        } else {
          this.genericToast.showErro(e);
        }
      },
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  onPageChange(event: any) {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadPatrimonios();
  }

  openModal() {
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
    this.loadPatrimonios();
  }
}
