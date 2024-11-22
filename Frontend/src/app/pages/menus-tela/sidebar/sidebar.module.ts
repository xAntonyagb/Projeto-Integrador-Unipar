import { AppComponent } from '../../../app.component';
import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { RouterModule } from "@angular/router";
import { SidebarComponent } from "./sidebar.component";
import { MatTabsModule } from '@angular/material/tabs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


@NgModule({
declarations:[SidebarComponent],
imports:[
  BrowserModule,
  RouterModule,
  MatTabsModule,
  BrowserAnimationsModule
],
exports:[SidebarComponent]
})
export class SidebarModule{}
