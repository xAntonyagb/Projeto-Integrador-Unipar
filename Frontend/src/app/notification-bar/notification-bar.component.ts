import {Component, ElementRef, HostListener, OnInit} from '@angular/core';
import {NotificacaoResponse} from "../dtos/responses/notificacao.response";
import {NotificacaoRequest} from "../dtos/requests/notificacao.request";

@Component({
  selector: 'app-notification-bar',
  templateUrl: './notification-bar.component.html',
  styleUrls: ['./notification-bar.component.scss']
})
export class NotificationBarComponent  implements OnInit {
  notificacoes: NotificacaoResponse[] = [];
  isOpen = false;

  constructor(
    private notificacao: NotificacaoRequest,
    private eRef: ElementRef
  ) {}

  ngOnInit(): void {
    this.carregarNotificacoes();
  }

  carregarNotificacoes(): void {
    this.notificacao.getNotificacoes().subscribe(
      (data) => {
        this.notificacoes = data;
      },
      (error) => {
        console.error('Erro ao carregar notificações:', error);
      }
    );
  }
  toggleVisibility(): void {
    this.isOpen = !this.isOpen;
  }
  @HostListener('document:click', ['$event'])
  clickOut(event: Event) {
    if (this.isOpen && !this.eRef.nativeElement.contains(event.target)) {
      this.isOpen = false;
    }
  }
}
