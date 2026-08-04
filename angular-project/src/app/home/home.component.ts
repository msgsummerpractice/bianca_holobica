import { Component, OnInit, inject, signal } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { DogService } from '../services/dog.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatButtonModule, MatIconModule],
  templateUrl: './home.html',
})
export class HomeComponent implements OnInit {
  private dogService = inject(DogService);

  dogImages = signal<string[]>([]);
  isLoading = signal<boolean>(false);

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
