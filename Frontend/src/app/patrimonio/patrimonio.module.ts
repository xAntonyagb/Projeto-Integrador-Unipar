import {NgModule} from "@angular/core";
import {PatrimonioComponent} from "./patrimonio.component";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [PatrimonioComponent],
  imports: [
    BrowserAnimationsModule,
    FormsModule
  ],
  exports: [PatrimonioComponent]
}) export class PatrimonioModule{}
