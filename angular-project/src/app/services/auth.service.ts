import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { jwtDecode } from 'jwt-decode';
import { User } from '../models/user.model';
import { SignInResponse } from '../models/signInResponse.model';
import { JwtPayload } from '../models/jwtPayload.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private http = inject(HttpClient);
  private router = inject(Router);
  private apiUrl = 'http://localhost:8080/api/auth';

  currentUser = signal<User | null>(null);

  constructor() {
    this.restoreUser();
  }

  login(credentials: {
    email: string;
    password: string;
  }): Observable<{ message: string; mfaCode: string; email: string }> {
    return this.http.post<{ message: string; mfaCode: string; email: string }>(
      `${this.apiUrl}/login`,
      credentials,
    );
  }

  verifyMfa(verifyData: { email: string; code: string }): Observable<SignInResponse> {
    return this.http.post<SignInResponse>(`${this.apiUrl}/verify-mfa`, verifyData);
  }

  saveToken(token: string): void {
    localStorage.setItem('token', token);
    this.restoreUser();
  }

  logout(): void {
    localStorage.removeItem('token');
    this.currentUser.set(null);
    this.router.navigate(['/login']);
  }

  isAuthenticated(): boolean {
    const decoded = this.getDecodedToken();
    if (!decoded) return false;
    return !!decoded.exp && decoded.exp * 1000 > Date.now();
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  private restoreUser(): void {
    const decoded = this.getDecodedToken();
    if (decoded && this.isAuthenticated()) {
      this.currentUser.set({
        email: decoded.sub,
        roles: decoded.roles || [],
      });
    } else {
      this.currentUser.set(null);
    }
  }

  private getDecodedToken(): JwtPayload | null {
    const token = this.getToken();
    if (!token) return null;
    try {
      return jwtDecode<JwtPayload>(token);
    } catch {
      return null;
    }
  }
}
