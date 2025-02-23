import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { GenericModalModule } from "../../generic-modal/generic-modal.module";
import { CadastrarPatrimonioComponent } from './cadastrar-patrimonio.component';

@NgModule({
  declarations: [CadastrarPatrimonioComponent],
  imports: [
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    GenericModalModule
],
  exports: [CadastrarPatrimonioComponent],
})
export class CadastrarPatrimonioModule {}
