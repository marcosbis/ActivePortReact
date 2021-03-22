import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Tenant from './tenant';
import UserRole from './user-role';
import UserProfile from './user-profile';
import CentralSwitch from './central-switch';
import Organization from './organization';
import ItemCode from './item-code';
import BillingSystem from './billing-system';
import CircuitVlan from './circuit-vlan';
import JunoCommandLog from './juno-command-log';
import MarketPlace from './market-place';
import NotificationEvent from './notification-event';
import ServiceCommand from './service-command';
import Ntu from './ntu';
import NtuConfig from './ntu-config';
import NtuPort from './ntu-port';
import NtuType from './ntu-type';
import ServiceType from './service-type';
import ConfigJob from './config-job';
import RateChangeLog from './rate-change-log';
import VntuDownlinkPort from './vntu-downlink-port';
import Partner from './partner';
import ProviderPort from './provider-port';
import Location from './location';
import UserService from './user-service';
import RealmIp from './realm-ip';
import AddressAllocation from './address-allocation';
import DeviceConfiguration from './device-configuration';
import TemplateConfiguration from './template-configuration';
import PolicerSchedule from './policer-schedule';
import PolicerRange from './policer-range';
import ThirdPartyApi from './third-party-api';
import XeroAccount from './xero-account';
import ProviderLog from './provider-log';
import ServiceCode from './service-code';
import ServiceConfiguration from './service-configuration';
import TileConfiguration from './tile-configuration';
import ProviderConfiguration from './provider-configuration';
import TileTenantConfiguration from './tile-tenant-configuration';
import EntityManager from './entity-manager';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}tenant`} component={Tenant} />
      <ErrorBoundaryRoute path={`${match.url}user-role`} component={UserRole} />
      <ErrorBoundaryRoute path={`${match.url}user-profile`} component={UserProfile} />
      <ErrorBoundaryRoute path={`${match.url}central-switch`} component={CentralSwitch} />
      <ErrorBoundaryRoute path={`${match.url}organization`} component={Organization} />
      <ErrorBoundaryRoute path={`${match.url}item-code`} component={ItemCode} />
      <ErrorBoundaryRoute path={`${match.url}billing-system`} component={BillingSystem} />
      <ErrorBoundaryRoute path={`${match.url}circuit-vlan`} component={CircuitVlan} />
      <ErrorBoundaryRoute path={`${match.url}juno-command-log`} component={JunoCommandLog} />
      <ErrorBoundaryRoute path={`${match.url}market-place`} component={MarketPlace} />
      <ErrorBoundaryRoute path={`${match.url}notification-event`} component={NotificationEvent} />
      <ErrorBoundaryRoute path={`${match.url}service-command`} component={ServiceCommand} />
      <ErrorBoundaryRoute path={`${match.url}ntu`} component={Ntu} />
      <ErrorBoundaryRoute path={`${match.url}ntu-config`} component={NtuConfig} />
      <ErrorBoundaryRoute path={`${match.url}ntu-port`} component={NtuPort} />
      <ErrorBoundaryRoute path={`${match.url}ntu-type`} component={NtuType} />
      <ErrorBoundaryRoute path={`${match.url}service-type`} component={ServiceType} />
      <ErrorBoundaryRoute path={`${match.url}config-job`} component={ConfigJob} />
      <ErrorBoundaryRoute path={`${match.url}rate-change-log`} component={RateChangeLog} />
      <ErrorBoundaryRoute path={`${match.url}vntu-downlink-port`} component={VntuDownlinkPort} />
      <ErrorBoundaryRoute path={`${match.url}partner`} component={Partner} />
      <ErrorBoundaryRoute path={`${match.url}provider-port`} component={ProviderPort} />
      <ErrorBoundaryRoute path={`${match.url}location`} component={Location} />
      <ErrorBoundaryRoute path={`${match.url}user-service`} component={UserService} />
      <ErrorBoundaryRoute path={`${match.url}realm-ip`} component={RealmIp} />
      <ErrorBoundaryRoute path={`${match.url}address-allocation`} component={AddressAllocation} />
      <ErrorBoundaryRoute path={`${match.url}device-configuration`} component={DeviceConfiguration} />
      <ErrorBoundaryRoute path={`${match.url}template-configuration`} component={TemplateConfiguration} />
      <ErrorBoundaryRoute path={`${match.url}policer-schedule`} component={PolicerSchedule} />
      <ErrorBoundaryRoute path={`${match.url}policer-range`} component={PolicerRange} />
      <ErrorBoundaryRoute path={`${match.url}third-party-api`} component={ThirdPartyApi} />
      <ErrorBoundaryRoute path={`${match.url}xero-account`} component={XeroAccount} />
      <ErrorBoundaryRoute path={`${match.url}provider-log`} component={ProviderLog} />
      <ErrorBoundaryRoute path={`${match.url}service-code`} component={ServiceCode} />
      <ErrorBoundaryRoute path={`${match.url}service-configuration`} component={ServiceConfiguration} />
      <ErrorBoundaryRoute path={`${match.url}tile-configuration`} component={TileConfiguration} />
      <ErrorBoundaryRoute path={`${match.url}provider-configuration`} component={ProviderConfiguration} />
      <ErrorBoundaryRoute path={`${match.url}tile-tenant-configuration`} component={TileTenantConfiguration} />
      <ErrorBoundaryRoute path={`${match.url}entity-manager`} component={EntityManager} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
