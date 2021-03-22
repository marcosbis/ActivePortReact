export interface IVntuDownlinkPort {
  id?: number;
  name?: string;
  uid?: string;
  description?: string;
  assignedOrgName?: string;
  assignedTenantName?: string;
  assignedOrgId?: string;
  assignedTenantId?: string;
  assignedVntuId?: number;
  assignedVntuName?: string;
  centralSwitchName?: string;
  centralSwitchId?: number;
}

export const defaultValue: Readonly<IVntuDownlinkPort> = {};
