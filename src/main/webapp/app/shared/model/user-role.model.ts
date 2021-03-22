import { IAuthority } from 'app/shared/model/authority.model';

export interface IUserRole {
  id?: number;
  name?: string;
  priority?: number;
  description?: string;
  authorities?: IAuthority[];
}

export const defaultValue: Readonly<IUserRole> = {};
