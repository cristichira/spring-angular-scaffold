import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {FormGroupValidator} from '../../../core/validator/form-group.validator';
import {AuthenticationService} from '../../../core/service/authentication.service';
import {Register} from '../../../core/models/register.model';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss', '../authentication.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm!: FormGroup;
  isValid: (formGroup: FormGroup, formControlName: string) => boolean = FormGroupValidator.isValid;

  constructor(private formBuilder: FormBuilder,
              private authenticationService: AuthenticationService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      firstName: [null, [Validators.required]],
      lastName: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email]],
      passwordGroup: this.formBuilder.group({
        password: [null, [Validators.required, Validators.minLength(6)]],
        confirmPassword: null
      }, {validator: FormGroupValidator.match('password', 'confirmPassword')})
    });
  }

  save(): void {
    const register: Register = {...this.registerForm.value, ...this.registerForm.controls.passwordGroup.value};
    this.authenticationService.register(register).subscribe(() => {
      this.router.navigate(['/']);
    });
  }
}

