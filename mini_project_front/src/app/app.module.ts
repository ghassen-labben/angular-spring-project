import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { NavComponent } from './nav/nav.component';
import { SearchComponent } from './search/search.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { ErrocomponentComponent } from './errocomponent/errocomponent.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { WorkerdashbordComponent } from './workerdashbord/workerdashbord.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavComponent,
    SearchComponent,
    LoginComponent,
    ProfileComponent,
    RegisterComponent,
    ErrocomponentComponent,
    DashboardComponent,
    WorkerdashbordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
