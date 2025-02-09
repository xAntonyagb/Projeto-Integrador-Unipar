import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TabViewModule } from 'primeng/tabview';
import { NgModule } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MenuModule } from 'primeng/menu';
import { TableModule } from 'primeng/table';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { TabOrdemDeServicoComponent } from './tab-ordem-de-servico.component';
import { CadastrarOrdemModule } from '../../../modals/Cadastro/cadastrar-ordem/cadastrar-ordem.module';
import {
  MatPaginatorIntl,
  MatPaginatorModule,
} from '@angular/material/paginator';
import { DropdownModule } from 'primeng/dropdown';
import { DividerModule } from 'primeng/divider';
import { DialogModule } from 'primeng/dialog';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [TabOrdemDeServicoComponent],
  imports: [
    BrowserAnimationsModule,
    TabViewModule,
    ButtonModule,
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    DropdownModule,
    DividerModule,
    DialogModule,
    MenuModule,
    TableModule,
    CadastrarOrdemModule,
    FormsModule,
  ],
  exports: [TabOrdemDeServicoComponent],
})
export class TabOrdemDeServicoModule {}
