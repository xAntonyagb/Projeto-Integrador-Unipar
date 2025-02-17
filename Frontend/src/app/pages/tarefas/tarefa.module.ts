import { NgModule } from '@angular/core';
import { TarefasComponent } from './tarefas.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ButtonModule } from 'primeng/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MenuModule } from 'primeng/menu';
import { CadastrarTarefaModule } from '../../modals/Cadastro/cadastrar-tarefa/cadastrar-tarefa.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NavbarModule } from '../../shared/navbar/navbar.module';

@NgModule({
  declarations: [TarefasComponent],
  imports: [
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    ButtonModule,
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    MenuModule,
    NavbarModule,
    CadastrarTarefaModule,
  ],
  exports: [TarefasComponent],
})
export class TarefasModule {}
