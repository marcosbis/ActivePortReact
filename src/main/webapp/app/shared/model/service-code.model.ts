import { HostedTypeEnum } from 'app/shared/model/enumerations/hosted-type-enum.model';
import { CreationTypeEnum } from 'app/shared/model/enumerations/creation-type-enum.model';

export interface IServiceCode {
  id?: number;
  name?: string;
  command?: any;
  description?: string;
  enabled?: boolean;
  serviceUrl?: string;
  hostedType?: HostedTypeEnum;
  creationType?: CreationTypeEnum;
  tag?: string;
  dtoClass?: string;
  providerTypeName?: string;
  providerTypeId?: number;
  serviceTypeCode?: string;
  serviceTypeId?: number;
}

export const defaultValue: Readonly<IServiceCode> = {
  enabled: false,
};
