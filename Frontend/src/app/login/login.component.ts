import { Router } from '@angular/router';
import { AuthService } from './../auth/auth.service';
import { Component } from '@angular/core';

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

  constructor(private authService: AuthService, private router: Router) {
    this.cookieConsent = this.checkCookieConsent();
  }

  onSubmit(event: Event) {

    event.preventDefault();
    if(this.cookieConsent) {

    this.authService.login(this.username, this.password).subscribe({
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
  this.authService.setCookie('cookieConsent', 'true', 365);
}

checkCookieConsent(): boolean {
  return this.authService.getCookie('cookieConsent') === 'true';
}

}
