import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { ApiResponse } from '../responses/api.response';
import { AmbienteResponse } from '../responses/ambiente.response';
import { AuthRequest } from './auth.request';


@Injectable({
  providedIn: 'root'
})

export class AmbienteRequest {

  private apiUrl=ApiResponse.DESENVOLVIMENTO;

  constructor(private http:HttpClient, private auth: AuthRequest){}

  getAmbientes(): Observable<AmbienteResponse[]>{
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.auth.getCookie('acessToken')}`
    });

    console.log(headers);
    return this.http.get<AmbienteResponse[]>(`${this.apiUrl}/ambiente/all`, { headers });
  }
}
