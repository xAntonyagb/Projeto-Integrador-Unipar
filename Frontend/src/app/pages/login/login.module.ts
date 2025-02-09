import { NgModule } from '@angular/core';
import { LoginComponent } from './login.component';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [LoginComponent],
  imports: [FormsModule, BrowserAnimationsModule],
  exports: [LoginComponent],
})
export class LoginModule {}
