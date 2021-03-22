export interface IOrganization {
  id?: number;
  name?: string;
  hostId?: string;
  description?: string;
  billing?: boolean;
  timeZone?: string;
}

export const defaultValue: Readonly<IOrganization> = {
  billing: false,
};
