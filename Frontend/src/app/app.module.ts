import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { FeatureModule } from './FeatureModule';
import { TokenInterceptor } from './infra/auth/token.interceptor';
import { MatPaginatorIntl } from '@angular/material/paginator';
import { MyMatPaginatorIntl } from './services/paginacao.service';
import { EditarBlocoComponent } from './modals/Editar/editar-bloco/editar-bloco.component';
import { EditarCategoriaComponent } from './modals/Editar/editar-categoria/editar-categoria.component';

@NgModule({
  declarations: [AppComponent],
  imports: [FeatureModule],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
    },
    {
      provide: MatPaginatorIntl,
      useClass: MyMatPaginatorIntl,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
