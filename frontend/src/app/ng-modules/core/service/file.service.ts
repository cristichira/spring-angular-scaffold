import {Injectable} from '@angular/core';
import {ConfigService} from './config.service';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {FileView} from '../models/file/file-view.model';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  serviceUrl: string = 'api/files';

  constructor(private configService: ConfigService, private httpClient: HttpClient) {
  }

  upload(file: File, isPublic: boolean): Observable<FileView> {
    const data: FormData = new FormData();
    data.append('file', file);
    data.append('isPublic', String(isPublic));

    return this.httpClient.post<FileView>(`${this.serviceUrl}/upload`, data);
  }
}
