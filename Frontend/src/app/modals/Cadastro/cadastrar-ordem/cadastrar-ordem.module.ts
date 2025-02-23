import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TabViewModule } from 'primeng/tabview';
import { ButtonModule } from 'primeng/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import { MenuModule } from 'primeng/menu';
import { TableModule } from 'primeng/table';
import { FormsModule } from '@angular/forms';
import { CadastrarOrdemComponent } from './cadastrar-ordem.component';
import { DropdownModule } from 'primeng/dropdown';
import { DividerModule } from 'primeng/divider';
import { DialogModule } from 'primeng/dialog';
import { ModalHeaderModule } from "../../modal-header/modal-header.module";

@NgModule({
  declarations: [CadastrarOrdemComponent],
  imports: [
    BrowserAnimationsModule,
    FormsModule,
    TabViewModule,
    ButtonModule,
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    MatTableModule,
    MatSortModule,
    MenuModule,
    TableModule,
    DropdownModule,
    DividerModule,
    DialogModule,
    ModalHeaderModule
],

  exports: [CadastrarOrdemComponent],
})
export class CadastrarOrdemModule {}
 