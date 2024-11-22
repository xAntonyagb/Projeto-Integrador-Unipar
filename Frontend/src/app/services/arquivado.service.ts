import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import {ArquivadoResponse} from "../dtos/responses/Arquivado.response";
import {ApiBaseUrls} from "../infra/api/api.baseUrls";
import {PaginacaoResponse} from "../dtos/responses/Paginacao.response";
import {TipoArquivado} from "../dtos/enums/TipoArquivado.enum";

@Injectable({
  providedIn: 'root',
})
export class ArquivadosRequest {
  private readonly apiUrl = ApiBaseUrls.DESENVOLVIMENTO

  constructor(private http: HttpClient) {}

  getAll(page: number = 0,
         size: number = 10,
         tipo ?: TipoArquivado,
         dtArquivado ?: number,
         dtExcluir ?: Date,
         arquivadoBy ?: string,
         ordemServico ?: number,
         tarefa ?: number ): Observable<PaginacaoResponse<ArquivadoResponse>>
  {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    if (tipo) params = params.set('tipo', tipo.toString());
    if (dtArquivado) params = params.set('dtArquivado', dtArquivado.toString());
    if (dtExcluir) params = params.set('dtExcluir', dtExcluir.toString());
    if (arquivadoBy) params = params.set('arquivadoBy', arquivadoBy);
    if (ordemServico) params = params.set('ordemServico', ordemServico);
    if (tarefa) params = params.set('tarefa', tarefa);

    return this.http.get<PaginacaoResponse<ArquivadoResponse>>(`${this.apiUrl}/arquivados/all`, { params });
  }

  getById(id: number): Observable<ArquivadoResponse> {
    return this.http.get<ArquivadoResponse>(`${this.apiUrl}/arquivados/${id}`);
  }

  deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/arquivados?id=${id}`);
  }

  restaurar(id: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/arquivados/restaurar?id=${id}`, {});
  }
}
