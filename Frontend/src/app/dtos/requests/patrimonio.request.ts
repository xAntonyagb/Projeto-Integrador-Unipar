import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse } from '../responses/api.response';
import { PaginacaoResponse } from '../responses/paginacao.response';
import { PatrimonioResponse } from '../responses/patrimonio.response';

@Injectable({
  providedIn: 'root'
})
export class PatrimonioRequest {
  private apiUrl = `${ApiResponse.DESENVOLVIMENTO}/patrimonio`;

  constructor(private http: HttpClient) {}

  getPatrimonios(page: number, size: number): Observable<PaginacaoResponse<PatrimonioResponse>> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get<PaginacaoResponse<PatrimonioResponse>>(`${this.apiUrl}/all`, { params });
  }

  setPatrimonio(patrimonioData: any): Observable<PatrimonioResponse> {
    return this.http.post<PatrimonioResponse>(this.apiUrl, patrimonioData);
  }

  deletePatrimonio(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
