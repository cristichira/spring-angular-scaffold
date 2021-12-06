import { Observable } from 'rxjs';

import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Page } from '../models/page.model';
import { User } from '../models/user/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  serviceUrl: string = 'api/users';

  constructor(private httpClient: HttpClient) {
  }

  get(id: number): Observable<User> {
    return this.httpClient.get<User>(`${this.serviceUrl}/${id}`);
  }

  getMe(): Observable<User> {
    return this.httpClient.get<User>(`${this.serviceUrl}/me`);
  }

  find(httpParams: HttpParams): Observable<Page<User>> {
    return this.httpClient.get<Page<User>>(`${this.serviceUrl}/list`, {params: httpParams});
  }
}
