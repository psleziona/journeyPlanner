import { Routes } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {authGuard} from "./_guard/auth.guard";
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {TripsComponent} from "./components/trips/trips.component";
import {TripComponent} from "./components/trip/trip.component";
import {PhotosComponent} from "./components/photos/photos.component";

export const routes: Routes = [
  {path: "home", component: HomeComponent, canActivate: [authGuard]},
  {path: "login", component: LoginComponent},
  {path: "register", component: RegisterComponent},
  {path: "trips", component: TripsComponent },
  {path: "trip/:id", component: TripComponent},
  {path: "photos", component: PhotosComponent },
  {path: '', component: HomeComponent, canActivate: [authGuard]},
];
