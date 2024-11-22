import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import { DatePipe } from '@angular/common';
import { OrdemRequest } from '../../dtos/requests/ordem.request';
import {ToastrService} from "ngx-toastr";
import {MatTableDataSource} from "@angular/material/table";
import { MatSort } from '@angular/material/sort';
import {catchError} from "rxjs/operators";
import {of} from "rxjs";
import {StatusOrdem} from "../../dtos/responses/ordem.response";
import {FormBuilder, FormGroup} from "@angular/forms";


@Component({
  selector: 'app-tab-ordem-de-servico',
  templateUrl: './tab-ordem-de-servico.component.html',
  styleUrls: ['./tab-ordem-de-servico.component.scss'],
  providers:[DatePipe]
})
export class TabOrdemDeServicoComponent implements OnInit, AfterViewInit {
  @ViewChild(MatSort) sort: MatSort | undefined;

  isModalOpen = false;
  showFilters =false;
  filters = {data:'', status:''};
  displayedColumns: string[] = ['id', 'descricao', 'qtdServicos', 'valorTotal', 'data', 'status','arquivar'];
  dataSource = new MatTableDataSource<any>([]);
  ordensServico: any[] = [];
  totalPages: number = 0;
  currentPage: number = 0;
  pageSize: number = 10;
  statusList: { key: string; value: string }[] = [];
  filtersForm: FormGroup;

  constructor(
    private ordem: OrdemRequest,
    private datePipe: DatePipe,
    private toastr: ToastrService,
    private fb: FormBuilder
  ) {
    this.filtersForm = this.fb.group({
      descricao: [''],
      servico: [''],
      valorTotal: [''],
      arquivada: [''],
      sort: ['']
    });
  }

  openModal() {
    this.isModalOpen = true;
  }
  closeModal(){
  this.isModalOpen = false;
}

  ngOnInit() {
    this.getOrdensServico();
  }
  ngAfterViewInit() {
    if (this.sort) {
      this.dataSource.sort = this.sort;
    }
  }
  toggleFilters() {
    this.showFilters = !this.showFilters;
  }
  /*filtrarOrdens() {
    const descricao = this.formFiltro.value.descricao;
    const servico = this.formFiltro.value.servico;
    const valorTotal = this.formFiltro.value.valorTotal;
    const arquivada = this.formFiltro.value.arquivada;
    const sort = this.formFiltro.value.sort;

    this.ordemRequest
      .getOrdensServico(
        this.paginaAtual,
        this.tamanhoPagina,
        descricao,
        servico,
        valorTotal,
        arquivada,
        sort
      )
      .subscribe({
        next: (response) => {
          this.ordens = response.content; // Atualize a lista de ordens no componente
          this.totalItens = response.totalElements;
        },
        error: (error) => {
          console.error('Erro ao filtrar ordens:', error);
        },
      });
  }*/
  getOrdensServico() {
    const params = {
      page: this.currentPage,
      size: this.pageSize,
      sort: this.sort?.active + ',' + this.sort?.direction || 'id,asc',
      ...this.filters
    };
    this.ordem.getOrdensServico(params.page, params.size).subscribe(response => {
      this.ordensServico = response.content.map((ordem: any) => ({
        id: ordem.id,
        descricao: ordem.descricao,
        data: this.datePipe.transform(ordem.data, 'dd/MM/yyyy'),
        qtdServicos: ordem.qtdServicos,
        valorTotal: ordem.valorTotal,
        status: ordem.status
      }));
      this.dataSource.data = this.ordensServico;
      this.totalPages = response.totalPages;
    });
  }


  arquivarOrdem(id: number) {
    this.ordem.arquivarOrdem(id).pipe(
      catchError((error) => {
        this.toastr.error('Erro ao arquivar ordem');
        return of(null);
      })
    ).subscribe(() => {
      this.toastr.success('Ordem arquivada com sucesso');
    });
    this.getOrdensServico();
  }
 /* applyFilters() {

      const params = {
        page: this.currentPage,
        size: this.pageSize,
        descricao: this.filtersForm.value.descricao,
        servico: this.filtersForm.value.servico,
        valorTotal: this.filtersForm.value.valorTotal,
        arquivada: this.filtersForm.value.arquivada,
        sort: this.filtersForm.value.sort
      };
    this.ordem.getOrdensServico(
      params.page,
      params.size,
      params.data,
      params.status
    ).subscribe(response => {
      this.ordensServico = response.content.map((ordem: any) => ({
        id: ordem.id,
        descricao: ordem.descricao,
        data: this.datePipe.transform(ordem.data, 'dd/MM/yyyy'),
        qtdServicos: ordem.qtdServicos,
        valorTotal: ordem.valorTotal,
        status: ordem.status
      }));
      this.dataSource.data = this.ordensServico;
      this.totalPages = response.totalPages;
    });
  }*/
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  onPageChange(event: any) {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.getOrdensServico();
  }
  initializeStatusList() {
    this.statusList = Object.entries(StatusOrdem).map(([key, value]) => ({
      key,
      value,
    }));
  }
}
