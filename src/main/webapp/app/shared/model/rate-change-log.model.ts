export interface IRateChangeLog {
  id?: number;
  previuosRate?: number;
  rate?: number;
  userServiceName?: string;
  userServiceId?: number;
}

export const defaultValue: Readonly<IRateChangeLog> = {};
