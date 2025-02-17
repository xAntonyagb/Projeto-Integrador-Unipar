import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InicioComponent } from './inicio.component';
import { ButtonModule } from 'primeng/button';
import { ChartModule } from 'primeng/chart';
import { NavbarModule } from '../../shared/navbar/navbar.module';

@NgModule({
  declarations: [InicioComponent],
  imports: [
    CommonModule,
    NavbarModule,
    ButtonModule,
    ChartModule,
  ],
  exports: [InicioComponent],
})
export class InicioModule {}
