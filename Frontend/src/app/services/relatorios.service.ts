import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiBaseUrls } from '../infra/api/api.baseUrls';
import { RelatoriosGeraisResponse } from '../dtos/responses/relatorios/RelatoriosGerais.response';
import { RelatoriosMensaisResponse } from '../dtos/responses/relatorios/RelatoriosMensais.response';
import { RelatoriosAnoResponse } from '../dtos/responses/relatorios/RelatoriosAno.response';
import { RelatoriosFullResponse } from '../dtos/responses/relatorios/RelatoriosFull.response';

@Injectable({
  providedIn: 'root',
})
export class RelatoriosService {
  private readonly apiUrl = ApiBaseUrls.getBaseUrl();

  constructor(private http: HttpClient) {}

  getGeral(): Observable<RelatoriosGeraisResponse> {
    return this.http.get<RelatoriosGeraisResponse>(
      `${this.apiUrl}/relatorios/geral`,
    );
  }

  getMensal(mes?: number): Observable<RelatoriosMensaisResponse> {
    let params = new HttpParams();
    if (mes) params = params.set('mes', mes.toString());

    return this.http.get<RelatoriosMensaisResponse>(
      `${this.apiUrl}/relatorios/mensal`,
      { params },
    );
  }

  getAnual(ano?: number): Observable<RelatoriosAnoResponse> {
    let params = new HttpParams();
    if (ano) params = params.set('ano', ano.toString());

    return this.http.get<RelatoriosAnoResponse>(
      `${this.apiUrl}/relatorios/anual`,
      { params },
    );
  }

  getAll(mes?: number, ano?: number): Observable<RelatoriosFullResponse> {
    let params = new HttpParams();
    if (mes) params = params.set('mes', mes.toString());
    if (ano) params = params.set('ano', ano.toString());

    return this.http.get<RelatoriosFullResponse>(
      `${this.apiUrl}/relatorios/all`,
      { params },
    );
  }
}
