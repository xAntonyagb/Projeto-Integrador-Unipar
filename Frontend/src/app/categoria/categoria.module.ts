import { NgModule } from "@angular/core";
import { CategoriaComponent } from "./categoria.component";
import {SidebarModule} from "../sidebar/sidebar.module";
import {HeaderModule} from "../header/header.module";
import {Button} from "primeng/button";
import {MenuModule} from "primeng/menu";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CadastrarCategoriaModule} from "../modals/Cadastro/cadastrar-categoria/cadastrar-categoria.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {EditarCategoriaModule} from "../modals/Editar/editar-categoria/editar-categoria.module";

@NgModule({
    declarations: [CategoriaComponent],
  imports: [
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    CadastrarCategoriaModule,
    EditarCategoriaModule,
    SidebarModule,
    HeaderModule,
    Button,
    MenuModule
  ],
    exports: [CategoriaComponent],
}) export class CategoriaModule {}
