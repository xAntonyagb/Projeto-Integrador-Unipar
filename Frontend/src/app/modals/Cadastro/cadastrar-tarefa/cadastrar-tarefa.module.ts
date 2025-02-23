import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CadastrarTarefaComponent } from './cadastrar-tarefa.component';
import { DropdownModule } from 'primeng/dropdown';
import { CalendarModule } from 'primeng/calendar';
import { RadioButtonModule } from 'primeng/radiobutton';
import { GenericModalModule } from "../../generic-modal/generic-modal.module";

@NgModule({
  declarations: [CadastrarTarefaComponent],
  imports: [
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    DropdownModule,
    CalendarModule,
    RadioButtonModule,
    GenericModalModule
],
  exports: [CadastrarTarefaComponent],
})
export class CadastrarTarefaModule {}
