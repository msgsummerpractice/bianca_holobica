import { Component } from '@angular/core';

@Component({
  selector: 'app-about',
  standalone: true,
  template: `
    <div class="min-h-screen bg-pink-50 p-8 pt-[96px] text-center">
      <h1 class="text-3xl font-bold text-gray-800 mb-4">Despre Dog Gallery</h1>
      <p class="text-gray-600">A web page created for dog overs.</p>
    </div>
  `,
})
export class AboutComponent {}
