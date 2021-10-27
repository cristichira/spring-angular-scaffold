export interface Address {
  id: number;
  createdOn: Date;
  modifiedOn: Date;
  number: string;
  street: string;
  postCode: string;
  city: string;
  region: string;
  country: string;
  placeName: string;
  longitude: number;
  latitude: number;
}
