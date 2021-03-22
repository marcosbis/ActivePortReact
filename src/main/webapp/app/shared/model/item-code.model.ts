export interface IItemCode {
  id?: number;
  name?: string;
  code?: string;
  codeRate?: string;
  codeActivation?: string;
  description?: string;
  localPriceRate?: number;
  localPriceMontlhy?: number;
  localPriceActivation?: number;
  codeUpLift?: string;
  upLift?: number;
  useUpLift?: boolean;
  useLocalPrice?: boolean;
}

export const defaultValue: Readonly<IItemCode> = {
  useUpLift: false,
  useLocalPrice: false,
};
