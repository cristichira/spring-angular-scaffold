import {Injectable} from '@angular/core';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import {BehaviorSubject, Observable, throwError} from 'rxjs';
import {catchError, filter, switchMap, take} from 'rxjs/operators';
import {AuthenticationStore} from '../store/authentication-store.service';
import {AuthenticationService} from '../service/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class JwtTokenInterceptor implements HttpInterceptor {

  private refreshTokenInProgress: boolean;

  /**
   * Used to treat subsequent requests. If one request returns the 401 http status, this subject will make
   * sure that the next call will wait for the refresh token action to be completed and the user authenticated.
   */
  private refreshTokenSubject: BehaviorSubject<boolean>;

  constructor(private authenticationStore: AuthenticationStore,
              private authenticationService: AuthenticationService) {
    this.refreshTokenInProgress = false;
    this.refreshTokenSubject = new BehaviorSubject<boolean>(false);
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError((error: any) => {
        if ([401].includes(error.status) &&
          error instanceof HttpErrorResponse) {

          if (this.refreshTokenInProgress) {
            return this.refreshTokenSubject.pipe(
              filter((result: boolean) => result),
              take(1),
              switchMap(() => next.handle(req))
            );
          } else {
            this.refreshTokenInProgress = true;
            this.refreshTokenSubject.next(false);

            return this.authenticationService.refreshToken()
            .pipe(switchMap(() => {
              this.refreshTokenInProgress = false;
              this.refreshTokenSubject.next(true);

              return next.handle(req);
            }), catchError((err: any) => {
              this.refreshTokenInProgress = false;
              this.authenticationService.logout();

              return throwError(err);
            }));
          }
        } else {
          return throwError(error);
        }
      })) as Observable<HttpEvent<any>>;
  }
}
