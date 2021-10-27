import {APP_INITIALIZER, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AuthenticationModule} from "./ng-modules/feature/authentication/authentication.module";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import {Locale} from "./ng-modules/core/enums/locale";
import {AuthenticationService} from "./ng-modules/core/service/authentication.service";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import {appInitializer} from "./ng-modules/core/helper/app.initializer";
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    AuthenticationModule,
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    HttpClientModule,
    TranslateModule.forRoot({
      defaultLanguage: Locale.RO,
      loader: {
        provide: TranslateLoader,
        useFactory: httpLoaderFactory,
        deps: [HttpClient]
      }
    }),
  ],
  providers: [{
    provide: APP_INITIALIZER,
    useFactory: appInitializer,
    multi: true,
    deps: [AuthenticationService]
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}

export function httpLoaderFactory(http: HttpClient): TranslateHttpLoader {
  return new TranslateHttpLoader(http);
}
