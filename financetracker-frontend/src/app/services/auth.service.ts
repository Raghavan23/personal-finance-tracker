import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from '../../environments/environment';
import { API_ENDPOINTS, APP_CONFIG } from '../config';
import { User } from '../models';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = environment.apiUrl;

  readonly currentUser = signal<User | null>(null);
  readonly isAuthenticated = signal<boolean>(this.hasToken());

  private hasToken(): boolean {
    return !!localStorage.getItem(APP_CONFIG.STORAGE_KEYS.AUTH_TOKEN);
  }

  logout(): void {
    localStorage.removeItem(APP_CONFIG.STORAGE_KEYS.AUTH_TOKEN);
    localStorage.removeItem(APP_CONFIG.STORAGE_KEYS.REFRESH_TOKEN);
    this.currentUser.set(null);
    this.isAuthenticated.set(false);
  }
}
