import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse } from '../responses/api.response';
import { AddBloco, BlocoResponse } from '../responses/bloco.response';
import { AuthRequest } from './auth.request';



@Injectable({
  providedIn: 'root'
})
export class BlocoRequest {
  private apiUrl = ApiResponse.DESENVOLVIMENTO; // URL do backend

  constructor(private http: HttpClient, private auth: AuthRequest) {}

  getBlocosData(): Observable<BlocoResponse[]>{
    return this.http.get<BlocoResponse[]>(`${this.apiUrl}/bloco/all`);
  }
  setBloco(bloco:AddBloco): Observable<any> {
    return this.http.post<AddBloco>(`${this.apiUrl}/bloco`, bloco);
  }

  deleteBloco(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/bloco/${id}`);
  }
  updateBloco(id: number, bloco: AddBloco): Observable<any> {
    return this.http.post<AddBloco>(`${this.apiUrl}/bloco?id=${id}`, bloco);
  }
}
