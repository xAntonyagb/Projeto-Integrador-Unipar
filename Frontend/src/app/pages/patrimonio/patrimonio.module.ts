import { NgModule, OnInit } from '@angular/core';
import { PatrimonioComponent } from './patrimonio.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Button } from 'primeng/button';
import { MenuModule } from 'primeng/menu';
import { MatTableModule } from '@angular/material/table';
import { CadastrarPatrimonioComponent } from '../../modals/Cadastro/cadastrar-patrimonio/cadastrar-patrimonio.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { NavbarModule } from '../../shared/navbar/navbar.module';

@NgModule({
  declarations: [PatrimonioComponent, CadastrarPatrimonioComponent],
  imports: [
    BrowserAnimationsModule,
    FormsModule,
    Button,
    MenuModule,
    ReactiveFormsModule,
    NavbarModule,
    MatPaginatorModule,
    MatTableModule,
    MatSortModule,
  ],
  exports: [PatrimonioComponent],
})
export class PatrimonioModule {}
