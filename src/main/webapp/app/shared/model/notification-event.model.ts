import { EventStatusEnum } from 'app/shared/model/enumerations/event-status-enum.model';
import { EventTypeEnum } from 'app/shared/model/enumerations/event-type-enum.model';

export interface INotificationEvent {
  id?: number;
  ntuName?: string;
  ntuId?: number;
  status?: EventStatusEnum;
  type?: EventTypeEnum;
  message?: string;
  serviceName?: string;
  serviceId?: number;
  vxcId?: number;
  uuid?: string;
  user?: string;
  errorMessage?: any;
  jobId?: number;
}

export const defaultValue: Readonly<INotificationEvent> = {};
