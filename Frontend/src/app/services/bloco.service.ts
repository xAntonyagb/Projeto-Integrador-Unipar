import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiBaseUrls } from '../infra/api/api.base-urls';
import { BlocoResponse } from '../dtos/responses/bloco.response';
import { BlocoRequest } from '../dtos/requests/bloco.request';
import { PaginacaoResponse } from '../dtos/responses/paginacao.response';
import { LoadingService } from './loading.service';

@Injectable({
  providedIn: 'root',
})
export class BlocoService {
  private readonly apiUrl = ApiBaseUrls.getBaseUrl();

  constructor(private http: HttpClient,
    private loadingService: LoadingService
  ) {}

  getAll(
    page: number = 0,
    size: number = 10,
    descricao?: string,
  ): Observable<PaginacaoResponse<BlocoResponse>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    if (descricao) params = params.set('descricao', descricao);

    return this.http.get<PaginacaoResponse<BlocoResponse>>(
      `${this.apiUrl}/bloco/all`,
      { params },
    );
  }

  getById(id: number): Observable<BlocoResponse> {
    return this.http.get<BlocoResponse>(`${this.apiUrl}/bloco?id=${id}`);
  }

  deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/bloco?id=${id}`);
  }

  transferirBloco(blocoId: number, blocoDestinoId: number): Observable<void> {
    let params = new HttpParams()
      .set('blocoId', blocoId.toString())
      .set('blocoDestinoId', blocoDestinoId.toString());

    return this.http.put<void>(
      `${this.apiUrl}/bloco/transferir`,
      {},
      { params },
    );
  }

  save(request: BlocoRequest): Observable<BlocoResponse> {
    return this.http.post<BlocoResponse>(`${this.apiUrl}/bloco`, request);
  }
}
