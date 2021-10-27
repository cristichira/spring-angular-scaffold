import {Address} from '../address/address.model';

export interface User {
  id: number;
  firstName: string;
  lastName: string;
  fullName: string;
  companyName: string;
  email: string;
  birthDate: string;
  phoneNumber: string;
  phoneNumberAlternate: string;
  roles: UserRole[];
  address: Address;
}

export enum UserRole {
  ROLE_USER = 'ROLE_USER',
  ROLE_ADMIN = 'ROLE_ADMIN'
}
