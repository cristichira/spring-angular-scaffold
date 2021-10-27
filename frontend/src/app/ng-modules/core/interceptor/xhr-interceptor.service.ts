import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ConfigService} from '../service/config.service';

@Injectable({
  providedIn: 'root'
})
export class XhrInterceptor implements HttpInterceptor {

  constructor(private configService: ConfigService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if (!req.url.startsWith(this.configService.getBaseUrl())) {
      return next.handle(req);
    }

    const xhr: HttpRequest<any> = req.clone({
      withCredentials: true
    });

    return next.handle(xhr);
  }
}
