import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'appCurrency',
  standalone: true
})
export class CurrencyFormatterPipe implements PipeTransform {
  transform(value: number | null | undefined, currencyCode: string = 'USD'): string {
    if (value === null || value === undefined || isNaN(value)) {
      return '$0.00';
    }
    return new Intl.NumberFormat('en-US', {
      style: 'currency',
      currency: currencyCode
    }).format(value);
  }
}
