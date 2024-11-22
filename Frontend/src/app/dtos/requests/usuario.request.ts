import { Injectable } from "@angular/core";
import { ApiResponse } from "../responses/api.response";
import { HttpClient } from "@angular/common/http";
import {map, Observable, of} from "rxjs";
import {AddUsuario, UsuarioResponse} from "../responses/usuario.response";
import {catchError, tap} from "rxjs/operators";

@Injectable({
    providedIn: 'root'
  })
  export class UsuarioRequest {
    private apiUrl = ApiResponse.DESENVOLVIMENTO;

    constructor(private http: HttpClient) {}

  getUsuario(): Observable<UsuarioResponse[]> {
    return this.http.get<{ content: UsuarioResponse[] }>(`${this.apiUrl}/usuario/all`).pipe(
      tap((response) => console.log('Dados paginados recebidos:', response)),

      map((response) => response.content),
      catchError((error) => {
        console.error('Erro ao buscar usuários:', error);
        return of([]);
      })
    );
  }
    setUsuario(usuarioData: AddUsuario): Observable<AddUsuario> {
      return this.http.post<AddUsuario>(`${this.apiUrl}/auth/cadastrar`, usuarioData);
    }
  }
