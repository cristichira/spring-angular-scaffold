import {AbstractControl, FormGroup, ValidatorFn} from '@angular/forms';

export class FormGroupValidator {

  static match(inputName: string, otherInputName: string): ValidatorFn {
    return (c: AbstractControl): { [key: string]: boolean } | null => {
      const inputControl: AbstractControl | null = c.get(inputName);
      const otherInputControl: AbstractControl | null = c.get(otherInputName);
      if (inputControl?.pristine || otherInputControl?.pristine) {
        return null;
      }

      if (inputControl?.value === otherInputControl?.value) {
        return null;
      }

      return {match: true};
    }
  }

  static isValid(formGroup: FormGroup, formControlName: string): boolean {
    return ((formGroup.get(formControlName)!.touched || formGroup.get(formControlName)!.dirty) && !formGroup.get(formControlName)!.valid);
  }
}
