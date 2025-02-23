import { Injectable } from '@angular/core';
import { ApiBaseUrls } from '../infra/api/api.base-urls';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UsuarioResponse } from '../dtos/responses/usuario.response';
import { PaginacaoResponse } from '../dtos/responses/paginacao.response';
import { UsuarioPermissoes } from '../dtos/enums/usuario-permissoes.enum';
import { CadastroRequest } from '../dtos/requests/cadastro.request';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  private readonly apiUrl = ApiBaseUrls.getBaseUrl();

  constructor(private http: HttpClient) {}

  getAll(
    page: number = 0,
    size: number = 10,
    username?: string,
    dtCriacao?: Date,
    lastLogin?: Date,
    permissao?: UsuarioPermissoes,
  ): Observable<PaginacaoResponse<UsuarioResponse>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    if (username) params = params.set('username', username);
    if (dtCriacao) params = params.set('dtCriacao', dtCriacao.toString());
    if (lastLogin) params = params.set('lastLogin', lastLogin.toString());
    if (permissao) params = params.set('permissao', permissao.toString());

    return this.http.get<PaginacaoResponse<UsuarioResponse>>(
      `${this.apiUrl}/usuario/all`,
      { params },
    );
  }

  getById(id: string): Observable<UsuarioResponse> {
    return this.http.get<UsuarioResponse>(`${this.apiUrl}/usuario?id=${id}`);
  }

  deleteById(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/usuario?id=${id}`);
  }

  cadastrar(usuario: CadastroRequest) {
    return this.http.post<UsuarioResponse>(
      `${this.apiUrl}/login/cadastrar`,
      usuario,
    );
  }
}
