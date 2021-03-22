import { ICentralSwitch } from 'app/shared/model/central-switch.model';
import { INtu } from 'app/shared/model/ntu.model';
import { PortServiceTypeEnum } from 'app/shared/model/enumerations/port-service-type-enum.model';
import { OsTypeEnum } from 'app/shared/model/enumerations/os-type-enum.model';

export interface INtuType {
  id?: number;
  model?: string;
  ethernetPorts?: number;
  sfpPorts?: number;
  pictureContentType?: string;
  description?: string;
  portServiceType?: PortServiceTypeEnum;
  osType?: OsTypeEnum;
  etherPrefix?: string;
  sfpPrefix?: string;
  startIndex?: number;
  portTemplate?: any;
  switches?: ICentralSwitch[];
  ntus?: INtu[];
}

export const defaultValue: Readonly<INtuType> = {};
