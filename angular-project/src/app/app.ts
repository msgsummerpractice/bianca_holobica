import { Component, OnInit, effect, inject, signal } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { DogService } from './dog.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [MatToolbarModule, MatButtonModule, MatIconModule, MatMenuModule],
  templateUrl: './app.html',
})
export class App implements OnInit {
  private dogService = inject(DogService);

  dogImages = signal<string[]>([]);
  isLoading = signal<boolean>(false);

  constructor() {
    effect(() => {
      console.log('The signal changed. The new image is:', this.dogImages());
    });
  }

  ngOnInit(): void {
    this.fetchDogImage();
  }

  fetchDogImage(): void {
    this.isLoading.set(true);

    this.dogService.getDogs().subscribe({
      next: (response) => {
        this.dogImages.set(response.message);
        this.isLoading.set(false);
      },
      error: (err) => {
        console.error('Error while fetching dog images:', err);
        this.isLoading.set(false);
      },
    });
  }
}
