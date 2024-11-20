import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { InicioComponent } from './inicio/inicio.component';
import { authGuard } from './auth/auth.guard';
import { AmbientesComponent } from './ambientes/ambientes.component';
import { OrdemDeServicoComponent } from './ordem-de-servico/ordem-de-servico.component';
import {TabBlocoComponent} from "./ambientes/tab-bloco/tab-bloco.component";
import {TabLancamentosComponent} from "./ambientes/tab-lancamentos/tab-lancamentos.component";
import {TabAmbientesComponent} from "./ambientes/tab-ambientes/tab-ambientes.component";
import {TarefasComponent} from "./tarefas/tarefas.component";
import {CategoriaComponent} from "./categoria/categoria.component";
import {TabTodosOsServicosComponent} from "./ordem-de-servico/tab-todos-os-servicos/tab-todos-os-servicos.component";
import {UsuariosComponent} from "./usuarios/usuarios.component";
import {ArquivadosComponent} from "./arquivados/arquivados.component";
import {TabOrdemDeServicoComponent} from "./ordem-de-servico/tab-ordem-de-servico/tab-ordem-de-servico.component";
import {PatrimonioComponent} from "./patrimonio/patrimonio.component";

export const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full', },
    { path: 'login', component: LoginComponent},
    { path: 'inicio', component: InicioComponent, canActivate: [authGuard]},
    { path: 'tarefas', component: TarefasComponent, canActivate: [authGuard]},
    { path: 'categorias', component: CategoriaComponent, canActivate: [authGuard]},
    { path: 'usuarios', component: UsuariosComponent, canActivate: [authGuard]},
    { path: 'arquivados', component: ArquivadosComponent, canActivate: [authGuard]},
    { path: 'patrimonios', component:PatrimonioComponent, canActivate: [authGuard]},
    { path: 'ambientes', component: AmbientesComponent, canActivate: [authGuard], children: [
        { path: '', component: TabAmbientesComponent},
        { path: 'blocos', component: TabBlocoComponent},
        { path: 'lancamentos', component: TabLancamentosComponent}
    ]},
    { path: 'ordem-servico', component: OrdemDeServicoComponent, canActivate: [authGuard], children:[
        { path: '', component: TabOrdemDeServicoComponent},
        { path: 'todos-servicos', component: TabTodosOsServicosComponent},
    ]},
];
