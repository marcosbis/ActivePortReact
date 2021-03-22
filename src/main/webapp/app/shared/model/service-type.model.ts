import { IProviderConfiguration } from 'app/shared/model/provider-configuration.model';

export interface IServiceType {
  id?: number;
  code?: string;
  name?: string;
  description?: string;
  providers?: IProviderConfiguration[];
}

export const defaultValue: Readonly<IServiceType> = {};
