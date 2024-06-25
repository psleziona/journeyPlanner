import { Routes } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {authGuard} from "./_guard/auth.guard";
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";

export const routes: Routes = [
  {path: "home", component: HomeComponent, canActivate: [authGuard]},
  {path: "login", component: LoginComponent},
  {path: "register", component: RegisterComponent},
  {path: '', component: HomeComponent, canActivate: [authGuard]},
];
