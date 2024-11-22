// src/app/dtos/requests/servico.request.ts
import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse } from '../responses/api.response';
import { ServicoResponse } from '../responses/servico.response';
import { AuthRequest } from './auth.request';
import {PaginacaoResponse} from "../responses/paginacao.response";

@Injectable({
  providedIn: 'root'
})
export class ServicoRequest {
  private apiUrl = ApiResponse.DESENVOLVIMENTO;

  constructor(private http: HttpClient, private auth: AuthRequest) {}

  getServicos(page: number, size: number): Observable<PaginacaoResponse<ServicoResponse>> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get<PaginacaoResponse<ServicoResponse>>(`${this.apiUrl}/servico/all`, { params });
  }
}
