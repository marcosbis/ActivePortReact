export interface IRealmIp {
  id?: number;
  subnet?: string;
  name?: string;
  desciption?: string;
  mask?: string;
  subnetSize?: string;
  firstIp?: string;
  lastIp?: string;
  broadcast?: string;
  cir?: string;
  ipsecGateway?: string;
  locationCode?: string;
  locationId?: number;
}

export const defaultValue: Readonly<IRealmIp> = {};
