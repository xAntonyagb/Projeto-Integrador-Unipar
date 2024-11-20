import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {ArquivadoResponse} from "../dtos/responses/arquivado.response";
import {ArquivadosRequest} from "../dtos/requests/arquivado.request";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-arquivados',
  templateUrl: './arquivados.component.html',
  styleUrls: ['./arquivados.component.scss']
})
export class ArquivadosComponent implements OnInit {
  displayedColumns: string[] = ['id', 'tipo', 'descricao', 'dtArquivado', 'dtExcluir', 'excluir'];
  dataSource: MatTableDataSource<ArquivadoResponse> = new MatTableDataSource<ArquivadoResponse>();

  constructor(
    private arquivados: ArquivadosRequest,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.loadArquivados();
  }

  loadArquivados(): void {
    this.arquivados.getArquivados().subscribe({
      next: (data) => {
        this.dataSource.data = data.content;
        console.log(this.dataSource.data);
      },
      error: (err) => {
        this.toastr.error("Erro ao carregar os dados, Erro");
      },
    });
  }

  excluirArquivado(id: number): void {
    this.arquivados.excluirArquivado(id).subscribe({
      next: () => {
        this.dataSource.data = this.dataSource.data.filter(item => item.id !== id);
      },
      error: (err) => {
        this.toastr.error("Erro ao excluir o arquivo, Erro");
      },
    });
  }

  restaurarArquivado(id: number): void {
    this.arquivados.restaurarArquivado(id).subscribe({
      next: () => {
        this.dataSource.data = this.dataSource.data.filter(item => item.id !== id);
      },
      error: (err) => {
        this.toastr.error("Erro ao restaurar o arquivo, Erro");
      },
    });
  }
}
