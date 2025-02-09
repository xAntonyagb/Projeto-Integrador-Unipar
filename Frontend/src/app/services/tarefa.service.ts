import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiBaseUrls } from '../infra/api/api.baseUrls';
import { PaginacaoResponse } from '../dtos/responses/Paginacao.response';
import { TarefaResponse } from '../dtos/responses/Tarefa.response';
import { TarefaRequest } from '../dtos/requests/Tarefa.request';
import { StatusTarefa } from '../dtos/enums/StatusTarefa.enum';
import { PrioridadeTarefa } from '../dtos/enums/PrioridadeTarefa.enum';

@Injectable({
  providedIn: 'root',
})
export class TarefaService {
  private readonly apiUrl = ApiBaseUrls.getBaseUrl();

  constructor(private http: HttpClient) {}

  getAll(
    page: number = 0,
    size: number = 10,
    titulo?: string,
    descricao?: string,
    previsao?: Date,
    ambiente?: number,
    categoria?: number,
    prioridade?: PrioridadeTarefa,
    status?: StatusTarefa,
    arquivado?: boolean,
  ): Observable<PaginacaoResponse<TarefaResponse>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    if (titulo) params = params.set('titulo', titulo);
    if (descricao) params = params.set('descricao', descricao);
    if (previsao) params = params.set('previsao', previsao.toString());
    if (ambiente) params = params.set('ambiente', ambiente.toString());
    if (categoria) params = params.set('categoria', categoria.toString());
    if (prioridade) params = params.set('prioridade', prioridade.toString());
    if (status) params = params.set('status', status.toString());
    if (arquivado !== undefined)
      params = params.set('arquivado', arquivado.toString());

    return this.http.get<PaginacaoResponse<TarefaResponse>>(
      `${this.apiUrl}/tarefa/all`,
      { params },
    );
  }

  getById(id: number): Observable<TarefaResponse> {
    return this.http.get<TarefaResponse>(`${this.apiUrl}/tarefa?id=${id}`);
  }

  deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/tarefa?id=${id}`);
  }

  arquivar(id: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/tarefa/arquivar?id=${id}`, {});
  }

  restaurar(id: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/tarefa/restaurar?id=${id}`, {});
  }

  save(request: TarefaRequest): Observable<TarefaResponse> {
    return this.http.post<TarefaResponse>(`${this.apiUrl}/tarefa`, request);
  }
}
