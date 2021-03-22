import { IServiceConfiguration } from 'app/shared/model/service-configuration.model';
import { OnEventTypeEnum } from 'app/shared/model/enumerations/on-event-type-enum.model';
import { ServiceTypeEnum } from 'app/shared/model/enumerations/service-type-enum.model';
import { NtuSerieEnum } from 'app/shared/model/enumerations/ntu-serie-enum.model';
import { FilterCommandTypeEnum } from 'app/shared/model/enumerations/filter-command-type-enum.model';
import { OsTypeEnum } from 'app/shared/model/enumerations/os-type-enum.model';
import { EntityTypeEnum } from 'app/shared/model/enumerations/entity-type-enum.model';

export interface IServiceCommand {
  id?: number;
  name?: string;
  command?: any;
  onEvent?: OnEventTypeEnum;
  onService?: ServiceTypeEnum;
  deviceType?: NtuSerieEnum;
  enabled?: boolean;
  circuitType?: FilterCommandTypeEnum;
  tag?: string;
  osType?: OsTypeEnum;
  entryType?: EntityTypeEnum;
  serviceConfigurations?: IServiceConfiguration[];
}

export const defaultValue: Readonly<IServiceCommand> = {
  enabled: false,
};
