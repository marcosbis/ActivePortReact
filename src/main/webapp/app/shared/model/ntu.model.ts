import { INtuPort } from 'app/shared/model/ntu-port.model';
import { NtuModeEnum } from 'app/shared/model/enumerations/ntu-mode-enum.model';

export interface INtu {
  id?: number;
  name?: string;
  hostId?: string;
  description?: string;
  serialNumber?: string;
  ipAddress?: string;
  companyName?: string;
  hostName?: string;
  loIp?: string;
  category?: string;
  alarmEmailAddresses?: string;
  metricCollection?: boolean;
  securityCollection?: boolean;
  alarmCollection?: boolean;
  trendCollection?: boolean;
  syslogCollection?: boolean;
  configBackup?: boolean;
  updateOneconfig?: boolean;
  firmwareVersion?: string;
  runningConfig?: string;
  configId?: string;
  endpoint?: string;
  restUsername?: string;
  restPassword?: string;
  restEnabled?: boolean;
  autoRollback?: boolean;
  mode?: NtuModeEnum;
  timeZone?: string;
  minRate?: number;
  maxRate?: number;
  defaultRate?: number;
  enableBod?: boolean;
  burstTime?: number;
  secondlinkPort?: string;
  deviceConfigurationSerialNumber?: string;
  deviceConfigurationId?: number;
  ports?: INtuPort[];
  ntutypeModel?: string;
  ntutypeId?: number;
  ntutypeModel?: string;
  ntutypeId?: number;
}

export const defaultValue: Readonly<INtu> = {
  metricCollection: false,
  securityCollection: false,
  alarmCollection: false,
  trendCollection: false,
  syslogCollection: false,
  configBackup: false,
  updateOneconfig: false,
  restEnabled: false,
  autoRollback: false,
  enableBod: false,
};
