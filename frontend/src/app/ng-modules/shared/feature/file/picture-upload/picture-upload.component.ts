import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

import { HelperService } from '../../../../core/helper/helper.service';
import { FileView } from '../../../../core/models/file/file-view.model';
import { FileService } from '../../../../core/services/file.service';

@Component({
  selector: 'app-picture-upload',
  templateUrl: './picture-upload.component.html',
  styleUrls: [ './picture-upload.component.scss' ]
})
export class PictureUploadComponent implements OnInit {

  @Input() picture!: FileView;
  @Input() isPublic!: boolean;
  pictureToDisplay!: string;
  @Output() notify: EventEmitter<number>;

  constructor(private helperService: HelperService,
              private fileService: FileService) {
    this.notify = new EventEmitter<number>();
  }

  ngOnInit(): void {
    this.pictureToDisplay = this.helperService.getPictureUrl(this.picture);
  }

  onChange(event: Event): void {
    const input: HTMLInputElement = event.target as HTMLInputElement;
    // tslint:disable-next-line:prefer-for-of
    for (let i: number = 0; i < input.files.length; i++) {
      const file: any = input.files[ i ];

      this.fileService.upload(file, this.isPublic).subscribe((fileView: FileView) => {
        this.pictureToDisplay = this.helperService.getPictureUrl(fileView);
        this.notify.emit(fileView.id);
      });
    }
  }

  removePicture(): void {
    this.pictureToDisplay = this.helperService.getPictureUrl(null);
    this.notify.emit(null);
  }

  resetPicture(): void {
    this.pictureToDisplay = this.helperService.getPictureUrl(this.picture);
    this.notify.emit(this.picture.id);
  }
}
