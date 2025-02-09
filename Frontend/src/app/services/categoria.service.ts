import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiBaseUrls } from '../infra/api/api.baseUrls';
import { CategoriaResponse } from '../dtos/responses/Categoria.response';
import { AuthService } from './auth.service';
import {CategoriaRequest} from "../dtos/requests/Categoria.request";
import {PaginacaoResponse} from "../dtos/responses/Paginacao.response";

@Injectable({
  providedIn: 'root',
})
export class CategoriaService {
  private readonly apiUrl = ApiBaseUrls.getBaseUrl();;

  constructor(private http: HttpClient) {}

  getAll(page: number = 0,
         size: number = 10,
         descricao?: string): Observable<PaginacaoResponse<CategoriaResponse>>
  {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    if (descricao) params = params.set('descricao', descricao);

    return this.http.get<PaginacaoResponse<CategoriaResponse>>(`${this.apiUrl}/categoria/all`, { params });
  }

  getById(id: number): Observable<CategoriaResponse> {
    return this.http.get<CategoriaResponse>(`${this.apiUrl}/categoria?id=${id}`);
  }

  deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/categoria?id=${id}`);
  }

  transferirCategoria(idOrigem: number, idDestino: number): Observable<void> {
    let params = new HttpParams()
      .set('idOrigem', idOrigem.toString())
      .set('idDestino', idDestino.toString());

    return this.http.put<void>(`${this.apiUrl}/categoria/transferir`, {}, { params });
  }

  save(request: CategoriaRequest): Observable<CategoriaResponse> {
    return this.http.post<CategoriaResponse>(`${this.apiUrl}/categoria`, request);
  }
}
