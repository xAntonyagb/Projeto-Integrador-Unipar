import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ButtonModule } from 'primeng/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MenuModule } from 'primeng/menu';
import {SidebarModule} from "../menus-tela/sidebar/sidebar.module";
import {HeaderModule} from "../menus-tela/header/header.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {UsuariosComponent} from "./usuarios.component";
import {CadastrarUsuarioModule} from "../../modals/Cadastro/cadastrar-usuario/cadastrar-usuário.module";

@NgModule({
  declarations: [UsuariosComponent],
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
    CadastrarUsuarioModule,
  ],
  exports: [UsuariosComponent]
}) export class UsuariosModule {}
