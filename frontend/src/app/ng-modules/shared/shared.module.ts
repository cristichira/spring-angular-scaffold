import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';
import { ReactiveFormsModule } from '@angular/forms';
import { ErrorComponent } from './error/error.component';
import { RouterModule } from '@angular/router';
import { MenuComponent } from './menu/menu.component';
import { FooterComponent } from './footer/footer.component';


@NgModule({
  declarations: [
    ErrorComponent,
    MenuComponent,
    FooterComponent
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
    RouterModule,
    MenuComponent,
    FooterComponent
  ]
})
export class SharedModule {
}
