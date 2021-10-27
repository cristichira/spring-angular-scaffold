import {FileView} from '../file/file-view.model';

export interface Event {
  id: number;
  createdOn: Date;
  modifiedOn: Date;
  urlName: string;
  name: string;
  picture: FileView;
}
