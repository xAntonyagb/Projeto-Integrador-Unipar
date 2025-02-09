// notification-bar.module.ts
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationBarComponent } from './notification-bar.component';

@NgModule({
  declarations: [NotificationBarComponent],
  imports: [CommonModule],
  exports: [NotificationBarComponent], // Export the component
})
export class NotificationBarModule {}
