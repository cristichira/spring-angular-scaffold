import { Injectable } from '@angular/core';
import { Register } from '../models/register.model';
import { Observable } from 'rxjs';
import { User, UserRole } from '../models/user/user.model';
import { ConfigService } from './config.service';
import { HttpClient } from '@angular/common/http';
import { Login } from '../models/login.model';
import { shareReplay, tap } from 'rxjs/operators';
import { AuthenticationStore } from '../store/authentication-store.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  serviceUrl: string = 'api/auth';

  constructor(private authenticationStore: AuthenticationStore,
              private configService: ConfigService,
              private httpClient: HttpClient) {
  }

  register(register: Register): Observable<User> {
    return this.httpClient.post<User>(`${this.serviceUrl}/register`, register).pipe(
      tap((user: User) => {
        this.authenticationStore.setUser(user);
      }),
      shareReplay()
    );
  }

  login(login: Login): Observable<User> {
    return this.httpClient.post<User>(`${this.serviceUrl}/login`, login).pipe(
      tap((user: User) => {
        this.authenticationStore.setUser(user);
      }),
      shareReplay()
    );
  }

  refreshToken(): Observable<User> {
    return this.httpClient.post<User>(`${this.serviceUrl}/refresh-token`, null).pipe(
      tap((user: User) => {
        this.authenticationStore.setUser(user);
      }),
      shareReplay()
    );
  }

  logout(): Observable<null> {
    this.authenticationStore.clearUser();

    return this.httpClient.post<null>(`${this.serviceUrl}/logout`, null).pipe(
      shareReplay()
    );
  }

  hasRole(role: UserRole): boolean {
    return this.authenticationStore.isLoggedIn() &&
      this.authenticationStore.getCurrentUser().roles.includes(role);
  }
}
