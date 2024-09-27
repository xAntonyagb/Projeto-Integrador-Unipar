import { Component } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})
export class SidebarComponent {
  constructor(private auth:  AuthService , private router: Router) { }

  Logout(event: Event){
    this.auth.deleteCookie('accessToken');
    this.router.navigate(['/login']);

  }
}
