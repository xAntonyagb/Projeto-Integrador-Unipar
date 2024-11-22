import { AfterViewInit, Component, inject, OnInit, ViewChild} from '@angular/core';
import {LiveAnnouncer} from '@angular/cdk/a11y';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort, Sort } from '@angular/material/sort';
import { AmbienteResponse } from '../../../dtos/responses/Ambiente.response';
import { AmbienteService } from '../../../services/ambiente.service';
import {MatPaginator} from "@angular/material/paginator";
import {PaginacaoResponse} from "../../../dtos/responses/Paginacao.response";
import {ToastrService} from "ngx-toastr";
import {ApiGenericToasts} from "../../../infra/api/api.genericToasts";

@Component({
  selector: 'app-tab-ambientes',
  templateUrl: './tab-ambientes.component.html',
  styleUrls: ['./tab-ambientes.component.scss']
})
export class TabAmbientesComponent  implements AfterViewInit, OnInit {

  private _liveAnnouncer = inject(LiveAnnouncer);

  isModalOpen = false;
  totalItems: number = 0;
  pageSize: number = 5;
  currentPage: number = 0;
  totalPages: number = 0;
  displayedColumns: string[] = ['id', 'nome', 'bloco', 'patrimonios'];
  dataSource = new MatTableDataSource<AmbienteResponse>([]);

  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private ambiente: AmbienteService,
    private genericToast: ApiGenericToasts
  ) {
  }

  openModal() {
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
    this.loadAmbientes();
  }

  ngOnInit() {
    this.loadAmbientes();

  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

  loadAmbientes() {
    this.ambiente.getAll(this.currentPage, this.pageSize).subscribe({
      next: (response: PaginacaoResponse<AmbienteResponse>) => {
        this.totalItems = response.totalElements;
        this.dataSource.data = response.content;
      },
      error: (e) => {
        if(e.status === 404) {
          this.dataSource.data = [];
        } else {
          this.genericToast.showErro(e)
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
    this.loadAmbientes();
  }

  deleteAmbiente(id: number) {
    if (confirm('Tem certeza que deseja excluir este ambiente?')) {
      this.ambiente.deleteById(id).subscribe({
        next: () => {
          this.genericToast.showSalvoSucesso(`Ambiente`);
          this.loadAmbientes();
        },
        error: (e) => {
          this.genericToast.showErro(e)
        },
      });
    }
  }

}
