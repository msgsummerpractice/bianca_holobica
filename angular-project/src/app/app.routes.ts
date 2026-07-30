import { Routes } from '@angular/router';
import { LoginComponent } from './login.component';
import { NotFoundComponent } from './not-found.component';
import { HomeComponent } from './home.component';

export const routes: Routes = [
  { path: 'login', loadComponent: () => import('./login.component').then((m) => m.LoginComponent) },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '404', component: NotFoundComponent },
  { path: 'home', component: HomeComponent },
  { path: '**', component: NotFoundComponent },
];
