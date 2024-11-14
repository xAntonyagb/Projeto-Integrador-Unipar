import { MatTableModule } from '@angular/material/table';
import {ModuleWithProviders, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { MatTabsModule } from '@angular/material/tabs';
import { BrowserAnimationsModule } from'@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { ButtonModule } from 'primeng/button';
import { NotificationBarModule } from './notification-bar/notification-bar.module';
import { InicioModule } from './inicio/inicio.module';
import {RouterModule, RouterOutlet, Routes} from '@angular/router';
import {HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AmbientesModule } from './ambientes/ambientes.module';
import { SidebarModule } from './sidebar/sidebar.module';
import { TabBlocoModule } from './ambientes/tab-bloco/tab-bloco.module';
import { TabAmbientesModule } from './ambientes/tab-ambientes/tab-ambientes.module';
import { TabLancamentosModule } from './ambientes/tab-lancamentos/tab-lancamentos.module';
import { MenuModule } from 'primeng/menu';
import { MatSortModule } from '@angular/material/sort';
import { OrdemDeServicoModule } from './ordem-de-servico/ordem-de-servico-module';
import { CadastrarOrdemModule } from './modals/Cadastro/cadastrar-ordem/cadastrar-ordem.module';
import { ToastrModule } from 'ngx-toastr';
import { CadastrarCategoriaModule } from './modals/Cadastro/cadastrar-categoria/cadastrar-categoria.module';
import { CadastrarBlocoModule } from './modals/Cadastro/cadastrar-bloco/cadastrar-bloco.module';
import { CadastrarTarefaModule } from './modals/Cadastro/cadastrar-tarefa/cadastrar-tarefa.module';
import { CadastrarUsuarioModule } from './modals/Cadastro/cadastrar-usuario/cadastrar-usuário.module';
import { CadastrarAmbienteModule } from './modals/Cadastro/cadastrar-ambiente/cadastrar-ambiente.module';
import { CategoriaModule } from './categoria/categoria.module';
import {LoginModule} from "./login/login.module";
import {TarefasModule} from "./tarefas/tarefa.module";
import {UsuariosModule} from "./usuarios/usuarios.module";
import {ArquivadosModule} from "./arquivados/arquivados.module";
import {PatrimonioModule} from "./patrimonio/patrimonio.module";

const toastrModule: ModuleWithProviders<ToastrModule> = ToastrModule.forRoot({
  timeOut: 2100,
  positionClass: 'toast-top-right',
  preventDuplicates: true,
});

@NgModule({
    imports:[
        RouterOutlet,
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
        CadastrarCategoriaModule,
        CadastrarBlocoModule,
        CadastrarTarefaModule,
        CadastrarUsuarioModule,
        CadastrarAmbienteModule,
        CategoriaModule,
        LoginModule,
        TarefasModule,
        UsuariosModule,
        ArquivadosModule,
        PatrimonioModule,
        toastrModule,
      ],
    exports: [
      RouterOutlet,
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
      CadastrarCategoriaModule,
      CadastrarBlocoModule,
      CadastrarTarefaModule,
      CadastrarUsuarioModule,
      CadastrarAmbienteModule,
      CategoriaModule,
      LoginModule,
      TarefasModule,
      UsuariosModule,
      ArquivadosModule,
      PatrimonioModule,
    ],
}) export class FeatureImportsModule {}
