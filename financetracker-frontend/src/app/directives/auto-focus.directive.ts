import { Directive, ElementRef, OnInit, inject } from '@angular/core';

@Directive({
  name: '[appAutoFocus]',
  standalone: true
})
export class AutoFocusDirective implements OnInit {
  private readonly elementRef = inject(ElementRef);

  ngOnInit(): void {
    setTimeout(() => {
      this.elementRef.nativeElement.focus();
    }, 50);
  }
}
