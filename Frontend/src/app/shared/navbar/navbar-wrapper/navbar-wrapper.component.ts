import { Component } from '@angular/core';

@Component({
  selector: 'app-navbar-wrapper',
  templateUrl: './navbar-wrapper.component.html',
  styleUrl: './navbar-wrapper.component.scss'
})
export class NavbarWrapperComponent {
  isSidebarActive = false;

  onMenuToggled() {
    this.isSidebarActive = !this.isSidebarActive;
  }
}