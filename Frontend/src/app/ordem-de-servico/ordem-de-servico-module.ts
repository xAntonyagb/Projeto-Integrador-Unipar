import { NgModule } from "@angular/core";
import { OrdemDeServicoComponent } from "./ordem-de-servico.component";
import { SidebarModule } from "../sidebar/sidebar.module";
import { HeaderModule } from "../header/header.module";
import { MatTabsModule } from "@angular/material/tabs";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { TabViewModule } from "primeng/tabview";
import { TabAmbientesModule } from "../ambientes/tab-ambientes/tab-ambientes.module";
import { TabBlocoModule } from "../ambientes/tab-bloco/tab-bloco.module";
import { TabLancamentosModule } from "../ambientes/tab-lancamentos/tab-lancamentos.module";
import { ButtonModule } from "primeng/button";
import { MatIconModule } from "@angular/material/icon";
import { MatMenuModule } from "@angular/material/menu";
import { MatButtonModule } from "@angular/material/button";
import { MenuModule } from "primeng/menu";
import { TabOrdemDeServicoModule } from "./tab-ordem-de-servico/tab-ordem-de-servico.module";

@NgModule({
  declarations: [OrdemDeServicoComponent],
  imports: [
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
  TabOrdemDeServicoModule
],
  exports: [OrdemDeServicoComponent]
})
export class OrdemDeServicoModule {

}
