import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TranslateModule} from '@ngx-translate/core';
import {ReactiveFormsModule} from '@angular/forms';
import {ErrorComponent} from './error/error.component';
import {RouterModule} from '@angular/router';


@NgModule({
  declarations: [
    ErrorComponent
  ],
  imports: [
    CommonModule,
    TranslateModule,
    ReactiveFormsModule,
    RouterModule
  ],
  exports: [
    TranslateModule,
    ReactiveFormsModule,
    RouterModule
  ]
})
export class SharedModule {
}
