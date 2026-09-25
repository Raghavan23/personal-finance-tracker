import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CurrencyFormatterPipe } from '../../pipes';
import { TransactionService } from '../../services';

@Component({
  selector: 'app-transactions',
  standalone: true,
  imports: [CommonModule, CurrencyFormatterPipe],
  templateUrl: './transactions.component.html',
  styleUrl: './transactions.component.css'
})
export class TransactionsComponent implements OnInit {
  protected readonly transactionService = inject(TransactionService);

  ngOnInit(): void {
    this.transactionService.getTransactions().subscribe();
  }
}
