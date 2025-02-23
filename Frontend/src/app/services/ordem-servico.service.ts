import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiBaseUrls } from '../infra/api/api.base-urls';
import { PaginacaoResponse } from '../dtos/responses/paginacao.response';
import { OrdemServicoResponse } from '../dtos/responses/ordem-servico.response';
import { OrdemServicoRequest } from '../dtos/requests/ordem-servico.request';
import { StatusOrdem } from '../dtos/enums/status-ordem.enum';

@Injectable({
  providedIn: 'root',
})
export class OrdemService {
  private readonly apiUrl = ApiBaseUrls.getBaseUrl();

  constructor(private http: HttpClient) {}

  getAll(
    page: number = 0,
    size: number = 10,
    descricao?: string,
    data?: string,
    servico?: number,
    valorTotal?: number,
    status?: StatusOrdem,
    arquivada: boolean = false,
  ): Observable<PaginacaoResponse<OrdemServicoResponse>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    if (descricao) params = params.set('descricao', descricao);
    if (data) params = params.set('data', data);
    if (servico) params = params.set('servico', servico.toString());
    if (valorTotal) params = params.set('valorTotal', valorTotal.toString());
    if (status) params = params.set('status', status.toString());
    if (arquivada !== undefined)
      params = params.set('arquivada', arquivada.toString());

    return this.http.get<PaginacaoResponse<OrdemServicoResponse>>(
      `${this.apiUrl}/ordem-servico/all`,
      { params },
    );
  }

  getById(id: number): Observable<OrdemServicoResponse> {
    return this.http.get<OrdemServicoResponse>(
      `${this.apiUrl}/ordem-servico?id=${id}`,
    );
  }

  deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/ordem-servico?id=${id}`);
  }

  arquivar(id: number): Observable<void> {
    return this.http.post<void>(
      `${this.apiUrl}/ordem-servico/arquivar?id=${id}`,
      {},
    );
  }

  restaurar(id: number): Observable<void> {
    return this.http.post<void>(
      `${this.apiUrl}/ordem-servico/restaurar?id=${id}`,
      {},
    );
  }

  save(request: OrdemServicoRequest): Observable<OrdemServicoResponse> {
    return this.http.post<OrdemServicoResponse>(
      `${this.apiUrl}/ordem-servico`,
      request,
    );
  }
}
