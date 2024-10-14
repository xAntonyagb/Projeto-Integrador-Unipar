import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
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
import { TabOrdemDeServicoComponent } from "./tab-ordem-de-servico.component";

@NgModule({
  declarations:[TabOrdemDeServicoComponent],
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
    TableModule
  ],
  exports:[TabOrdemDeServicoComponent]
  })
  export class TabOrdemDeServicoModule{}
