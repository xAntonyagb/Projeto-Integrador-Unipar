import { Injectable } from "@angular/core";
import { ApiResponse } from "../responses/api.response";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
  })
  export class UsuarioRequest {
    private apiUrl = ApiResponse.DESENVOLVIMENTO;
  
    constructor(private http: HttpClient) {}
  
    getUsuario(): Observable<any> {
      return this.http.get<any>(`${this.apiUrl}/usuario/all`);
    }
    setUsuario(ordemData: any): Observable<any> {
      return this.http.post<any>(`${this.apiUrl}/auth/cadastrar`, ordemData);
    }
  }