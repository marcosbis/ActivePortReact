import { IServiceCommand } from 'app/shared/model/service-command.model';
import { ITileConfiguration } from 'app/shared/model/tile-configuration.model';
import { TenantTypeEnum } from 'app/shared/model/enumerations/tenant-type-enum.model';

export interface IServiceConfiguration {
  id?: number;
  name?: string;
  description?: string;
  tenantType?: TenantTypeEnum;
  command?: any;
  test?: string;
  useDefaultCommands?: boolean;
  serviceCodeName?: string;
  serviceCodeId?: number;
  providerName?: string;
  providerId?: number;
  priceCodeName?: string;
  priceCodeId?: number;
  commands?: IServiceCommand[];
  tileConfigurations?: ITileConfiguration[];
}

export const defaultValue: Readonly<IServiceConfiguration> = {
  useDefaultCommands: false,
};
