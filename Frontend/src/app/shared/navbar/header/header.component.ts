import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { NotificationBarComponent } from '../notification-bar/notification-bar.component';
import { NotificacaoResponse } from '../../../dtos/responses/notificacao.response';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent {
  @Output() menuToggled = new EventEmitter<void>();

  notificacoes: NotificacaoResponse[] = [];
  @ViewChild('notificationBar') notificationBar!: NotificationBarComponent;

  toggleNotificationBar(event: Event) {
    event.preventDefault();
    event.stopPropagation();
    this.notificationBar.toggleVisibility();
  }

  toggleMenu() {
    this.menuToggled.emit();
  }
}
