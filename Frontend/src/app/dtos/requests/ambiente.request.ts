import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { ApiResponse } from '../responses/api.response';
import { AmbienteResponse } from '../responses/ambiente.response';
import { AuthRequest } from './auth.request';
import {PaginacaoResponse} from "../responses/paginacao.response";


@Injectable({
  providedIn: 'root'
})

export class AmbienteRequest {

  private apiUrl=ApiResponse.DESENVOLVIMENTO;

  constructor(private http:HttpClient, private auth: AuthRequest){}

  getAmbientes(page: number, size: number): Observable<PaginacaoResponse<AmbienteResponse>> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get<PaginacaoResponse<AmbienteResponse>>(`${this.apiUrl}/ambiente/all`, { params });
  }

  setAmbiente(ordemData: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/ambiente`, ordemData);
  }

  deleteAmbiente(id: number): Observable<any> {
    return this.http.delete<void>(`${this.apiUrl}/ambiente/${id}`);
  }
}
