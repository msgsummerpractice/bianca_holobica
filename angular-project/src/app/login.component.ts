import { Component } from '@angular/core';
import { AuthService } from './auth.service';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [MatButtonModule, MatIconModule],
  template: `<div class="min-h-screen flex items-center justify-center bg-gray-100 p-4 pt-[96px]">
    <div class="max-w-md w-full bg-white rounded-lg shadow-md p-6 text-center">
      <h2 class="text-2xl font-bold text-gray-800 mb-4">Authenticate</h2>

      @if (authService.isAuthenticated()) {
        <p class="text-green-600 font-medium mb-4">You are successfully authenticated!</p>
        <button mat-raised-button color="warn" (click)="logout()">
          <mat-icon class="mr-1">logout</mat-icon> Logout
        </button>
      } @else {
        <p class="text-gray-600 mb-6">Click the button below to authenticate MOCK.</p>
        <button mat-raised-button color="primary" (click)="login()">
          <mat-icon class="mr-1">login</mat-icon> Login
        </button>
      }
    </div>
  </div>`,
})
export class LoginComponent {
  authService = inject(AuthService);
  router = inject(Router);

  login(): void {
    this.authService.login();
    this.router.navigate(['/breeds']);
  }

  logout(): void {
    this.authService.logout();
  }
}
