import {NgModule} from "@angular/core";
import {AppComponent} from "./app.component";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {FeatureModule} from "./FeatureModule";
import {TokenInterceptor} from "./auth/token.interceptor";
import { ArquivadosComponent } from './arquivados/arquivados.component';
import { PatrimonioComponent } from './patrimonio/patrimonio.component';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    FeatureModule,
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
