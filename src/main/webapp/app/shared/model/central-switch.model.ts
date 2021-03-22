export interface ICentralSwitch {
  id?: number;
  name?: string;
  hostId?: string;
  description?: string;
  serialNumber?: string;
  ipAddress?: string;
  companyName?: string;
  hostName?: string;
  configBackup?: boolean;
  poolVlanStart?: number;
  poolVlanEnd?: number;
  endpoint?: string;
  restUsername?: string;
  restPassword?: string;
  restEnabled?: boolean;
  autoRollback?: boolean;
  feign?: boolean;
  locationCode?: string;
  locationId?: number;
  ntutypeModel?: string;
  ntutypeId?: number;
  ntutypeModel?: string;
  ntutypeId?: number;
}

export const defaultValue: Readonly<ICentralSwitch> = {
  configBackup: false,
  restEnabled: false,
  autoRollback: false,
  feign: false,
};
