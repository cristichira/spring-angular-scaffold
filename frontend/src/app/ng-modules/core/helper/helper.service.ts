import { Injectable } from '@angular/core';

import { FileView } from '../models/file/file-view.model';

@Injectable({
  providedIn: 'root'
})
export class HelperService {
  backgroundCardPlaceholder: string;

  constructor() {
    this.backgroundCardPlaceholder = '../../../../../assets/images/background-card.jpg';
  }

  getPictureUrl(fileView: FileView): string {
    return fileView ?
      `api/${fileView.storageFileName}` :
      this.backgroundCardPlaceholder;
  }
}
