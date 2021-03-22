import { PartnerTypeEnum } from 'app/shared/model/enumerations/partner-type-enum.model';
import { ConnetionTypeEnum } from 'app/shared/model/enumerations/connetion-type-enum.model';
import { AwsTypeEnum } from 'app/shared/model/enumerations/aws-type-enum.model';

export interface IPartner {
  id?: number;
  name?: string;
  email?: string;
  description?: string;
  type?: PartnerTypeEnum;
  connection?: ConnetionTypeEnum;
  portType?: AwsTypeEnum;
  port?: string;
  market?: string;
  locationId?: number;
  vxcpermitted?: boolean;
  locationIx?: string;
  vlanPort?: string;
  centralSwitchName?: string;
  centralSwitchId?: number;
  providerCodeName?: string;
  providerCodeId?: number;
}

export const defaultValue: Readonly<IPartner> = {
  vxcpermitted: false,
};
