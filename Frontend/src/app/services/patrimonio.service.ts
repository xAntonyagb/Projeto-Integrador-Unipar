import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiBaseUrls } from '../infra/api/api.base-urls';
import { PaginacaoResponse } from '../dtos/responses/paginacao.response';
import { PatrimonioResponse } from '../dtos/responses/patrimonio.response';
import { PatrimonioRequest } from '../dtos/requests/patrimonio.request';

@Injectable({
  providedIn: 'root',
})
export class PatrimonioService {
  private readonly apiUrl = ApiBaseUrls.getBaseUrl();

  constructor(private http: HttpClient) {}

  getAll(
    page: number = 0,
    size: number = 10,
    descricao?: string,
    ambiente?: number,
  ): Observable<PaginacaoResponse<PatrimonioResponse>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    if (descricao) params = params.set('descricao', descricao);
    if (ambiente) params = params.set('ambiente', ambiente.toString());

    return this.http.get<PaginacaoResponse<PatrimonioResponse>>(
      `${this.apiUrl}/patrimonio/all`,
      { params },
    );
  }

  getById(id: number): Observable<PatrimonioResponse> {
    return this.http.get<PatrimonioResponse>(
      `${this.apiUrl}/patrimonio?id=${id}`,
    );
  }

  deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/patrimonio?id=${id}`);
  }

  save(request: PatrimonioRequest): Observable<PatrimonioResponse> {
    return this.http.post<PatrimonioResponse>(
      `${this.apiUrl}/patrimonio`,
      request,
    );
  }
}
