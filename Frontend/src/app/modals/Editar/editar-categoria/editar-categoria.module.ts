import { NgModule } from '@angular/core';
import { EditarCategoriaComponent } from './editar-categoria.component';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [EditarCategoriaComponent],
  imports: [FormsModule, BrowserAnimationsModule],
  exports: [EditarCategoriaComponent],
})
export class EditarCategoriaModule {}
