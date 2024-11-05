import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse } from '../responses/api.response';


@Injectable({
  providedIn: 'root'
})
export class TarefaRequest {
  private apiUrl = ApiResponse.DESENVOLVIMENTO;

  constructor(private http: HttpClient) {}

  getTarefa(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/tarefa/all`);
  }
  setTarefa(ordemData: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/tarefa`, ordemData);
  }
}
