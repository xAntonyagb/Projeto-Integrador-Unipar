import { AuthService } from './../../../auth/auth.service';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Bloco } from '../../tab-ambientes/service-ambiente/ambiente';


@Injectable({
  providedIn: 'root'
})
export class BlocoService {
  private apiUrl = 'http://assetinsight.awahosting.cloud:8080/bloco/all'; // URL do backend

  constructor(private http: HttpClient, private auth: AuthService) {}

  getBlocosData(): Observable<Bloco[]>{
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.auth.getCookie('acessToken')}`
    });

    console.log(headers);
    return this.http.get<Bloco[]>(this.apiUrl, { headers });
  }
}
