import { Moment } from 'moment';

export interface ITenant {
  id?: number;
  name?: string;
  description?: string;
  tenantId?: string;
  disableAccess?: boolean;
  ilmDays?: number;
  slmDays?: string;
}

export const defaultValue: Readonly<ITenant> = {
  disableAccess: false,
};
