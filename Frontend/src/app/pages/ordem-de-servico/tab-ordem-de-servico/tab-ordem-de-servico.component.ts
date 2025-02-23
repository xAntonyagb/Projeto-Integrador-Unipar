import { AfterViewInit, Component, OnInit, ViewChild, Output, EventEmitter } from '@angular/core';
import { DatePipe } from '@angular/common';
import { OrdemService } from '../../../services/ordem-servico.service';
import { ToastrService } from 'ngx-toastr';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { FormBuilder, FormGroup } from '@angular/forms';
import { StatusOrdem } from '../../../dtos/enums/status-ordem.enum';
import { ApiGenericToasts } from '../../../infra/api/api.generic-toasts';
@Component({
  selector: 'app-tab-ordem-de-servico',
  templateUrl: './tab-ordem-de-servico.component.html',
  styleUrls: ['./tab-ordem-de-servico.component.scss'],
  providers: [DatePipe],
})
export class TabOrdemDeServicoComponent implements OnInit, AfterViewInit {
  @ViewChild(MatSort) sort: MatSort | undefined;

  showFilters = false;
  filters = { data: '', status: '' };
  displayedColumns: string[] = [
    'id',
    'descricao',
    'qtdServicos',
    'valorTotal',
    'data',
    'status',
    'arquivar',
  ];
  dataSource = new MatTableDataSource<any>([]);
  ordensServico: any[] = [];
  totalPages: number = 0;
  currentPage: number = 0;
  pageSize: number = 10;
  statusList: { key: string; value: string }[] = [];
  filtersForm: FormGroup;
  isModalOpen = false;

  constructor(
    private ordem: OrdemService,
    private datePipe: DatePipe,
    private toastr: ToastrService,
    private fb: FormBuilder,
    private genericToast: ApiGenericToasts
  ) {
    this.filtersForm = this.fb.group({
      descricao: [''],
      servico: [''],
      valorTotal: [''],
      arquivada: [''],
      sort: [''],
    });
  }
 
  ngOnInit() {
    this.loadOrdensServico();
  }

  ngAfterViewInit() {
    if (this.sort) {
      this.dataSource.sort = this.sort;
    }
  }
  toggleFilters() {
    this.showFilters = !this.showFilters;
  }

  loadOrdensServico() {
    this.ordem.getAll().subscribe({
      next: (data) => {
        this.ordensServico = data.content.map((ordem: any) => ({
          id: ordem.id,
          descricao: ordem.descricao,
          data: this.datePipe.transform(ordem.data, 'dd/MM/yyyy'),
          qtdServicos: ordem.qtdServicos,
          valorTotal: ordem.valorTotal,
          status: ordem.status,
        }));
        this.dataSource.data = this.ordensServico;
        this.totalPages = data.totalPages;
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

  arquivarOrdem(id: number) {
    this.ordem.arquivar(id).subscribe({
      next: (data) => {
        this.loadOrdensServico();
        this.toastr.success('Ordem arquivada com sucesso');
      },
      error: (e) => {
        this.genericToast.showErro(e);
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
    this.loadOrdensServico();
  }
  initializeStatusList() {
    this.statusList = Object.entries(StatusOrdem).map(([key, value]) => ({
      key,
      value,
    }));
  }

  @Output() open = new EventEmitter<void>();
  openModal() {
    this.isModalOpen = true;
    this.open.emit();
  }
  closeModal() {
    this.isModalOpen = false;
  }
}
