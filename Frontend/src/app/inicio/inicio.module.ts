import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InicioComponent } from './inicio.component';
import { NotificationBarModule } from '../notification-bar/notification-bar.module';
import { HeaderModule } from '../header/header.module';
import { SidebarModule } from '../sidebar/sidebar.module';
import { ButtonModule } from 'primeng/button';

@NgModule({
  declarations: [InicioComponent],
  imports: [
    CommonModule,
    NotificationBarModule,
    HeaderModule,
    SidebarModule,
    ButtonModule
],
  exports: [InicioComponent]
})
export class InicioModule {

}
