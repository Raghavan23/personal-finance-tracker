import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StatCardComponent } from '../../components';
import { CurrencyFormatterPipe } from '../../pipes';
import { TransactionService } from '../../services';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, StatCardComponent, CurrencyFormatterPipe],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {
  protected readonly transactionService = inject(TransactionService);

  readonly totalBalance = signal<number>(12450.75);
  readonly monthlyIncome = signal<number>(4500.00);
  readonly monthlyExpense = signal<number>(2150.25);
  readonly savingsRate = signal<string>('52.2%');

  ngOnInit(): void {
    // Initial data load hook
  }
}
