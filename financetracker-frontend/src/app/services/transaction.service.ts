import { Injectable, inject, signal } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from '../../environments/environment';
import { API_ENDPOINTS } from '../config';
import { Transaction, MonthlySummary, CategorySummary } from '../models';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = environment.apiUrl;

  readonly transactions = signal<Transaction[]>([]);
  readonly isLoading = signal<boolean>(false);

  getTransactions(params?: { from?: string; to?: string; categoryId?: number }): Observable<Transaction[]> {
    let httpParams = new HttpParams();
    if (params?.from) httpParams = httpParams.set('from', params.from);
    if (params?.to) httpParams = httpParams.set('to', params.to);
    if (params?.categoryId) httpParams = httpParams.set('categoryId', params.categoryId.toString());

    this.isLoading.set(true);
    return this.http.get<Transaction[]>(`${this.baseUrl}${API_ENDPOINTS.TRANSACTIONS.BASE}`, { params: httpParams }).pipe(
      tap({
        next: (data) => {
          this.transactions.set(data);
          this.isLoading.set(false);
        },
        error: () => this.isLoading.set(false)
      })
    );
  }

  getMonthlySummary(): Observable<MonthlySummary[]> {
    return this.http.get<MonthlySummary[]>(`${this.baseUrl}${API_ENDPOINTS.TRANSACTIONS.SUMMARY_MONTHLY}`);
  }

  getCategorySummary(): Observable<CategorySummary[]> {
    return this.http.get<CategorySummary[]>(`${this.baseUrl}${API_ENDPOINTS.TRANSACTIONS.SUMMARY_CATEGORY}`);
  }
}
