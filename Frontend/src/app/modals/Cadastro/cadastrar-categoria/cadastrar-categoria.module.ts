import { NgModule } from '@angular/core';
import { CadastrarCategoriaComponent } from './cadastrar-categoria.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [CadastrarCategoriaComponent],
  imports: [BrowserAnimationsModule, FormsModule],
  exports: [CadastrarCategoriaComponent],
})
export class CadastrarCategoriaModule {}
