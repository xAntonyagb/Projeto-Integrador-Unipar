import { NgModule } from "@angular/core";
import { AmbientesComponent } from "./ambientes.component";
import { HeaderModule } from "../header/header.module";
import { SidebarModule } from "../sidebar/sidebar.module";
import { MatTabsModule } from "@angular/material/tabs";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { TabViewModule } from 'primeng/tabview';
import { TabAmbientesModule } from "./tab-ambientes/tab-ambientes.module";
import { TabBlocoModule } from "./tab-bloco/tab-bloco.module";
import { TabLancamentosModule } from "./tab-lancamentos/tab-lancamentos.module";
import { ButtonModule } from 'primeng/button';
import { MatIconModule } from "@angular/material/icon";
import { MatMenuModule } from "@angular/material/menu";
import { MatButtonModule } from "@angular/material/button";
import { MenuModule } from "primeng/menu";
import { CadastrarOrdemModule } from "../modals/Cadastro/cadastrar-ordem/cadastrar-ordem.module";
@NgModule({
declarations:[AmbientesComponent],
imports:[
  HeaderModule,
  SidebarModule,
  MatTabsModule,
  BrowserAnimationsModule,
  TabViewModule,
  TabAmbientesModule,
  TabBlocoModule,
  TabLancamentosModule,
  ButtonModule,
  MatIconModule,
  MatMenuModule,
  MatButtonModule,
  MenuModule,
  CadastrarOrdemModule
],
exports:[AmbientesComponent]
})
export class AmbientesModule{}
