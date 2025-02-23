import { NgModule } from '@angular/core';
import { CadastrarAmbienteComponent } from './cadastrar-ambiente.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BlocoService } from '../../../services/bloco.service';
import { PatrimonioService } from '../../../services/patrimonio.service';
import { GenericModalModule } from '../../generic-modal/generic-modal.module';

@NgModule({
  declarations: [CadastrarAmbienteComponent],
  imports: [
    BrowserAnimationsModule, 
    FormsModule, 
    ReactiveFormsModule,
    GenericModalModule
  ],
  providers: [BlocoService, PatrimonioService],
  exports: [CadastrarAmbienteComponent],
})
export class CadastrarAmbienteModule {}
