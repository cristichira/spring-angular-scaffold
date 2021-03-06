import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../models/user/user.model';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationStore {
  isLoggedIn$: Observable<boolean>;
  isLoggedOut$: Observable<boolean>;
  user$: Observable<User>;
  private readonly _subject: BehaviorSubject<User>;

  constructor() {
    this._subject = new BehaviorSubject<User>(null);
    this.user$ = this._subject.asObservable();
    this.isLoggedIn$ = this.user$.pipe(map((user: User) => !!user));
    this.isLoggedOut$ = this.isLoggedIn$.pipe(map((loggedIn: boolean) => !loggedIn));
  }

  isLoggedIn(): boolean {
    return !!this._subject.value;
  }

  getCurrentUser(): User {
    return this._subject.value;
  }

  setUser(user: User): void {
    this._subject.next(user);
  }

  clearUser(): void {
    this._subject.next(null);
  }
}
