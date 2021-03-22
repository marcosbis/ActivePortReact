import { InternetTypeEnum } from 'app/shared/model/enumerations/internet-type-enum.model';
import { ConnectTypeEnum } from 'app/shared/model/enumerations/connect-type-enum.model';

export interface ICircuitVlan {
  id?: number;
  zone?: string;
  serviceKey?: string;
  vlanId?: number;
  rd?: string;
  serviceId?: number;
  tenantName?: string;
  childServiceId?: number;
  childNtuId?: number;
  realmIp?: string;
  internetType?: InternetTypeEnum;
  type?: ConnectTypeEnum;
  serviceConfigurationName?: string;
  serviceConfigurationId?: number;
}

export const defaultValue: Readonly<ICircuitVlan> = {};
