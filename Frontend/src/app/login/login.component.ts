import { Router } from '@angular/router';
import { Component } from '@angular/core';
import { AuthRequest } from '../dtos/requests/auth.request';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';
  cookieConsent: boolean = false;

  constructor(private auth: AuthRequest, private router: Router) {
    this.cookieConsent = this.checkCookieConsent();
  }

  onSubmit(event: Event) {

    event.preventDefault();
    if(this.cookieConsent) {

    this.auth.login(this.username, this.password).subscribe({
      next: (response: any) => {
        console.log('Resposta da API de login:', response);
      },
      error: (error: string) => {
        this.errorMessage = " ";
      },
      complete: () => {
        console.log('Requisição de login completada.');
      }
    });
  }else{
    alert("você precisa aceitar os cookies para continuar")
  }

}
acceptCookies() {
  this.cookieConsent = true;
  this.auth.setCookie('cookieConsent', 'true', 365);
}

checkCookieConsent(): boolean {
  return this.auth.getCookie('cookieConsent') === 'true';
}

}
