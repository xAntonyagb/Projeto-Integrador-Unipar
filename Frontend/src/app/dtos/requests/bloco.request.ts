import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse } from '../responses/api.response';
import { BlocoResponse } from '../responses/bloco.response';
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
}
