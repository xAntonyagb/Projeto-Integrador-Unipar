import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { InicioComponent } from './pages/inicio/inicio.component';
import { authGuard } from './infra/auth/auth.guard';
import { AmbientesComponent } from './pages/ambientes/ambientes.component';
import { OrdemDeServicoComponent } from './pages/ordem-de-servico/ordem-de-servico.component';
import {TabBlocoComponent} from "./pages/ambientes/tab-bloco/tab-bloco.component";
import {TabAmbientesComponent} from "./pages/ambientes/tab-ambientes/tab-ambientes.component";
import {TarefasComponent} from "./pages/tarefas/tarefas.component";
import {CategoriaComponent} from "./pages/categoria/categoria.component";
import {TabTodosOsServicosComponent} from "./pages/ordem-de-servico/tab-todos-os-servicos/tab-todos-os-servicos.component";
import {UsuariosComponent} from "./pages/usuarios/usuarios.component";
import {ArquivadosComponent} from "./pages/arquivados/arquivados.component";
import {TabOrdemDeServicoComponent} from "./pages/ordem-de-servico/tab-ordem-de-servico/tab-ordem-de-servico.component";
import {PatrimonioComponent} from "./pages/patrimonio/patrimonio.component";

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
        { path: 'blocos', component: TabBlocoComponent}
    ]},
    { path: 'ordem-servico', component: OrdemDeServicoComponent, canActivate: [authGuard], children:[
        { path: '', component: TabOrdemDeServicoComponent},
        { path: 'todos-servicos', component: TabTodosOsServicosComponent},
    ]},
];
