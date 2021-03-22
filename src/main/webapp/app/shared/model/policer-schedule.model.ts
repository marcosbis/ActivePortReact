import { IPolicerRange } from 'app/shared/model/policer-range.model';
import { ScheduleDayEnum } from 'app/shared/model/enumerations/schedule-day-enum.model';

export interface IPolicerSchedule {
  id?: number;
  name?: string;
  description?: string;
  days?: ScheduleDayEnum;
  ntuName?: string;
  ntuId?: number;
  policerRanges?: IPolicerRange[];
}

export const defaultValue: Readonly<IPolicerSchedule> = {};
