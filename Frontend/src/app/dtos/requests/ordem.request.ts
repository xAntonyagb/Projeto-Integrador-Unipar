import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse } from '../responses/api.response';


@Injectable({
  providedIn: 'root'
})
export class OrdemRequest {
  private apiUrl = ApiResponse.DESENVOLVIMENTO;

  constructor(private http: HttpClient) {}

  getOrdensServico(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/ordem-servico/all`);
  }
}
