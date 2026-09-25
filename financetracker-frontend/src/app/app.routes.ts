import { Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { MainLayoutComponent } from './layout';
import { TransactionsComponent } from './pages/transactions/transactions.component';

export const routes: Routes = [
  {
    path: '',
    component: DashboardComponent
  },
  {
    path: 'app',
    component: MainLayoutComponent,
    children: [
      {
        path: 'transactions',
        component: TransactionsComponent
      }
    ]
  },
  {
    path: '**',
    redirectTo: ''
  }
];
