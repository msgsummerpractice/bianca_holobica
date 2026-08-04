import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { HomeComponent } from './home/home.component';
import { BreedsComponent } from './breeds/breeds.component';
import { AboutComponent } from './about/about.component';
import { authGuard } from './auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'breeds', component: BreedsComponent, canActivate: [authGuard] },
  { path: 'about', component: AboutComponent },
  {
    path: 'login',
    loadComponent: () => import('./login/login.component').then((m) => m.LoginComponent),
  },
  { path: '404', component: NotFoundComponent },
  { path: '**', redirectTo: 'home', pathMatch: 'full' },
];
