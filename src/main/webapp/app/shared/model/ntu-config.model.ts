export interface INtuConfig {
  id?: number;
  serialNumber?: string;
  name?: string;
  firmwareVersion?: string;
  ntuId?: number;
}

export const defaultValue: Readonly<INtuConfig> = {};
