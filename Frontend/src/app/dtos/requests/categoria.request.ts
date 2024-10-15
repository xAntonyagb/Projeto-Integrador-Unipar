import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse } from '../responses/api.response';
import { CategoriaResponse } from '../responses/categoria.response';
import { AuthRequest } from './auth.request';

@Injectable({
  providedIn: 'root'
})
export class CategoriaRequest {
  private apiUrl = ApiResponse.DESENVOLVIMENTO;

  constructor(private http: HttpClient, private auth: AuthRequest) {}

  getCategorias(): Observable<CategoriaResponse[]> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.auth.getCookie('acessToken')}`
    });

    return this.http.get<CategoriaResponse[]>(`${this.apiUrl}/categoria/all`, { headers });
  }
}