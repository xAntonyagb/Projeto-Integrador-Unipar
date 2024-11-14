import { NgModule } from "@angular/core";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { CadastrarTarefaComponent } from "./cadastrar-tarefa.component";

@NgModule({
  declarations: [CadastrarTarefaComponent],
    imports: [
        BrowserAnimationsModule,
        FormsModule,
        ReactiveFormsModule,
    ],
  exports: [CadastrarTarefaComponent],
}) export class CadastrarTarefaModule {

}
