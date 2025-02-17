import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from './sidebar/sidebar.component';
import { NotificationBarComponent } from './notification-bar/notification-bar.component';
import { HeaderComponent } from './header/header.component';
import { NavbarWrapperComponent } from './navbar-wrapper/navbar-wrapper.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTabsModule } from '@angular/material/tabs';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';

@NgModule({
  declarations: [
    SidebarComponent,
    NotificationBarComponent,
    HeaderComponent,
    NavbarWrapperComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule,
    MatTabsModule,
    BrowserAnimationsModule
  ],
  exports: [
    SidebarComponent,
    NotificationBarComponent,
    HeaderComponent,
    NavbarWrapperComponent
  ]
})
export class NavbarModule { }