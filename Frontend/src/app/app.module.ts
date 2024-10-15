import { MatTableModule } from '@angular/material/table';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { NotificationBarModule } from './notification-bar/notification-bar.module';
import { InicioModule } from './inicio/inicio.module';
import { RouterModule, Routes } from '@angular/router';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { TokenInterceptor } from './auth/token.interceptor';
import { AppRoutingModule } from './app-routing.module';
import { AmbientesModule } from './ambientes/ambientes.module';
import { SidebarModule } from './sidebar/sidebar.module';
import { MatTabsModule } from '@angular/material/tabs';
import { BrowserAnimationsModule } from'@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { ButtonModule } from 'primeng/button';
import { TabBlocoModule } from './ambientes/tab-bloco/tab-bloco.module';
import { TabAmbientesModule } from './ambientes/tab-ambientes/tab-ambientes.module';
import { TabLancamentosModule } from './ambientes/tab-lancamentos/tab-lancamentos.module';
import { MenuModule } from 'primeng/menu';
import { MatSortModule } from '@angular/material/sort';
import { OrdemDeServicoComponent } from './ordem-de-servico/ordem-de-servico.component';
import { OrdemDeServicoModule } from './ordem-de-servico/ordem-de-servico-module';
import { TabTodosOsServicosComponent } from './ordem-de-servico/tab-todos-os-servicos/tab-todos-os-servicos.component';
import { TabOrdemDeServicoComponent } from './ordem-de-servico/tab-ordem-de-servico/tab-ordem-de-servico.component';
import { CadastrarOrdemModule } from './modals/cadastrar-ordem/cadastrar-ordem.module';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    TabTodosOsServicosComponent,
  ],

  imports: [
    BrowserModule,
    NotificationBarModule,
    AppRoutingModule,
    FormsModule,
    InicioModule,
    HttpClientModule,
    RouterModule,
    AmbientesModule,
    SidebarModule,
    MatTabsModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatMenuModule,
    MatTableModule,
    MatSortModule,
    MatButtonModule,
    ButtonModule,
    TabBlocoModule,
    TabAmbientesModule,
    TabLancamentosModule,
    MenuModule,
    OrdemDeServicoModule,
    CadastrarOrdemModule,
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
