export interface IProviderLog {
  id?: number;
  orgId?: string;
  tenantId?: string;
  serviceId?: number;
  log?: any;
  type?: string;
  jobUid?: string;
  ntuId?: number;
  request?: any;
  responseLog?: any;
}

export const defaultValue: Readonly<IProviderLog> = {};
