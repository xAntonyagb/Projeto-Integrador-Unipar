import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse } from '../responses/api.response';
import { AddCategoria, CategoriaResponse } from '../responses/categoria.response';
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
  setCategoria(categoria: AddCategoria): Observable<AddCategoria> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.auth.getCookie('acessToken')}`,
      'Content-Type': 'application/json'
    });
  
    return this.http.post<AddCategoria>(`${this.apiUrl}/categoria/add`, categoria, { headers });
  }
  deleteCategoria(id: number): Observable<void> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.auth.getCookie('acessToken')}`
    });
  
    return this.http.delete<void>(`${this.apiUrl}/categoria/delete/${id}`, { headers });
  }
}