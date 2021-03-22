import { ConnectTypeEnum } from 'app/shared/model/enumerations/connect-type-enum.model';
import { ProvisioningStatusEnum } from 'app/shared/model/enumerations/provisioning-status-enum.model';
import { FirewallStatusEnum } from 'app/shared/model/enumerations/firewall-status-enum.model';
import { ServiceStateEnum } from 'app/shared/model/enumerations/service-state-enum.model';
import { PartnerTypeEnum } from 'app/shared/model/enumerations/partner-type-enum.model';
import { CircuitTypeEnum } from 'app/shared/model/enumerations/circuit-type-enum.model';
import { VXCTypeEnum } from 'app/shared/model/enumerations/vxc-type-enum.model';

export interface IUserService {
  id?: number;
  type?: ConnectTypeEnum;
  name?: string;
  description?: string;
  serviceKey?: string;
  rateLimit?: number;
  price?: string;
  uuid?: string;
  productUid?: string;
  reTaggedVlanId?: number;
  provisioningStatus?: ProvisioningStatusEnum;
  vlanIdA?: number;
  vlanIdB?: number;
  vlanIdS?: number;
  ntuId?: number;
  userIp?: string;
  firewallPrice?: string;
  firewallStatus?: FirewallStatusEnum;
  state?: ServiceStateEnum;
  bEndProductUid?: string;
  partnerType?: PartnerTypeEnum;
  circuitType?: CircuitTypeEnum;
  userSubnet?: string;
  myGw?: string;
  activePortGw?: string;
  awsAuthKey?: string;
  awsIp?: string;
  asn?: number;
  peerAsn?: number;
  vxcType?: VXCTypeEnum;
  ntuPortName?: string;
  ntuPortId?: number;
}

export const defaultValue: Readonly<IUserService> = {};
