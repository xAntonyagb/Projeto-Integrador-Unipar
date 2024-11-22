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
    return this.http.get<CategoriaResponse[]>(`${this.apiUrl}/categoria/all`);
  }
  setCategoria(categoria: AddCategoria): Observable<AddCategoria> {
    return this.http.post<AddCategoria>(`${this.apiUrl}/categoria`, categoria);
  }
  deleteCategoria(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/categoria/delete/${id}`);
  }
  updateCategoria(id: number, categoria: AddCategoria): Observable<any> {
    return this.http.post<AddCategoria>(`${this.apiUrl}/categoria?id=${id}`, categoria);
  }
}
