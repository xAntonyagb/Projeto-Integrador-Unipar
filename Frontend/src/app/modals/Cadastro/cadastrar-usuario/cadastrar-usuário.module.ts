import { NgModule } from '@angular/core';
import { CadastrarUsuarioComponent } from './cadastrar-usuario.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [CadastrarUsuarioComponent],
  imports: [BrowserAnimationsModule, FormsModule, ReactiveFormsModule],
  exports: [CadastrarUsuarioComponent],
})
export class CadastrarUsuarioModule {}
