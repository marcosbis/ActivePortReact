import { Moment } from 'moment';
import { EventTypeEnum } from 'app/shared/model/enumerations/event-type-enum.model';
import { DeviceTargetTypeEnum } from 'app/shared/model/enumerations/device-target-type-enum.model';

export interface IJunoCommandLog {
  id?: number;
  ntuId?: number;
  switchId?: number;
  serviceId?: number;
  vxcId?: number;
  deviceUrl?: string;
  uuid?: string;
  command?: string;
  cmdResponse?: string;
  executedDate?: string;
  eventType?: EventTypeEnum;
  user?: string;
  deviceName?: string;
  targetType?: DeviceTargetTypeEnum;
  hasErrors?: boolean;
}

export const defaultValue: Readonly<IJunoCommandLog> = {
  hasErrors: false,
};
