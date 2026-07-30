import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  standalone: true,
  template: `<div class="min-h-screen flex items-center justify-center bg-gray-100 p-4">
    <div class="max-w-md w-full bg-white rounded-lg shadow-md p-6">
      <h2 class="text-2xl font-bold text-center text-gray-800 mb-6">Autentificare</h2>
      <p class="text-gray-600 text-center mb-4">Login</p>
    </div>
  </div>`,
})
export class LoginComponent {}
