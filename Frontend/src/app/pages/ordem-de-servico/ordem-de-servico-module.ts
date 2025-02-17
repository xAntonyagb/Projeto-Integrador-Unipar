import { NgModule } from '@angular/core';
import { OrdemDeServicoComponent } from './ordem-de-servico.component';
import { MatTabsModule } from '@angular/material/tabs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TabViewModule } from 'primeng/tabview';
import { TabBlocoModule } from '../ambientes/tab-bloco/tab-bloco.module';
import { ButtonModule } from 'primeng/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MenuModule } from 'primeng/menu';
import { TabOrdemDeServicoModule } from './tab-ordem-de-servico/tab-ordem-de-servico.module';
import { CadastrarOrdemModule } from '../../modals/Cadastro/cadastrar-ordem/cadastrar-ordem.module';
import { FormsModule } from '@angular/forms';
import { DropdownModule } from 'primeng/dropdown';
import { DividerModule } from 'primeng/divider';
import { DialogModule } from 'primeng/dialog';
import { RouterOutlet } from '@angular/router';
import { NavbarModule } from '../../shared/navbar/navbar.module';

@NgModule({
  declarations: [OrdemDeServicoComponent],
  imports: [
    NavbarModule,
    MatTabsModule,
    BrowserAnimationsModule,
    DropdownModule,
    DividerModule,
    DialogModule,
    FormsModule,
    TabViewModule,
    TabBlocoModule,
    ButtonModule,
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    MenuModule,
    RouterOutlet,
    TabOrdemDeServicoModule,
    CadastrarOrdemModule,
  ],
  exports: [OrdemDeServicoComponent],
})
export class OrdemDeServicoModule {}
