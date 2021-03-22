import { IServiceType } from 'app/shared/model/service-type.model';
import { PartnerTypeEnum } from 'app/shared/model/enumerations/partner-type-enum.model';
import { ApiTypeEnum } from 'app/shared/model/enumerations/api-type-enum.model';

export interface IProviderConfiguration {
  id?: number;
  name?: string;
  description?: string;
  type?: PartnerTypeEnum;
  apiType?: ApiTypeEnum;
  hasPortId?: boolean;
  services?: IServiceType[];
}

export const defaultValue: Readonly<IProviderConfiguration> = {
  hasPortId: false,
};
