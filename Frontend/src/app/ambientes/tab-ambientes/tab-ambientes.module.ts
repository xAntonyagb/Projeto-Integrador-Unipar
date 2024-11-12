import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { TabAmbientesComponent } from "./tab-ambientes.component";
import { TabViewModule } from "primeng/tabview";
import { NgModule } from "@angular/core";
import { ButtonModule } from 'primeng/button';
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';
import { MenuModule } from 'primeng/menu';
import { TableModule } from 'primeng/table';
import {MatSortModule} from '@angular/material/sort';
import {MatTableModule} from '@angular/material/table';
import { CadastrarOrdemModule } from "../../modals/Cadastro/cadastrar-ordem/cadastrar-ordem.module";

@NgModule({
  declarations:[TabAmbientesComponent],
  imports:[
    BrowserAnimationsModule,
    TabViewModule,
    ButtonModule,
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    MatTableModule,
    MatSortModule,
    MenuModule,
    TableModule,
    CadastrarOrdemModule,
  ],
  exports:[TabAmbientesComponent]
  })
  export class TabAmbientesModule{}
