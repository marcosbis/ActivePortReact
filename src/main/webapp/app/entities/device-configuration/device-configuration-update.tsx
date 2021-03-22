import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { INtuType } from 'app/shared/model/ntu-type.model';
import { getEntities as getNtuTypes } from 'app/entities/ntu-type/ntu-type.reducer';
import { ITemplateConfiguration } from 'app/shared/model/template-configuration.model';
import { getEntities as getTemplateConfigurations } from 'app/entities/template-configuration/template-configuration.reducer';
import { IRealmIp } from 'app/shared/model/realm-ip.model';
import { getEntities as getRealmIps } from 'app/entities/realm-ip/realm-ip.reducer';
import { getEntity, updateEntity, createEntity, reset } from './device-configuration.reducer';
import { IDeviceConfiguration } from 'app/shared/model/device-configuration.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDeviceConfigurationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DeviceConfigurationUpdate = (props: IDeviceConfigurationUpdateProps) => {
  const [ntutypeId, setNtutypeId] = useState('0');
  const [configurationId, setConfigurationId] = useState('0');
  const [realmId, setRealmId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { deviceConfigurationEntity, ntuTypes, templateConfigurations, realmIps, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/device-configuration' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getNtuTypes();
    props.getTemplateConfigurations();
    props.getRealmIps();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...deviceConfigurationEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="activePortApp.deviceConfiguration.home.createOrEditLabel">
            <Translate contentKey="activePortApp.deviceConfiguration.home.createOrEditLabel">
              Create or edit a DeviceConfiguration
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : deviceConfigurationEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="device-configuration-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="device-configuration-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="uidLabel" for="device-configuration-uid">
                  <Translate contentKey="activePortApp.deviceConfiguration.uid">Uid</Translate>
                </Label>
                <AvField id="device-configuration-uid" type="text" name="uid" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="device-configuration-description">
                  <Translate contentKey="activePortApp.deviceConfiguration.description">Description</Translate>
                </Label>
                <AvField id="device-configuration-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="serialNumberLabel" for="device-configuration-serialNumber">
                  <Translate contentKey="activePortApp.deviceConfiguration.serialNumber">Serial Number</Translate>
                </Label>
                <AvField id="device-configuration-serialNumber" type="text" name="serialNumber" />
              </AvGroup>
              <AvGroup>
                <Label id="hostNameLabel" for="device-configuration-hostName">
                  <Translate contentKey="activePortApp.deviceConfiguration.hostName">Host Name</Translate>
                </Label>
                <AvField id="device-configuration-hostName" type="text" name="hostName" />
              </AvGroup>
              <AvGroup>
                <Label id="loIpLabel" for="device-configuration-loIp">
                  <Translate contentKey="activePortApp.deviceConfiguration.loIp">Lo Ip</Translate>
                </Label>
                <AvField id="device-configuration-loIp" type="text" name="loIp" />
              </AvGroup>
              <AvGroup>
                <Label id="firmwareVersionLabel" for="device-configuration-firmwareVersion">
                  <Translate contentKey="activePortApp.deviceConfiguration.firmwareVersion">Firmware Version</Translate>
                </Label>
                <AvField id="device-configuration-firmwareVersion" type="text" name="firmwareVersion" />
              </AvGroup>
              <AvGroup>
                <Label id="endpointLabel" for="device-configuration-endpoint">
                  <Translate contentKey="activePortApp.deviceConfiguration.endpoint">Endpoint</Translate>
                </Label>
                <AvField id="device-configuration-endpoint" type="text" name="endpoint" />
              </AvGroup>
              <AvGroup>
                <Label id="restUsernameLabel" for="device-configuration-restUsername">
                  <Translate contentKey="activePortApp.deviceConfiguration.restUsername">Rest Username</Translate>
                </Label>
                <AvField id="device-configuration-restUsername" type="text" name="restUsername" />
              </AvGroup>
              <AvGroup>
                <Label id="restPasswordLabel" for="device-configuration-restPassword">
                  <Translate contentKey="activePortApp.deviceConfiguration.restPassword">Rest Password</Translate>
                </Label>
                <AvField id="device-configuration-restPassword" type="text" name="restPassword" />
              </AvGroup>
              <AvGroup check>
                <Label id="restEnabledLabel">
                  <AvInput id="device-configuration-restEnabled" type="checkbox" className="form-check-input" name="restEnabled" />
                  <Translate contentKey="activePortApp.deviceConfiguration.restEnabled">Rest Enabled</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="modeLabel" for="device-configuration-mode">
                  <Translate contentKey="activePortApp.deviceConfiguration.mode">Mode</Translate>
                </Label>
                <AvInput
                  id="device-configuration-mode"
                  type="select"
                  className="form-control"
                  name="mode"
                  value={(!isNew && deviceConfigurationEntity.mode) || 'DEMO'}
                >
                  <option value="DEMO">{translate('activePortApp.NtuModeEnum.DEMO')}</option>
                  <option value="EDGE">{translate('activePortApp.NtuModeEnum.EDGE')}</option>
                  <option value="CLOUD">{translate('activePortApp.NtuModeEnum.CLOUD')}</option>
                  <option value="MANAGED">{translate('activePortApp.NtuModeEnum.MANAGED')}</option>
                  <option value="BRIDGE">{translate('activePortApp.NtuModeEnum.BRIDGE')}</option>
                  <option value="SD_WAN">{translate('activePortApp.NtuModeEnum.SD_WAN')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="defaultRateLabel" for="device-configuration-defaultRate">
                  <Translate contentKey="activePortApp.deviceConfiguration.defaultRate">Default Rate</Translate>
                </Label>
                <AvField id="device-configuration-defaultRate" type="string" className="form-control" name="defaultRate" />
              </AvGroup>
              <AvGroup>
                <Label id="subnetLabel" for="device-configuration-subnet">
                  <Translate contentKey="activePortApp.deviceConfiguration.subnet">Subnet</Translate>
                </Label>
                <AvField id="device-configuration-subnet" type="text" name="subnet" />
              </AvGroup>
              <AvGroup>
                <Label id="deviceTypeLabel" for="device-configuration-deviceType">
                  <Translate contentKey="activePortApp.deviceConfiguration.deviceType">Device Type</Translate>
                </Label>
                <AvInput
                  id="device-configuration-deviceType"
                  type="select"
                  className="form-control"
                  name="deviceType"
                  value={(!isNew && deviceConfigurationEntity.deviceType) || 'EDGE'}
                >
                  <option value="EDGE">{translate('activePortApp.PortServiceTypeEnum.EDGE')}</option>
                  <option value="SWITCH">{translate('activePortApp.PortServiceTypeEnum.SWITCH')}</option>
                  <option value="FIREWALL">{translate('activePortApp.PortServiceTypeEnum.FIREWALL')}</option>
                  <option value="NTU">{translate('activePortApp.PortServiceTypeEnum.NTU')}</option>
                  <option value="PE">{translate('activePortApp.PortServiceTypeEnum.PE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="addressSetupTypeLabel" for="device-configuration-addressSetupType">
                  <Translate contentKey="activePortApp.deviceConfiguration.addressSetupType">Address Setup Type</Translate>
                </Label>
                <AvInput
                  id="device-configuration-addressSetupType"
                  type="select"
                  className="form-control"
                  name="addressSetupType"
                  value={(!isNew && deviceConfigurationEntity.addressSetupType) || 'FIRST'}
                >
                  <option value="FIRST">{translate('activePortApp.AddressSetupTypeEnum.FIRST')}</option>
                  <option value="LAST">{translate('activePortApp.AddressSetupTypeEnum.LAST')}</option>
                  <option value="RANDOM">{translate('activePortApp.AddressSetupTypeEnum.RANDOM')}</option>
                  <option value="MANUAL">{translate('activePortApp.AddressSetupTypeEnum.MANUAL')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="device-configuration-ntutype">
                  <Translate contentKey="activePortApp.deviceConfiguration.ntutype">Ntutype</Translate>
                </Label>
                <AvInput id="device-configuration-ntutype" type="select" className="form-control" name="ntutypeId">
                  <option value="" key="0" />
                  {ntuTypes
                    ? ntuTypes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.model}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="device-configuration-configuration">
                  <Translate contentKey="activePortApp.deviceConfiguration.configuration">Configuration</Translate>
                </Label>
                <AvInput id="device-configuration-configuration" type="select" className="form-control" name="configurationId">
                  <option value="" key="0" />
                  {templateConfigurations
                    ? templateConfigurations.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="device-configuration-realm">
                  <Translate contentKey="activePortApp.deviceConfiguration.realm">Realm</Translate>
                </Label>
                <AvInput id="device-configuration-realm" type="select" className="form-control" name="realmId">
                  <option value="" key="0" />
                  {realmIps
                    ? realmIps.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.subnet}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/device-configuration" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  ntuTypes: storeState.ntuType.entities,
  templateConfigurations: storeState.templateConfiguration.entities,
  realmIps: storeState.realmIp.entities,
  deviceConfigurationEntity: storeState.deviceConfiguration.entity,
  loading: storeState.deviceConfiguration.loading,
  updating: storeState.deviceConfiguration.updating,
  updateSuccess: storeState.deviceConfiguration.updateSuccess,
});

const mapDispatchToProps = {
  getNtuTypes,
  getTemplateConfigurations,
  getRealmIps,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DeviceConfigurationUpdate);
