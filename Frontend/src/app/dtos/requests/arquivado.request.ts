import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
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

  getArquivados(page: number, size: number): Observable<PaginacaoResponse<ArquivadoResponse>> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get<PaginacaoResponse<ArquivadoResponse>>(`${this.apiUrl}/arquivados/all`, { params });
  }

  excluirArquivado(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/arquivados?id=${id}`);
  }

  restaurarArquivado(id: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/arquivados/restaurar?id=${id}`, {});
  }
}
