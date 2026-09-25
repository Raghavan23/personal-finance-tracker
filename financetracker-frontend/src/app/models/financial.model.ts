import { TransactionType } from '../enums/transaction-type.enum';

export interface User {
  id: number;
  username: string;
  email: string;
  createdAt?: string;
  updatedAt?: string;
}

export interface Category {
  id: number;
  name: string;
  type: TransactionType;
  icon?: string;
  color?: string;
}

export interface Transaction {
  id: number;
  title: string;
  amount: number;
  type: TransactionType;
  date: string;
  category: Category;
  description?: string;
  userId?: number;
}

export interface MonthlySummary {
  month: string;
  totalIncome: number;
  totalExpense: number;
  netSavings: number;
}

export interface CategorySummary {
  categoryId: number;
  categoryName: string;
  totalAmount: number;
  percentage: number;
}
