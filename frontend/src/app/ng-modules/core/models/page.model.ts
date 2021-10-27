import {Pageable} from './pageable.model';

export interface Page<T> {
  content: T[];
  empty: boolean;
  first: boolean;
  last: boolean;
  number: number;
  numberOfElements: number;
  pageable: Pageable;
  size: number;
  totalPages: number;
  totalElements: number;
}
