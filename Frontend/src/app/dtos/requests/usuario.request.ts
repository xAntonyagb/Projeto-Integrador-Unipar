import { Injectable } from "@angular/core";
import { ApiResponse } from "../responses/api.response";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import {UsuarioResponse} from "../responses/usuario.response";

@Injectable({
    providedIn: 'root'
  })
  export class UsuarioRequest {
    private apiUrl = ApiResponse.DESENVOLVIMENTO;

    constructor(private http: HttpClient) {}

    getUsuario(): Observable<UsuarioResponse[]> {
      return this.http.get<UsuarioResponse[]>(`${this.apiUrl}/usuario/all`);
    }
    setUsuario(ordemData: UsuarioResponse[]): Observable<UsuarioResponse[]> {
      return this.http.post<UsuarioResponse[]>(`${this.apiUrl}/auth/cadastrar`, ordemData);
    }
  }
