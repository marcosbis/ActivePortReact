import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <MenuItem icon="asterisk" to="/tenant">
      <Translate contentKey="global.menu.entities.tenant" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/user-role">
      <Translate contentKey="global.menu.entities.userRole" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/user-profile">
      <Translate contentKey="global.menu.entities.userProfile" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/central-switch">
      <Translate contentKey="global.menu.entities.centralSwitch" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/organization">
      <Translate contentKey="global.menu.entities.organization" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/item-code">
      <Translate contentKey="global.menu.entities.itemCode" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/billing-system">
      <Translate contentKey="global.menu.entities.billingSystem" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/circuit-vlan">
      <Translate contentKey="global.menu.entities.circuitVlan" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/juno-command-log">
      <Translate contentKey="global.menu.entities.junoCommandLog" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/market-place">
      <Translate contentKey="global.menu.entities.marketPlace" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/notification-event">
      <Translate contentKey="global.menu.entities.notificationEvent" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/service-command">
      <Translate contentKey="global.menu.entities.serviceCommand" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/ntu">
      <Translate contentKey="global.menu.entities.ntu" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/ntu-config">
      <Translate contentKey="global.menu.entities.ntuConfig" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/ntu-port">
      <Translate contentKey="global.menu.entities.ntuPort" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/ntu-type">
      <Translate contentKey="global.menu.entities.ntuType" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/service-type">
      <Translate contentKey="global.menu.entities.serviceType" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/config-job">
      <Translate contentKey="global.menu.entities.configJob" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/rate-change-log">
      <Translate contentKey="global.menu.entities.rateChangeLog" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/vntu-downlink-port">
      <Translate contentKey="global.menu.entities.vntuDownlinkPort" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/partner">
      <Translate contentKey="global.menu.entities.partner" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/provider-port">
      <Translate contentKey="global.menu.entities.providerPort" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/location">
      <Translate contentKey="global.menu.entities.location" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/user-service">
      <Translate contentKey="global.menu.entities.userService" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/realm-ip">
      <Translate contentKey="global.menu.entities.realmIp" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/address-allocation">
      <Translate contentKey="global.menu.entities.addressAllocation" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/device-configuration">
      <Translate contentKey="global.menu.entities.deviceConfiguration" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/template-configuration">
      <Translate contentKey="global.menu.entities.templateConfiguration" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/policer-schedule">
      <Translate contentKey="global.menu.entities.policerSchedule" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/policer-range">
      <Translate contentKey="global.menu.entities.policerRange" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/third-party-api">
      <Translate contentKey="global.menu.entities.thirdPartyApi" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/xero-account">
      <Translate contentKey="global.menu.entities.xeroAccount" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/provider-log">
      <Translate contentKey="global.menu.entities.providerLog" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/service-code">
      <Translate contentKey="global.menu.entities.serviceCode" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/service-configuration">
      <Translate contentKey="global.menu.entities.serviceConfiguration" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/tile-configuration">
      <Translate contentKey="global.menu.entities.tileConfiguration" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/provider-configuration">
      <Translate contentKey="global.menu.entities.providerConfiguration" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/tile-tenant-configuration">
      <Translate contentKey="global.menu.entities.tileTenantConfiguration" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity-manager">
      <Translate contentKey="global.menu.entities.entityManager" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
