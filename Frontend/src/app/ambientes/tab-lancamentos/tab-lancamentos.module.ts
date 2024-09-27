import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { TabViewModule } from "primeng/tabview";
import { NgModule } from "@angular/core";
import { TabLancamentosComponent } from "./tab-lancamentos.component";

@NgModule({
  declarations:[TabLancamentosComponent],
  imports:[
    BrowserAnimationsModule,
    TabViewModule
  ],
  exports:[TabLancamentosComponent]
  })
  export class TabLancamentosModule{}
