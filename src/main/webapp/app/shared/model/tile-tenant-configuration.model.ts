export interface ITileTenantConfiguration {
  id?: number;
  tenantId?: string;
  orgId?: string;
  tileConfigurationName?: string;
  tileConfigurationId?: number;
}

export const defaultValue: Readonly<ITileTenantConfiguration> = {};
