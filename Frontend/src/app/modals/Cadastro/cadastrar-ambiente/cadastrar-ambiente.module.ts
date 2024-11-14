import { NgModule } from "@angular/core";
import { CadastrarAmbienteComponent } from "./cadastrar-ambiente.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { FormsModule } from "@angular/forms";


@NgModule({
    declarations: [CadastrarAmbienteComponent],
    imports: [
        BrowserAnimationsModule,
        FormsModule,
    ],
    exports: [CadastrarAmbienteComponent]
}) export class CadastrarAmbienteModule {

}
