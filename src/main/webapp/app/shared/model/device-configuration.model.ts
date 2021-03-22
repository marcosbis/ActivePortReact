import { NtuModeEnum } from 'app/shared/model/enumerations/ntu-mode-enum.model';
import { PortServiceTypeEnum } from 'app/shared/model/enumerations/port-service-type-enum.model';
import { AddressSetupTypeEnum } from 'app/shared/model/enumerations/address-setup-type-enum.model';

export interface IDeviceConfiguration {
  id?: number;
  uid?: string;
  description?: string;
  serialNumber?: string;
  hostName?: string;
  loIp?: string;
  firmwareVersion?: string;
  endpoint?: string;
  restUsername?: string;
  restPassword?: string;
  restEnabled?: boolean;
  mode?: NtuModeEnum;
  defaultRate?: number;
  subnet?: string;
  deviceType?: PortServiceTypeEnum;
  addressSetupType?: AddressSetupTypeEnum;
  ntutypeModel?: string;
  ntutypeId?: number;
  configurationName?: string;
  configurationId?: number;
  realmSubnet?: string;
  realmId?: number;
}

export const defaultValue: Readonly<IDeviceConfiguration> = {
  restEnabled: false,
};
