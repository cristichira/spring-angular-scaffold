import { Component, OnDestroy, OnInit } from '@angular/core';
import { Locale } from '../../core/enums/locale';
import { TranslateService } from '@ngx-translate/core';
import { Router } from '@angular/router';
import { User, UserRole } from '../../core/models/user/user.model';
import { AuthenticationStore } from '../../core/store/authentication-store.service';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { AuthenticationService } from '../../core/service/authentication.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: [ './menu.component.scss' ]
})
export class MenuComponent implements OnInit, OnDestroy {

  locales: string[];
  currentUser: User;
  destroy$: Subject<boolean>;
  adminRole: UserRole = UserRole.ROLE_ADMIN;

  constructor(private translate: TranslateService,
              public authenticationService: AuthenticationService,
              private authenticationStore: AuthenticationStore,
              private router: Router) {
    this.destroy$ = new Subject<boolean>();
  }

  ngOnInit(): void {
    this.locales = Object.values(Locale);

    this.authenticationStore.user$.pipe(takeUntil((this.destroy$))).subscribe((user: User) =>
      this.currentUser = user
    );
  }

  useLanguage(locale: string): void {
    this.translate.use(locale);
  }

  logout(): void {
    this.authenticationService.logout().subscribe(
      () => this.router.navigate([ '/' ])
    );
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }
}
