import { NgModule } from '@angular/core';
import { DeletarAmbienteComponent } from './deletar-ambiente.component';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { AvatarModule } from 'primeng/avatar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [DeletarAmbienteComponent],
  imports: [
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    DialogModule,
    ButtonModule,
    InputTextModule,
    AvatarModule,
  ],
  exports: [DeletarAmbienteComponent],
})
export class DeletarAmbienteModule {}
