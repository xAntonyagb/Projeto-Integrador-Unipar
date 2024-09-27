import { Component, ViewChild } from '@angular/core';
import { NotificationBarComponent, } from '../notification-bar/notification-bar.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  @ViewChild('notificationBar') notificationBar!: NotificationBarComponent;

  toggleNotificationBar() {
    this.notificationBar.toggleVisibility();
  }
}
