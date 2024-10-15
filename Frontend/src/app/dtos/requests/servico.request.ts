// src/app/dtos/requests/servico.request.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse } from '../responses/api.response';
import { ServicoResponse } from '../responses/servico.response';
import { AuthRequest } from './auth.request';

@Injectable({
  providedIn: 'root'
})
export class ServicoRequest {
  private apiUrl = ApiResponse.DESENVOLVIMENTO;

  constructor(private http: HttpClient, private auth: AuthRequest) {}

  getServicos(): Observable<ServicoResponse[]> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.auth.getCookie('acessToken')}`
    });

    return this.http.get<ServicoResponse[]>(`${this.apiUrl}/servico/all`, { headers });
  }
}