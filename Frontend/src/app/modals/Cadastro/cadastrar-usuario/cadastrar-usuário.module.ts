import { NgModule } from '@angular/core';
import { CadastrarUsuarioComponent } from './cadastrar-usuario.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { GenericModalModule } from '../../generic-modal/generic-modal.module';

@NgModule({
  declarations: [CadastrarUsuarioComponent],
  imports: [
    BrowserAnimationsModule, 
    FormsModule, 
    ReactiveFormsModule,
    GenericModalModule
  ],
  exports: [CadastrarUsuarioComponent],
})
export class CadastrarUsuarioModule {}
