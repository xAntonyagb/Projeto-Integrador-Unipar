import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { ApiBaseUrls } from '../infra/api/api.baseUrls';
import { AmbienteResponse } from '../dtos/responses/Ambiente.response';
import { AuthService } from './auth.service';
import {PaginacaoResponse} from "../dtos/responses/Paginacao.response";
import {AmbienteRequest} from "../dtos/requests/Ambiente.request";


@Injectable({
  providedIn: 'root'
})

export class AmbienteService {

  private apiUrl=ApiBaseUrls.DESENVOLVIMENTO;

  constructor(private http:HttpClient, private auth: AuthService){}

  getAll(page: number = 0,
         size: number = 10,
         descricao?: string,
         bloco?: number,
         patrimonio?: number
  ): Observable<PaginacaoResponse<AmbienteResponse>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    if(descricao) params = params.set('descricao', descricao);
    if(bloco) params = params.set('bloco', bloco);
    if(patrimonio) params = params.set('patrimonio', patrimonio);

    return this.http.get<PaginacaoResponse<AmbienteResponse>>(`${this.apiUrl}/ambiente/all`, { params });
  }

  getById(id: number): Observable<AmbienteResponse> {
    return this.http.get<AmbienteResponse>(`${this.apiUrl}/ambiente/${id}`);
  }

  save(ambiente: AmbienteRequest): Observable<AmbienteResponse> {
    return this.http.post<AmbienteResponse>(`${this.apiUrl}/ambiente`, ambiente);
  }

  deleteById(id: number): Observable<any> {
    let params = new HttpParams().set('id', id);
    return this.http.delete<void>(`${this.apiUrl}/ambiente/`, { params });
  }

  transferirAmbientes(origem: number, destino: number): Observable<any> {
    let params = new HttpParams()
      .set('ambienteId', origem)
      .set('ambienteDestinoId', destino);

    return this.http.post<void>(`${this.apiUrl}/ambiente/transferir`, { params });
  }
}
