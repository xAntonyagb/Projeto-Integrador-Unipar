import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { CadastrarBlocoComponent } from './cadastrar-bloco.component';

@NgModule({
  declarations: [CadastrarBlocoComponent],
  imports: [BrowserAnimationsModule, FormsModule],
  exports: [CadastrarBlocoComponent],
})
export class CadastrarBlocoModule {}
