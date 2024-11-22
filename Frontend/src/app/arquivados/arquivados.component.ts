import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {ArquivadoResponse} from "../dtos/responses/arquivado.response";
import {ArquivadosRequest} from "../dtos/requests/arquivado.request";
import {ToastrService} from "ngx-toastr";
import {PageEvent} from "@angular/material/paginator";

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
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.loadArquivados();
  }

  loadArquivados(): void {
    this.arquivados.getArquivados(this.currentPage, this.pageSize).subscribe({
      next: (data) => {
        this.dataSource.data = data.content;
        this.totalItems = data.totalElements;
        this.totalPages = data.totalPages;
        this.currentPage = data.number;
      },
      error: () => {
        this.toastr.error("Erro ao carregar os dados", "Erro");
      },
    });
  }
  onPageChange(event: PageEvent): void {
    this.pageSize = event.pageSize;
    this.currentPage = event.pageIndex;
    this.loadArquivados();
  }

  excluirArquivado(id: number): void {
    this.arquivados.excluirArquivado(id).subscribe({
      next: () => {
        this.toastr.success("Arquivo excluído com sucesso");
        this.loadArquivados();
      },
      error: () => {
        this.toastr.error("Erro ao excluir o arquivo", "Erro");
      },
    });
  }

  restaurarArquivado(id: number): void {
    this.arquivados.restaurarArquivado(id).subscribe({
      next: () => {
        this.toastr.success("Arquivo restaurado com sucesso");
        this.loadArquivados();
      },
      error: () => {
        this.toastr.error("Erro ao restaurar o arquivo", "Erro");
      },
    });
  }
  calcularDiasRestantes(dtExcluir: Date, dtArquivado: Date): number {
    const diffTime = new Date(dtExcluir).getTime() - new Date(dtArquivado).getTime();
    return Math.ceil(diffTime / (1000 * 60 * 60 * 24));
  }
}
