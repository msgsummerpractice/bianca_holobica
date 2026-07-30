import { Component } from '@angular/core';

@Component({
  selector: 'app-breeds',
  standalone: true,
  template: `
    <div class="min-h-screen bg-pink-50 p-8 pt-[96px] text-center">
      <h1 class="text-3xl font-bold text-gray-800 mb-4">Dog breeds</h1>
      <p class="text-gray-600">
        The list with all existing dog breeds: Golden Retriever, German Shepherd, French Bulldog.
      </p>
    </div>
  `,
})
export class BreedsComponent {}
