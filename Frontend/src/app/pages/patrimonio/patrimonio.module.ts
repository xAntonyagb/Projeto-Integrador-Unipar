import { NgModule, OnInit } from '@angular/core';
import { PatrimonioComponent } from './patrimonio.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PesquisarPatrimonioComponent } from '../../modals/Cadastro/cadastrar-ambiente/pesquisar-patrimonio/pesquisar-patrimonio.component';
import { Button } from 'primeng/button';
import { CadastrarCategoriaModule } from '../../modals/Cadastro/cadastrar-categoria/cadastrar-categoria.module';
import { HeaderModule } from '../menus-tela/header/header.module';
import { MenuModule } from 'primeng/menu';
import { SidebarModule } from '../menus-tela/sidebar/sidebar.module';
import {
  MatTable,
  MatTableDataSource,
  MatTableModule,
} from '@angular/material/table';
import { PatrimonioService } from '../../services/patrimonio.service';
import { PatrimonioResponse } from '../../dtos/responses/Patrimonio.response';
import { CadastrarPatrimonioComponent } from '../../modals/Cadastro/cadastrar-patrimonio/cadastrar-patrimonio.component';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';

@NgModule({
  declarations: [PatrimonioComponent, CadastrarPatrimonioComponent],
  imports: [
    BrowserAnimationsModule,
    FormsModule,
    Button,
    HeaderModule,
    MenuModule,
    ReactiveFormsModule,
    SidebarModule,
    MatPaginatorModule,
    MatTableModule,
    MatSortModule,
  ],
  exports: [PatrimonioComponent],
})
export class PatrimonioModule {}
