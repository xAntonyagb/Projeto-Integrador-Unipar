import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {ApiResponse} from "../responses/api.response";
import {NotificacaoResponse} from "../responses/notificacao.response";

@Injectable({
  providedIn: 'root'
})
export class NotificacaoRequest {

  private apiUrl = ApiResponse.DESENVOLVIMENTO; // URL do endpoint do backend

  constructor(private http: HttpClient) {}

  getNotificacoes(): Observable<NotificacaoResponse[]> {
    return this.http.get<NotificacaoResponse[]>(`${this.apiUrl}/notificacao/all`);
  }
}
