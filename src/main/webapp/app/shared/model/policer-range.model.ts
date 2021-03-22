import { Moment } from 'moment';
import { IPolicerSchedule } from 'app/shared/model/policer-schedule.model';
import { RangeTypeEnum } from 'app/shared/model/enumerations/range-type-enum.model';

export interface IPolicerRange {
  id?: number;
  name?: string;
  burst?: number;
  bandwidth?: number;
  startPolicer?: string;
  endPolicer?: string;
  rangeType?: RangeTypeEnum;
  policerSchedules?: IPolicerSchedule[];
}

export const defaultValue: Readonly<IPolicerRange> = {};
