import { NgModule } from "@angular/core";
import { CadastrarUsuarioComponent } from "./cadastrar-usuario.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { FormsModule } from "@angular/forms";

@NgModule({
    declarations:[CadastrarUsuarioComponent],
    imports:[ 
        BrowserAnimationsModule,
        FormsModule
    ],
    exports:[CadastrarUsuarioComponent]
}) 
    export class CadastrarUsuarioModule{

    }