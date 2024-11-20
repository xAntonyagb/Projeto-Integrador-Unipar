import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {ArquivadoResponse} from "../responses/arquivado.response";
import {ApiResponse} from "../responses/api.response";
import {PaginacaoResponse} from "../responses/paginacao.response";

@Injectable({
  providedIn: 'root',
})
export class ArquivadosRequest {
  private readonly apiUrl = ApiResponse.DESENVOLVIMENTO

  constructor(private http: HttpClient) {}

  getArquivados(): Observable<PaginacaoResponse<ArquivadoResponse>> {
    return this.http.get<PaginacaoResponse<ArquivadoResponse>>(`${this.apiUrl}/arquivados/all`);
  }
  excluirArquivado(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
  restaurarArquivado(id: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/restaurar/${id}`, {});
  }
}
