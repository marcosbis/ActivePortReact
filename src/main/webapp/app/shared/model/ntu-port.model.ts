import { IUserService } from 'app/shared/model/user-service.model';
import { PortTypeEnum } from 'app/shared/model/enumerations/port-type-enum.model';

export interface INtuPort {
  id?: number;
  name?: string;
  label?: string;
  description?: string;
  mac?: string;
  port?: number;
  portType?: PortTypeEnum;
  trunk?: boolean;
  jumbo?: boolean;
  portSpeed?: string;
  internetPort?: boolean;
  uplinkPort?: string;
  userServices?: IUserService[];
  ntuName?: string;
  ntuId?: number;
}

export const defaultValue: Readonly<INtuPort> = {
  trunk: false,
  jumbo: false,
  internetPort: false,
};
