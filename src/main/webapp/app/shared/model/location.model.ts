export interface ILocation {
  id?: number;
  code?: string;
  description?: string;
  address?: string;
  uuid?: string;
}

export const defaultValue: Readonly<ILocation> = {};
