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
import {MatSort, Sort, MatSortModule} from '@angular/material/sort';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';

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
    TableModule
  ],
  exports:[TabAmbientesComponent]
  })
  export class TabAmbientesModule{}
