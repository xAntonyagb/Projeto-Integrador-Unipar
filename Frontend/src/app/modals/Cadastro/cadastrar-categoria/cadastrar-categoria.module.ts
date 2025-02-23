import { NgModule } from '@angular/core';
import { CadastrarCategoriaComponent } from './cadastrar-categoria.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { GenericModalModule } from '../../generic-modal/generic-modal.module';

@NgModule({
  declarations: [CadastrarCategoriaComponent],
  imports: [
    BrowserAnimationsModule, 
    FormsModule,
    GenericModalModule
  ],
  exports: [CadastrarCategoriaComponent],
})
export class CadastrarCategoriaModule {}
