import {Injectable} from '@angular/core';

import {FileView} from '../models/file/file-view.model';
import {ConfigService} from '../service/config.service';

@Injectable({
  providedIn: 'root'
})
export class HelperService {
  backgroundCardPlaceholder: string;

  constructor(private configService: ConfigService) {
    this.backgroundCardPlaceholder = '../../../../../assets/images/background-card.jpg';
  }

  getPictureUrl(fileView: FileView): string {
    return fileView ?
      `api/${fileView.storageFileName}` :
      this.backgroundCardPlaceholder;
  }
}
