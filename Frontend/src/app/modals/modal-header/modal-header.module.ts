import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ModalHeaderComponent } from './modal-header.component';

@NgModule({
  declarations: [ModalHeaderComponent],
  imports: [
    BrowserAnimationsModule, 
    FormsModule, 
    ReactiveFormsModule
  ],
  providers: [],
  exports: [ModalHeaderComponent],
})
export class ModalHeaderModule {}
