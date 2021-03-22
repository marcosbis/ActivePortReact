import { NtuModeEnum } from 'app/shared/model/enumerations/ntu-mode-enum.model';
import { PortServiceTypeEnum } from 'app/shared/model/enumerations/port-service-type-enum.model';
import { AllocationTypeEnum } from 'app/shared/model/enumerations/allocation-type-enum.model';

export interface IAddressAllocation {
  id?: number;
  subnet?: string;
  deviceName?: string;
  deviceMode?: NtuModeEnum;
  deviceId?: number;
  deviceType?: PortServiceTypeEnum;
  description?: string;
  allocationType?: AllocationTypeEnum;
  serialNumber?: string;
}

export const defaultValue: Readonly<IAddressAllocation> = {};
