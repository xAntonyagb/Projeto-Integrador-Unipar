import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {ArquivadoResponse} from "../../dtos/responses/Arquivado.response";
import {ArquivadosRequest} from "../../services/arquivado.service";
import {ToastrService} from "ngx-toastr";
import {PageEvent} from "@angular/material/paginator";
import {ApiGenericToasts} from "../../infra/api/api.genericToasts";

@Component({
  selector: 'app-arquivados',
  templateUrl: './arquivados.component.html',
  styleUrls: ['./arquivados.component.scss']
})
export class ArquivadosComponent implements OnInit {
  displayedColumns: string[] = ['id', 'tipo', 'descricao', 'dtArquivado', 'dtExcluir', 'excluir'];
  dataSource: MatTableDataSource<ArquivadoResponse> = new MatTableDataSource<ArquivadoResponse>();
  totalItems: number = 0;
  pageSize: number = 5;
  currentPage: number = 0;
  totalPages: number = 0;

  constructor(
    private arquivados: ArquivadosRequest,
    private toastr: ToastrService,
    private genericToast: ApiGenericToasts
  ) {}

  ngOnInit(): void {
    this.loadArquivados();
  }

  loadArquivados(): void {
    this.arquivados.getAll(this.currentPage, this.pageSize).subscribe({
      next: (data) => {
        this.dataSource.data = data.content;
        this.totalItems = data.totalElements;
        this.totalPages = data.totalPages;
        this.currentPage = data.number;
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

  onPageChange(event: PageEvent): void {
    this.pageSize = event.pageSize;
    this.currentPage = event.pageIndex;
    this.loadArquivados();
  }

  excluirArquivado(id: number): void {
    this.arquivados.deleteById(id).subscribe({
      complete: () => {
        this.toastr.success("Arquivo excluído com sucesso");
        this.loadArquivados();
      },
      error: (e) => {
        this.genericToast.showErro(e);
      },
    });
  }

  restaurarArquivado(id: number): void {
    this.arquivados.restaurar(id).subscribe({
      complete: () => {
        this.toastr.success("Arquivo restaurado com sucesso");
        this.loadArquivados();
      },
      error: (e) => {
        this.genericToast.showErro(e);
      },
    });
  }
  calcularDiasRestantes(dtExcluir: Date, dtArquivado: Date): number {
    const diffTime = new Date(dtExcluir).getTime() - new Date(dtArquivado).getTime();
    return Math.ceil(diffTime / (1000 * 60 * 60 * 24));
  }
}
