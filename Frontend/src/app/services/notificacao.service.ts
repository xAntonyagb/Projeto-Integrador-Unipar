import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiBaseUrls } from '../infra/api/api.base-urls';
import { NotificacaoResponse } from '../dtos/responses/notificacao.response';
import { PaginacaoResponse } from '../dtos/responses/paginacao.response';

@Injectable({
  providedIn: 'root',
})
export class NotificacaoService {
  private readonly apiUrl = ApiBaseUrls.getBaseUrl(); // URL do endpoint do backend

  constructor(private http: HttpClient) {}

  getAll(
    page: number = 0,
    size: number = 10,
    sort?: string,
  ): Observable<PaginacaoResponse<NotificacaoResponse>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    if (sort) params = params.set('sort', sort);

    return this.http.get<PaginacaoResponse<NotificacaoResponse>>(
      `${this.apiUrl}/notificacao/all`,
      { params },
    );
  }

  getById(id: number): Observable<NotificacaoResponse> {
    return this.http.get<NotificacaoResponse>(
      `${this.apiUrl}/notificacao?id=${id}`,
    );
  }

  markAsRead(id: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/notificacao/read?id=${id}`, {});
  }

  markAllAsRead(): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/notificacao/read/all`, {});
  }
}
