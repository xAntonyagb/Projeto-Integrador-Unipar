import { NgModule } from "@angular/core";
import { CadastrarAmbienteComponent } from "./cadastrar-ambiente.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BlocoRequest} from "../../../dtos/requests/bloco.request";
import {PatrimonioRequest} from "../../../dtos/requests/patrimonio.request";


@NgModule({
    declarations: [CadastrarAmbienteComponent],
    imports: [
        BrowserAnimationsModule,
        FormsModule,
        ReactiveFormsModule
    ],
    providers:[BlocoRequest,PatrimonioRequest],
    exports: [CadastrarAmbienteComponent]
}) export class CadastrarAmbienteModule {

}
