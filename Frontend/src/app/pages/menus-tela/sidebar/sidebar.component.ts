import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';

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
