import { NgModule } from '@angular/core';
import { HeaderComponent } from './header.component';
import { NotificationBarModule } from '../notification-bar/notification-bar.module';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [HeaderComponent],
  imports: [NotificationBarModule, CommonModule],
  exports: [HeaderComponent],
})
export class HeaderModule {}
