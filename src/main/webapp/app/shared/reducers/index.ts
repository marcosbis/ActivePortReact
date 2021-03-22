import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import tenant, {
  TenantState
} from 'app/entities/tenant/tenant.reducer';
// prettier-ignore
import userRole, {
  UserRoleState
} from 'app/entities/user-role/user-role.reducer';
// prettier-ignore
import userProfile, {
  UserProfileState
} from 'app/entities/user-profile/user-profile.reducer';
// prettier-ignore
import centralSwitch, {
  CentralSwitchState
} from 'app/entities/central-switch/central-switch.reducer';
// prettier-ignore
import organization, {
  OrganizationState
} from 'app/entities/organization/organization.reducer';
// prettier-ignore
import itemCode, {
  ItemCodeState
} from 'app/entities/item-code/item-code.reducer';
// prettier-ignore
import billingSystem, {
  BillingSystemState
} from 'app/entities/billing-system/billing-system.reducer';
// prettier-ignore
import circuitVlan, {
  CircuitVlanState
} from 'app/entities/circuit-vlan/circuit-vlan.reducer';
// prettier-ignore
import junoCommandLog, {
  JunoCommandLogState
} from 'app/entities/juno-command-log/juno-command-log.reducer';
// prettier-ignore
import marketPlace, {
  MarketPlaceState
} from 'app/entities/market-place/market-place.reducer';
// prettier-ignore
import notificationEvent, {
  NotificationEventState
} from 'app/entities/notification-event/notification-event.reducer';
// prettier-ignore
import serviceCommand, {
  ServiceCommandState
} from 'app/entities/service-command/service-command.reducer';
// prettier-ignore
import ntu, {
  NtuState
} from 'app/entities/ntu/ntu.reducer';
// prettier-ignore
import ntuConfig, {
  NtuConfigState
} from 'app/entities/ntu-config/ntu-config.reducer';
// prettier-ignore
import ntuPort, {
  NtuPortState
} from 'app/entities/ntu-port/ntu-port.reducer';
// prettier-ignore
import ntuType, {
  NtuTypeState
} from 'app/entities/ntu-type/ntu-type.reducer';
// prettier-ignore
import serviceType, {
  ServiceTypeState
} from 'app/entities/service-type/service-type.reducer';
// prettier-ignore
import configJob, {
  ConfigJobState
} from 'app/entities/config-job/config-job.reducer';
// prettier-ignore
import rateChangeLog, {
  RateChangeLogState
} from 'app/entities/rate-change-log/rate-change-log.reducer';
// prettier-ignore
import vntuDownlinkPort, {
  VntuDownlinkPortState
} from 'app/entities/vntu-downlink-port/vntu-downlink-port.reducer';
// prettier-ignore
import partner, {
  PartnerState
} from 'app/entities/partner/partner.reducer';
// prettier-ignore
import providerPort, {
  ProviderPortState
} from 'app/entities/provider-port/provider-port.reducer';
// prettier-ignore
import location, {
  LocationState
} from 'app/entities/location/location.reducer';
// prettier-ignore
import userService, {
  UserServiceState
} from 'app/entities/user-service/user-service.reducer';
// prettier-ignore
import realmIp, {
  RealmIpState
} from 'app/entities/realm-ip/realm-ip.reducer';
// prettier-ignore
import addressAllocation, {
  AddressAllocationState
} from 'app/entities/address-allocation/address-allocation.reducer';
// prettier-ignore
import deviceConfiguration, {
  DeviceConfigurationState
} from 'app/entities/device-configuration/device-configuration.reducer';
// prettier-ignore
import templateConfiguration, {
  TemplateConfigurationState
} from 'app/entities/template-configuration/template-configuration.reducer';
// prettier-ignore
import policerSchedule, {
  PolicerScheduleState
} from 'app/entities/policer-schedule/policer-schedule.reducer';
// prettier-ignore
import policerRange, {
  PolicerRangeState
} from 'app/entities/policer-range/policer-range.reducer';
// prettier-ignore
import thirdPartyApi, {
  ThirdPartyApiState
} from 'app/entities/third-party-api/third-party-api.reducer';
// prettier-ignore
import xeroAccount, {
  XeroAccountState
} from 'app/entities/xero-account/xero-account.reducer';
// prettier-ignore
import providerLog, {
  ProviderLogState
} from 'app/entities/provider-log/provider-log.reducer';
// prettier-ignore
import serviceCode, {
  ServiceCodeState
} from 'app/entities/service-code/service-code.reducer';
// prettier-ignore
import serviceConfiguration, {
  ServiceConfigurationState
} from 'app/entities/service-configuration/service-configuration.reducer';
// prettier-ignore
import tileConfiguration, {
  TileConfigurationState
} from 'app/entities/tile-configuration/tile-configuration.reducer';
// prettier-ignore
import providerConfiguration, {
  ProviderConfigurationState
} from 'app/entities/provider-configuration/provider-configuration.reducer';
// prettier-ignore
import tileTenantConfiguration, {
  TileTenantConfigurationState
} from 'app/entities/tile-tenant-configuration/tile-tenant-configuration.reducer';
// prettier-ignore
import entityManager, {
  EntityManagerState
} from 'app/entities/entity-manager/entity-manager.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly tenant: TenantState;
  readonly userRole: UserRoleState;
  readonly userProfile: UserProfileState;
  readonly centralSwitch: CentralSwitchState;
  readonly organization: OrganizationState;
  readonly itemCode: ItemCodeState;
  readonly billingSystem: BillingSystemState;
  readonly circuitVlan: CircuitVlanState;
  readonly junoCommandLog: JunoCommandLogState;
  readonly marketPlace: MarketPlaceState;
  readonly notificationEvent: NotificationEventState;
  readonly serviceCommand: ServiceCommandState;
  readonly ntu: NtuState;
  readonly ntuConfig: NtuConfigState;
  readonly ntuPort: NtuPortState;
  readonly ntuType: NtuTypeState;
  readonly serviceType: ServiceTypeState;
  readonly configJob: ConfigJobState;
  readonly rateChangeLog: RateChangeLogState;
  readonly vntuDownlinkPort: VntuDownlinkPortState;
  readonly partner: PartnerState;
  readonly providerPort: ProviderPortState;
  readonly location: LocationState;
  readonly userService: UserServiceState;
  readonly realmIp: RealmIpState;
  readonly addressAllocation: AddressAllocationState;
  readonly deviceConfiguration: DeviceConfigurationState;
  readonly templateConfiguration: TemplateConfigurationState;
  readonly policerSchedule: PolicerScheduleState;
  readonly policerRange: PolicerRangeState;
  readonly thirdPartyApi: ThirdPartyApiState;
  readonly xeroAccount: XeroAccountState;
  readonly providerLog: ProviderLogState;
  readonly serviceCode: ServiceCodeState;
  readonly serviceConfiguration: ServiceConfigurationState;
  readonly tileConfiguration: TileConfigurationState;
  readonly providerConfiguration: ProviderConfigurationState;
  readonly tileTenantConfiguration: TileTenantConfigurationState;
  readonly entityManager: EntityManagerState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  tenant,
  userRole,
  userProfile,
  centralSwitch,
  organization,
  itemCode,
  billingSystem,
  circuitVlan,
  junoCommandLog,
  marketPlace,
  notificationEvent,
  serviceCommand,
  ntu,
  ntuConfig,
  ntuPort,
  ntuType,
  serviceType,
  configJob,
  rateChangeLog,
  vntuDownlinkPort,
  partner,
  providerPort,
  location,
  userService,
  realmIp,
  addressAllocation,
  deviceConfiguration,
  templateConfiguration,
  policerSchedule,
  policerRange,
  thirdPartyApi,
  xeroAccount,
  providerLog,
  serviceCode,
  serviceConfiguration,
  tileConfiguration,
  providerConfiguration,
  tileTenantConfiguration,
  entityManager,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
});

export default rootReducer;
