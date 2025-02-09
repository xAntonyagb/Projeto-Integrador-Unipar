import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TabViewModule } from 'primeng/tabview';
import { NgModule } from '@angular/core';
import { TabBlocoComponent } from './tab-bloco.component';
import { ButtonModule } from 'primeng/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MenuModule } from 'primeng/menu';
import { TableModule } from 'primeng/table';
import { CadastrarBlocoModule } from '../../../modals/Cadastro/cadastrar-bloco/cadastrar-bloco.module';
import { EditarBlocoModule } from '../../../modals/Editar/editar-bloco/editar-bloco.module';

@NgModule({
  declarations: [TabBlocoComponent],
  imports: [
    BrowserAnimationsModule,
    TabViewModule,
    ButtonModule,
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    MenuModule,
    TableModule,
    CadastrarBlocoModule,
    EditarBlocoModule,
  ],
  exports: [TabBlocoComponent],
})
export class TabBlocoModule {}
