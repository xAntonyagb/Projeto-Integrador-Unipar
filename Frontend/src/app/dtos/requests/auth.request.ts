import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { ApiResponse } from '../responses/api.response';
import { AuthResponse } from '../responses/auth.response.type';
import { ToastrService } from 'ngx-toastr';



@Injectable({
  providedIn: 'root',
})
export class AuthRequest {
  private apiUrl = ApiResponse.DESENVOLVIMENTO;

  constructor(private http: HttpClient, private router: Router, private toastr : ToastrService) {}

  login(username: string, password: string) {
    const body = {
      username: username,  // Usando as credenciais fornecidas
      password: password
    };

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    });

    console.log("login: " + username + " senha: " + password);

   return this.http.post<AuthResponse>(`${this.apiUrl}/auth/login`, body, { headers })
  .pipe(
    tap((response) => {
      this.extractToken(response);
    }),
    catchError((error) => {
      this.toastr.error('Erro de login. Verifique suas credenciais.', 'Login Inválido');
      return throwError(() => error); 
      }));
    }
  private extractToken(response: AuthResponse): void {
    if (response && response.acessToken) {
      const createdAt = new Date(response.createdAt).getTime();
      const expiresIn = new Date(response.expiresIn).getTime();
      const expiresInHours = (expiresIn - createdAt) / (1000 * 60 * 60);
      this.setCookie('acessToken', response.acessToken,expiresInHours);
      console.log('Token recebido: ', response.acessToken
      + ' Expira em: ' + expiresInHours + ' horas'
      );
      this.router.navigate(['/inicio']);
    } else {
      console.log('Token não recebido, verifique a resposta do servidor.');
      alert('Token não recebido, verifique a resposta do servidor.');
    }
  }

  getToken() {
    return this.getCookie('acessToken');
  }

  isLoggedIn(): boolean {
    return !!this.getCookie('acessToken');
  }

  getHeadersWithToken(): HttpHeaders {
    const token = this.getToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    });
  }

  getProtectedResource() {
    const headers = this.getHeadersWithToken();
    return this.http.get(`${this.apiUrl}/protected`, { headers })
      .pipe(
        catchError((error) => {
          console.error('Erro ao acessar recurso protegido', error);
          return throwError(error);
        })
      );
  }
  setCookie(name: string, value: string, expiresInHours: number) {
    const date = new Date();
    date.setTime(date.getTime() + (expiresInHours *60 * 60 * 1000));
    const expires = "expires=" + date.toUTCString();
    document.cookie = name + "=" + value + ";" + expires + ";path=/";
  }

  getCookie(name: string): string | null {
  const nameEQ = name + "=";
  const ca = document.cookie.split(';');
  for (let i = 0; i < ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) === ' ') c = c.substring(1, c.length);
    if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
  }
  deleteCookie(name: string) {
    document.cookie = name + '=; Max-Age=-99999999;';
  }
}
