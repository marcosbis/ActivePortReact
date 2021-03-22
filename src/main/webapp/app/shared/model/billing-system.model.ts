import { Moment } from 'moment';
import { BillingTypeEnum } from 'app/shared/model/enumerations/billing-type-enum.model';

export interface IBillingSystem {
  id?: number;
  name?: string;
  description?: string;
  api?: BillingTypeEnum;
  stage?: string;
  username?: string;
  secret?: string;
  privateKeyCert?: string;
  privateKeyPassword?: string;
  billingUid?: string;
  startBilling?: string;
  currencyCode?: string;
}

export const defaultValue: Readonly<IBillingSystem> = {};
