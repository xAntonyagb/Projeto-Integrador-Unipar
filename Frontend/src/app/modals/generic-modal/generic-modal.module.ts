import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { GenericModalComponent } from './generic-modal.component';
import { ModalHeaderModule } from "../modal-header/modal-header.module";

@NgModule({
  declarations: [GenericModalComponent],
  imports: [BrowserAnimationsModule, 
    FormsModule, 
    ReactiveFormsModule, 
    ModalHeaderModule
  ],
  providers: [],
  exports: [GenericModalComponent],
})
export class GenericModalModule {}
