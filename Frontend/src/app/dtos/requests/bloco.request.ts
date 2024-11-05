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
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.auth.getCookie('acessToken')}`
    });

    console.log(headers);
    return this.http.get<BlocoResponse[]>(`${this.apiUrl}/bloco/all`, { headers });
  }
  setBloco(bloco:AddBloco): Observable<any> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.auth.getCookie('acessToken')}`,
      'Content-Type': 'application/json'
    });
  
    return this.http.post<AddBloco>(`${this.apiUrl}/bloco`, bloco, { headers });
  }
  
  deleteBloco(id: number): Observable<void> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.auth.getCookie('acessToken')}`
    });
  
    return this.http.delete<void>(`${this.apiUrl}/categoria/${id}`, { headers });
  }
}
