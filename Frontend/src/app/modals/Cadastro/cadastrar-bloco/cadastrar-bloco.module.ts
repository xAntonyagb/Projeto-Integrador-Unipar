import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { CadastrarBlocoComponent } from './cadastrar-bloco.component';
import { GenericModalModule } from '../../generic-modal/generic-modal.module';

@NgModule({
  declarations: [CadastrarBlocoComponent],
  imports: [
    BrowserAnimationsModule, 
    FormsModule,
    GenericModalModule
  ],
  exports: [CadastrarBlocoComponent],
})
export class CadastrarBlocoModule {}
