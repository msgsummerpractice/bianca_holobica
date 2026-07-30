import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-not-found',
  standalone: true,
  imports: [RouterLink, MatButtonModule, MatIconModule],
  template: `
    <div class="min-h-screen flex flex-col items-center justify-center bg-pink-50 p-6 text-center">
      <mat-icon class="!w-24 !h-24 !text-8xl text-pink-500 mb-4">pets</mat-icon>
      <h1 class="text-6xl font-extrabold text-gray-900 mb-2">404</h1>
      <h2 class="text-2xl font-semibold text-gray-700 mb-4">Page not found.</h2>
      <a mat-raised-button color="primary" routerLink="/">
        <mat-icon class="mr-1">home</mat-icon>
        Back to Dog Gallery
      </a>
    </div>
  `,
})
export class NotFoundComponent {}
