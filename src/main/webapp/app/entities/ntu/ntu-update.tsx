import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDeviceConfiguration } from 'app/shared/model/device-configuration.model';
import { getEntities as getDeviceConfigurations } from 'app/entities/device-configuration/device-configuration.reducer';
import { INtuType } from 'app/shared/model/ntu-type.model';
import { getEntities as getNtuTypes } from 'app/entities/ntu-type/ntu-type.reducer';
import { getEntity, updateEntity, createEntity, reset } from './ntu.reducer';
import { INtu } from 'app/shared/model/ntu.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface INtuUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NtuUpdate = (props: INtuUpdateProps) => {
  const [deviceConfigurationId, setDeviceConfigurationId] = useState('0');
  const [ntutypeId, setNtutypeId] = useState('0');
  const [ntutypeId, setNtutypeId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { ntuEntity, deviceConfigurations, ntuTypes, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/ntu' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getDeviceConfigurations();
    props.getNtuTypes();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...ntuEntity,
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
          <h2 id="activePortApp.ntu.home.createOrEditLabel">
            <Translate contentKey="activePortApp.ntu.home.createOrEditLabel">Create or edit a Ntu</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : ntuEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="ntu-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="ntu-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="ntu-name">
                  <Translate contentKey="activePortApp.ntu.name">Name</Translate>
                </Label>
                <AvField id="ntu-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="hostIdLabel" for="ntu-hostId">
                  <Translate contentKey="activePortApp.ntu.hostId">Host Id</Translate>
                </Label>
                <AvField id="ntu-hostId" type="text" name="hostId" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="ntu-description">
                  <Translate contentKey="activePortApp.ntu.description">Description</Translate>
                </Label>
                <AvField id="ntu-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="serialNumberLabel" for="ntu-serialNumber">
                  <Translate contentKey="activePortApp.ntu.serialNumber">Serial Number</Translate>
                </Label>
                <AvField id="ntu-serialNumber" type="text" name="serialNumber" />
              </AvGroup>
              <AvGroup>
                <Label id="ipAddressLabel" for="ntu-ipAddress">
                  <Translate contentKey="activePortApp.ntu.ipAddress">Ip Address</Translate>
                </Label>
                <AvField id="ntu-ipAddress" type="text" name="ipAddress" />
              </AvGroup>
              <AvGroup>
                <Label id="companyNameLabel" for="ntu-companyName">
                  <Translate contentKey="activePortApp.ntu.companyName">Company Name</Translate>
                </Label>
                <AvField id="ntu-companyName" type="text" name="companyName" />
              </AvGroup>
              <AvGroup>
                <Label id="hostNameLabel" for="ntu-hostName">
                  <Translate contentKey="activePortApp.ntu.hostName">Host Name</Translate>
                </Label>
                <AvField id="ntu-hostName" type="text" name="hostName" />
              </AvGroup>
              <AvGroup>
                <Label id="loIpLabel" for="ntu-loIp">
                  <Translate contentKey="activePortApp.ntu.loIp">Lo Ip</Translate>
                </Label>
                <AvField id="ntu-loIp" type="text" name="loIp" />
              </AvGroup>
              <AvGroup>
                <Label id="categoryLabel" for="ntu-category">
                  <Translate contentKey="activePortApp.ntu.category">Category</Translate>
                </Label>
                <AvField id="ntu-category" type="text" name="category" />
              </AvGroup>
              <AvGroup>
                <Label id="alarmEmailAddressesLabel" for="ntu-alarmEmailAddresses">
                  <Translate contentKey="activePortApp.ntu.alarmEmailAddresses">Alarm Email Addresses</Translate>
                </Label>
                <AvField id="ntu-alarmEmailAddresses" type="text" name="alarmEmailAddresses" />
              </AvGroup>
              <AvGroup check>
                <Label id="metricCollectionLabel">
                  <AvInput id="ntu-metricCollection" type="checkbox" className="form-check-input" name="metricCollection" />
                  <Translate contentKey="activePortApp.ntu.metricCollection">Metric Collection</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="securityCollectionLabel">
                  <AvInput id="ntu-securityCollection" type="checkbox" className="form-check-input" name="securityCollection" />
                  <Translate contentKey="activePortApp.ntu.securityCollection">Security Collection</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="alarmCollectionLabel">
                  <AvInput id="ntu-alarmCollection" type="checkbox" className="form-check-input" name="alarmCollection" />
                  <Translate contentKey="activePortApp.ntu.alarmCollection">Alarm Collection</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="trendCollectionLabel">
                  <AvInput id="ntu-trendCollection" type="checkbox" className="form-check-input" name="trendCollection" />
                  <Translate contentKey="activePortApp.ntu.trendCollection">Trend Collection</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="syslogCollectionLabel">
                  <AvInput id="ntu-syslogCollection" type="checkbox" className="form-check-input" name="syslogCollection" />
                  <Translate contentKey="activePortApp.ntu.syslogCollection">Syslog Collection</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="configBackupLabel">
                  <AvInput id="ntu-configBackup" type="checkbox" className="form-check-input" name="configBackup" />
                  <Translate contentKey="activePortApp.ntu.configBackup">Config Backup</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="updateOneconfigLabel">
                  <AvInput id="ntu-updateOneconfig" type="checkbox" className="form-check-input" name="updateOneconfig" />
                  <Translate contentKey="activePortApp.ntu.updateOneconfig">Update Oneconfig</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="firmwareVersionLabel" for="ntu-firmwareVersion">
                  <Translate contentKey="activePortApp.ntu.firmwareVersion">Firmware Version</Translate>
                </Label>
                <AvField id="ntu-firmwareVersion" type="text" name="firmwareVersion" />
              </AvGroup>
              <AvGroup>
                <Label id="runningConfigLabel" for="ntu-runningConfig">
                  <Translate contentKey="activePortApp.ntu.runningConfig">Running Config</Translate>
                </Label>
                <AvField id="ntu-runningConfig" type="text" name="runningConfig" />
              </AvGroup>
              <AvGroup>
                <Label id="configIdLabel" for="ntu-configId">
                  <Translate contentKey="activePortApp.ntu.configId">Config Id</Translate>
                </Label>
                <AvField id="ntu-configId" type="text" name="configId" />
              </AvGroup>
              <AvGroup>
                <Label id="endpointLabel" for="ntu-endpoint">
                  <Translate contentKey="activePortApp.ntu.endpoint">Endpoint</Translate>
                </Label>
                <AvField id="ntu-endpoint" type="text" name="endpoint" />
              </AvGroup>
              <AvGroup>
                <Label id="restUsernameLabel" for="ntu-restUsername">
                  <Translate contentKey="activePortApp.ntu.restUsername">Rest Username</Translate>
                </Label>
                <AvField id="ntu-restUsername" type="text" name="restUsername" />
              </AvGroup>
              <AvGroup>
                <Label id="restPasswordLabel" for="ntu-restPassword">
                  <Translate contentKey="activePortApp.ntu.restPassword">Rest Password</Translate>
                </Label>
                <AvField id="ntu-restPassword" type="text" name="restPassword" />
              </AvGroup>
              <AvGroup check>
                <Label id="restEnabledLabel">
                  <AvInput id="ntu-restEnabled" type="checkbox" className="form-check-input" name="restEnabled" />
                  <Translate contentKey="activePortApp.ntu.restEnabled">Rest Enabled</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="autoRollbackLabel">
                  <AvInput id="ntu-autoRollback" type="checkbox" className="form-check-input" name="autoRollback" />
                  <Translate contentKey="activePortApp.ntu.autoRollback">Auto Rollback</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="modeLabel" for="ntu-mode">
                  <Translate contentKey="activePortApp.ntu.mode">Mode</Translate>
                </Label>
                <AvInput id="ntu-mode" type="select" className="form-control" name="mode" value={(!isNew && ntuEntity.mode) || 'DEMO'}>
                  <option value="DEMO">{translate('activePortApp.NtuModeEnum.DEMO')}</option>
                  <option value="EDGE">{translate('activePortApp.NtuModeEnum.EDGE')}</option>
                  <option value="CLOUD">{translate('activePortApp.NtuModeEnum.CLOUD')}</option>
                  <option value="MANAGED">{translate('activePortApp.NtuModeEnum.MANAGED')}</option>
                  <option value="BRIDGE">{translate('activePortApp.NtuModeEnum.BRIDGE')}</option>
                  <option value="SD_WAN">{translate('activePortApp.NtuModeEnum.SD_WAN')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="timeZoneLabel" for="ntu-timeZone">
                  <Translate contentKey="activePortApp.ntu.timeZone">Time Zone</Translate>
                </Label>
                <AvField id="ntu-timeZone" type="text" name="timeZone" />
              </AvGroup>
              <AvGroup>
                <Label id="minRateLabel" for="ntu-minRate">
                  <Translate contentKey="activePortApp.ntu.minRate">Min Rate</Translate>
                </Label>
                <AvField id="ntu-minRate" type="string" className="form-control" name="minRate" />
              </AvGroup>
              <AvGroup>
                <Label id="maxRateLabel" for="ntu-maxRate">
                  <Translate contentKey="activePortApp.ntu.maxRate">Max Rate</Translate>
                </Label>
                <AvField id="ntu-maxRate" type="string" className="form-control" name="maxRate" />
              </AvGroup>
              <AvGroup>
                <Label id="defaultRateLabel" for="ntu-defaultRate">
                  <Translate contentKey="activePortApp.ntu.defaultRate">Default Rate</Translate>
                </Label>
                <AvField id="ntu-defaultRate" type="string" className="form-control" name="defaultRate" />
              </AvGroup>
              <AvGroup check>
                <Label id="enableBodLabel">
                  <AvInput id="ntu-enableBod" type="checkbox" className="form-check-input" name="enableBod" />
                  <Translate contentKey="activePortApp.ntu.enableBod">Enable Bod</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="burstTimeLabel" for="ntu-burstTime">
                  <Translate contentKey="activePortApp.ntu.burstTime">Burst Time</Translate>
                </Label>
                <AvField
                  id="ntu-burstTime"
                  type="string"
                  className="form-control"
                  name="burstTime"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    min: { value: 5, errorMessage: translate('entity.validation.min', { min: 5 }) },
                    max: { value: 1000, errorMessage: translate('entity.validation.max', { max: 1000 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="secondlinkPortLabel" for="ntu-secondlinkPort">
                  <Translate contentKey="activePortApp.ntu.secondlinkPort">Secondlink Port</Translate>
                </Label>
                <AvField id="ntu-secondlinkPort" type="text" name="secondlinkPort" />
              </AvGroup>
              <AvGroup>
                <Label for="ntu-deviceConfiguration">
                  <Translate contentKey="activePortApp.ntu.deviceConfiguration">Device Configuration</Translate>
                </Label>
                <AvInput id="ntu-deviceConfiguration" type="select" className="form-control" name="deviceConfigurationId">
                  <option value="" key="0" />
                  {deviceConfigurations
                    ? deviceConfigurations.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.serialNumber}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="ntu-ntutype">
                  <Translate contentKey="activePortApp.ntu.ntutype">Ntutype</Translate>
                </Label>
                <AvInput id="ntu-ntutype" type="select" className="form-control" name="ntutypeId">
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
                <Label for="ntu-ntutype">
                  <Translate contentKey="activePortApp.ntu.ntutype">Ntutype</Translate>
                </Label>
                <AvInput id="ntu-ntutype" type="select" className="form-control" name="ntutypeId">
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
              <Button tag={Link} id="cancel-save" to="/ntu" replace color="info">
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
  deviceConfigurations: storeState.deviceConfiguration.entities,
  ntuTypes: storeState.ntuType.entities,
  ntuEntity: storeState.ntu.entity,
  loading: storeState.ntu.loading,
  updating: storeState.ntu.updating,
  updateSuccess: storeState.ntu.updateSuccess,
});

const mapDispatchToProps = {
  getDeviceConfigurations,
  getNtuTypes,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NtuUpdate);
