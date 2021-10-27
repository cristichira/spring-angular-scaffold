import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Login} from '../../../core/models/login.model';
import {FormGroupValidator} from '../../../core/validator/form-group.validator';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticationService} from "../../../core/service/authentication.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss', '../authentication.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;
  isValid: (formGroup: FormGroup, formControlName: string) => boolean = FormGroupValidator.isValid;

  constructor(private formBuilder: FormBuilder,
              private authenticationService: AuthenticationService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]]
    })
  }

  login(): void {
    const login: Login = {
      username: this.loginForm.value.email,
      password: this.loginForm.value.password
    };

    this.authenticationService.login(login).subscribe(() => {
      this.router.navigate(['/']);
    });
  }
}
