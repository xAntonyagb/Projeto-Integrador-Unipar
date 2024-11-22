import {NgModule} from "@angular/core";
import {EditarBlocoComponent} from "./editar-bloco.component";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

@NgModule({
  declarations: [EditarBlocoComponent],
  imports: [
    FormsModule,
    BrowserAnimationsModule,
  ],
  exports: [EditarBlocoComponent],
})export class EditarBlocoModule{}
