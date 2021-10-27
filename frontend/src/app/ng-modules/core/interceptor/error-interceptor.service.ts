import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';

import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Router} from '@angular/router';

import {AuthenticationStore} from '../store/authentication-store.service';

@Injectable({
  providedIn: 'root'
})
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private router: Router,
              private authenticationStore: AuthenticationStore) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError((error: any) => {
        if (error instanceof HttpErrorResponse) {

          if ([401].includes(error.status)) {
            return next.handle(req);
          }
          // if (error.error instanceof ErrorEvent) {
          //   console.error(`Error occurred during request`, error.error.message);
          // } else {
          //   console.error(`Error occurred during backend request. Code ${ error.status }. Body: `, error.error);
          // }
          //
          // if ([ 401, 403 ].includes(error.status) && this.authenticationStore.isLoggedIn()) {
          //   // auto logout if 401 or 403 response returned from api
          //   console.error('Auto log out if 401 or 403 response is returned while being logged in.');
          //   this.authenticationStore.logout();
          // }
          //
          // this.router.navigate([ '/error' ]);
          return throwError('A http error occurred. Please try again later.');
        }
        return throwError('An error occurred. Please try again later.');
      }));
  }
}
