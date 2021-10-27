import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {AuthenticationRoutingModule} from './authentication-routing.module';
import {AuthenticationComponent} from './authentication.component';
import {RegisterComponent} from './register/register.component';
import {LoginComponent} from './login/login.component';
import {SharedModule} from '../../shared/shared.module';


@NgModule({
  declarations: [
    AuthenticationComponent,
    RegisterComponent,
    LoginComponent
  ],
  imports: [
    CommonModule,
    AuthenticationRoutingModule,
    SharedModule
  ]
})
export class AuthenticationModule {
}
