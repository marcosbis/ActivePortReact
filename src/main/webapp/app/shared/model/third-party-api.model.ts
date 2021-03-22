import { ApiType } from 'app/shared/model/enumerations/api-type.model';
import { PortApiTypeEnum } from 'app/shared/model/enumerations/port-api-type-enum.model';

export interface IThirdPartyApi {
  id?: number;
  name?: string;
  description?: string;
  api?: ApiType;
  stage?: string;
  username?: string;
  secret?: string;
  privateKeyCert?: string;
  privateKeyPassword?: string;
  billingUid?: string;
  productUid?: string;
  endpoint?: string;
  allowSharedPortsUid?: string;
  connectionType?: PortApiTypeEnum;
}

export const defaultValue: Readonly<IThirdPartyApi> = {};
