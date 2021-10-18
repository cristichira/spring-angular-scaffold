import { Injectable } from '@angular/core';

import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  getName(): string {
    return environment.name;
  }
}
