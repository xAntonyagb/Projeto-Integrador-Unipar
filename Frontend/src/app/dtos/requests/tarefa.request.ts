import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse } from '../responses/api.response';
import {PaginacaoResponse} from "../responses/paginacao.response";


@Injectable({
  providedIn: 'root'
})
export class TarefaRequest {
  private apiUrl = ApiResponse.DESENVOLVIMENTO;

  constructor(private http: HttpClient) {}

  getTarefa(page: number, size: number): Observable<PaginacaoResponse<any>> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get<PaginacaoResponse<any>>(`${this.apiUrl}/tarefa/all`, { params });
  }

  setTarefa(ordemData: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/tarefa`, ordemData);
  }
}
