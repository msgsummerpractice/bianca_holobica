import { Injectable, inject, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Dog {
  message: string[];
  status: string;
}

@Injectable({
  providedIn: 'root',
})
export class DogService {
  private http = inject(HttpClient);
  private apiUrl = 'https://dog.ceo/api/breeds/image/random/3';

  getDogs(): Observable<Dog> {
    return this.http.get<Dog>(this.apiUrl);
  }
}
