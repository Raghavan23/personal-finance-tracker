import { Routes } from '@angular/router';
import { MainLayoutComponent } from './layout';
import { DashboardComponent, TransactionsComponent } from './pages';

export const routes: Routes = [
  {
    path: '',
    component: MainLayoutComponent,
    children: [
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      },
      {
        path: 'dashboard',
        component: DashboardComponent
      },
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
