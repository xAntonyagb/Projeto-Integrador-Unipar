import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component'; // Importe o LoginComponent
import { InicioComponent } from './inicio/inicio.component';
import { authGuard } from './auth/auth.guard';
import { AmbientesComponent } from './ambientes/ambientes.component';



const routes: Routes = [
  { path: 'login', component: LoginComponent},
  {path: 'inicio', component: InicioComponent, canActivate: [authGuard]},
  { path: 'ambientes', component: AmbientesComponent, canActivate: [authGuard]},
  { path: '', redirectTo: '/login', pathMatch: 'full', }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
