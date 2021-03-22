import { Moment } from 'moment';
import { EventTypeEnum } from 'app/shared/model/enumerations/event-type-enum.model';

export interface IConfigJob {
  id?: number;
  hostId?: string;
  status?: string;
  uuid?: string;
  message?: string;
  command?: any;
  executed?: string;
  executedStatus?: string;
  executedMessage?: string;
  ntuId?: number;
  eventType?: EventTypeEnum;
  user?: string;
  errorMessage?: any;
  callbackUrl?: string;
}

export const defaultValue: Readonly<IConfigJob> = {};
