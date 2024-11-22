import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse } from '../responses/api.response';
import {PaginacaoResponse} from "../responses/paginacao.response";
import {OrdemResponse} from "../responses/ordem.response";


@Injectable({
  providedIn: 'root'
})
export class OrdemRequest {
  private apiUrl = ApiResponse.DESENVOLVIMENTO;

  constructor(private http: HttpClient) {}

  getOrdensServico(
    page: number,
    size: number,
    descricao?: string,
    servico?: number,
    valorTotal?: number,
    arquivada?: boolean,
    sort?: string
  ): Observable<PaginacaoResponse<OrdemResponse>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    if (descricao) {
      params = params.set('descricao', descricao);
    }

    if (servico !== undefined) {
      params = params.set('servico', servico.toString());
    }

    if (valorTotal !== undefined) {
      params = params.set('valorTotal', valorTotal.toString());
    }

    if (arquivada !== undefined) {
      params = params.set('arquivada', arquivada.toString());
    }

    if (sort) {
      params = params.set('sort', sort);
    }

    return this.http.get<PaginacaoResponse<OrdemResponse>>(`${this.apiUrl}/ordem-servico/all`, { params });
  }

  setOrdensServico(ordemData: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/ordem-servico`, ordemData);
  }

  arquivarOrdem(id: number): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/ordem-servico/arquivar?id=${id}`,{});
  }
}
