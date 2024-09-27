import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface Bloco {
  id: number;
  nome: string;
  quantidadeAmbientes: number;
}

@Injectable({
  providedIn: 'root'
})
export class BlocoService {
  private apiUrl = 'http://localhost:8080/bloco/all'; // URL do backend

  constructor(private http: HttpClient) {}

  getBlocosData(): Observable<Bloco[]> {
    return this.http.get<Bloco[]>(this.apiUrl);
  }
}
