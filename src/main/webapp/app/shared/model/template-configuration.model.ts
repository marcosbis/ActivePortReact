export interface ITemplateConfiguration {
  id?: number;
  name?: string;
  description?: string;
  configuration?: any;
}

export const defaultValue: Readonly<ITemplateConfiguration> = {};
