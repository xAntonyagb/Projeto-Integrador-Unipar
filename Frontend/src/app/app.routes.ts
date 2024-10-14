import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { InicioComponent } from './inicio/inicio.component';
import { authGuard } from './auth/auth.guard';
import { AmbientesComponent } from './ambientes/ambientes.component';
import { OrdemDeServicoComponent } from './ordem-de-servico/ordem-de-servico.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent},
    {path: 'inicio', component: InicioComponent, canActivate: [authGuard]},
    { path: 'ambientes', component: AmbientesComponent, canActivate: [authGuard]},
    { path: 'ordem-servico', component: OrdemDeServicoComponent, canActivate: [authGuard]},
    { path: '', redirectTo: '/login', pathMatch: 'full', }
  ];
