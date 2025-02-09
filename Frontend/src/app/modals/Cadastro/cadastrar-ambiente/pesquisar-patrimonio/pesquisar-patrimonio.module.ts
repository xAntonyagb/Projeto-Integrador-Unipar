import { NgModule } from '@angular/core';
import { PesquisarPatrimonioComponent } from './pesquisar-patrimonio.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { DynamicDialogModule } from 'primeng/dynamicdialog';

@NgModule({
  declarations: [PesquisarPatrimonioComponent],
  imports: [
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    DynamicDialogModule,
    ToastModule,
    ButtonModule,
  ],
  exports: [PesquisarPatrimonioComponent],
})
export class PesquisarPatrimonioModule {}
