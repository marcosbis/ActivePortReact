export interface IUserProfile {
  id?: number;
  name?: string;
  description?: string;
  userRoleName?: string;
  userRoleId?: number;
}

export const defaultValue: Readonly<IUserProfile> = {};
