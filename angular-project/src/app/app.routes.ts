import { Routes } from '@angular/router';
import { LoginComponent } from './login.component';
import { NotFoundComponent } from './not-found.component';
import { HomeComponent } from './home.component';
import { BreedsComponent } from './breeds.component';
import { AboutComponent } from './about.component';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'breeds', component: BreedsComponent },
  { path: 'about', component: AboutComponent },
  { path: 'login', loadComponent: () => import('./login.component').then((m) => m.LoginComponent) },
  { path: '404', component: NotFoundComponent },
  { path: '**', component: NotFoundComponent },
];
