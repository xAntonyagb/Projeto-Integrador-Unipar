import { NgModule } from "@angular/core";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { FormsModule } from "@angular/forms";
import { CadastrarTarefaComponent } from "./cadastrar-tarefa.component";

@NgModule({
  declarations: [CadastrarTarefaComponent],
  imports: [
    BrowserAnimationsModule,
    FormsModule,
  ],
  exports: [CadastrarTarefaComponent],
}) export class CadastrarTarefaModule {

}
