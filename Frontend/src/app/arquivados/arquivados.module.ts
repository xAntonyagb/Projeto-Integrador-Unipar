import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ButtonModule } from 'primeng/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MenuModule } from 'primeng/menu';
import {SidebarModule} from "../sidebar/sidebar.module";
import {HeaderModule} from "../header/header.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ArquivadosComponent} from "./arquivados.component";
import {CadastrarCategoriaModule} from "../modals/Cadastro/cadastrar-categoria/cadastrar-categoria.module";
import {MatTable, MatTableModule} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";

@NgModule({
  declarations: [ArquivadosComponent],
    imports: [
        FormsModule,
        BrowserAnimationsModule,
        ReactiveFormsModule,
        ButtonModule,
        MatIconModule,
        MatMenuModule,
        MatButtonModule,
        MenuModule,
        SidebarModule,
        HeaderModule,
        MatTableModule,
        MatPaginator,
    ],
  exports: [ArquivadosComponent]
}) export class ArquivadosModule {}
