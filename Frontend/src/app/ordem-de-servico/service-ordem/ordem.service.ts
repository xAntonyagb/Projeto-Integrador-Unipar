import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrdemServicoService {
  private apiUrl = 'http://assetinsight.awahosting.cloud:8080/ordem-servico/all';

  constructor(private http: HttpClient) {}

  getOrdensServico(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }
}
