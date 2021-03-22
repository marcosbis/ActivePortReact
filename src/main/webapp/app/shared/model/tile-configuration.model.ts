import { IServiceConfiguration } from 'app/shared/model/service-configuration.model';

export interface ITileConfiguration {
  id?: number;
  name?: string;
  description?: string;
  command?: any;
  serviceTypeCode?: string;
  serviceTypeId?: number;
  services?: IServiceConfiguration[];
}

export const defaultValue: Readonly<ITileConfiguration> = {};
