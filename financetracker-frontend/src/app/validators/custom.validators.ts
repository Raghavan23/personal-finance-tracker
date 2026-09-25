import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export class CustomValidators {
  static positiveNumber(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      if (control.value === null || control.value === undefined || control.value === '') {
        return null;
      }
      const num = Number(control.value);
      return !isNaN(num) && num > 0 ? null : { positiveNumber: { value: control.value } };
    };
  }

  static dateNotInFuture(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      if (!control.value) {
        return null;
      }
      const inputDate = new Date(control.value);
      const today = new Date();
      today.setHours(23, 59, 59, 999);
      return inputDate <= today ? null : { futureDateNotAllowed: { value: control.value } };
    };
  }
}
