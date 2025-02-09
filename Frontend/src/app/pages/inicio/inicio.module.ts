import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InicioComponent } from './inicio.component';
import { NotificationBarModule } from '../menus-tela/notification-bar/notification-bar.module';
import { HeaderModule } from '../menus-tela/header/header.module';
import { SidebarModule } from '../menus-tela/sidebar/sidebar.module';
import { ButtonModule } from 'primeng/button';
import { ChartModule } from 'primeng/chart';

@NgModule({
  declarations: [InicioComponent],
  imports: [
    CommonModule,
    NotificationBarModule,
    HeaderModule,
    SidebarModule,
    ButtonModule,
    ChartModule,
  ],
  exports: [InicioComponent],
})
export class InicioModule {}
