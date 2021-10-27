export interface Pageable {
  page: number;
  size: number;
  sort?: string[];
}

export const DEFAULT_PAGEABLE: Pageable = {
  page: 0,
  size: 10
};

export const UNPAGED: Pageable = {
  page: 0,
  size: 99
};
