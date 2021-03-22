export interface IEntityManager {
  id?: number;
  name?: string;
  description?: string;
  uid?: string;
}

export const defaultValue: Readonly<IEntityManager> = {};
