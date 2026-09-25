import { Component, input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-stat-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './stat-card.component.html',
  styleUrl: './stat-card.component.css'
})
export class StatCardComponent {
  readonly title = input.required<string>();
  readonly value = input.required<string | number>();
  readonly subtitle = input<string>();
  readonly icon = input<string>('📊');
  readonly trend = input<'positive' | 'negative' | 'neutral'>('neutral');
}
