import { PartnerTypeEnum } from 'app/shared/model/enumerations/partner-type-enum.model';
import { ConnetionTypeEnum } from 'app/shared/model/enumerations/connetion-type-enum.model';
import { AwsTypeEnum } from 'app/shared/model/enumerations/aws-type-enum.model';

export interface IProviderPort {
  id?: number;
  name?: string;
  uid?: string;
  description?: string;
  type?: PartnerTypeEnum;
  connection?: ConnetionTypeEnum;
  portType?: AwsTypeEnum;
  portId?: string;
  market?: string;
  locationId?: number;
  thirdPartyApiName?: string;
  thirdPartyApiId?: number;
}

export const defaultValue: Readonly<IProviderPort> = {};
