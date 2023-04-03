import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { SearchComponent } from './search/search.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { ErrocomponentComponent } from './errocomponent/errocomponent.component';
import { ProfileComponent } from './profile/profile.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { WorkerdashbordComponent } from './workerdashbord/workerdashbord.component';

const routes: Routes = [
  { path: 'accueil', title: 'Accueil', component: HomeComponent },
  { path: 'search/:id', title: 'Search', component: SearchComponent },
  { path: 'search/:id/:location', title: 'Search', component: SearchComponent },
  { path: 'profile/:id', title: 'Profile', component: ProfileComponent },
  { path: 'signup', title: 'SignUp', component: RegisterComponent },
  { path: 'login', title: 'Login', component: LoginComponent },
  { path: 'dashboard', title: 'dashboard', component: DashboardComponent },
  { path: 'workerdashboard', title: 'workerdashboard', component: WorkerdashbordComponent },
  { path: '', redirectTo: 'accueil', pathMatch: 'full' },
  { path: '**', title: 'Erreur', component: ErrocomponentComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
