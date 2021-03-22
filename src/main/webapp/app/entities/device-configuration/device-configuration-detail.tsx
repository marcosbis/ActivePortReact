import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './device-configuration.reducer';
import { IDeviceConfiguration } from 'app/shared/model/device-configuration.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDeviceConfigurationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DeviceConfigurationDetail = (props: IDeviceConfigurationDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { deviceConfigurationEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.deviceConfiguration.detail.title">DeviceConfiguration</Translate> [
          <b>{deviceConfigurationEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="uid">
              <Translate contentKey="activePortApp.deviceConfiguration.uid">Uid</Translate>
            </span>
          </dt>
          <dd>{deviceConfigurationEntity.uid}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.deviceConfiguration.description">Description</Translate>
            </span>
          </dt>
          <dd>{deviceConfigurationEntity.description}</dd>
          <dt>
            <span id="serialNumber">
              <Translate contentKey="activePortApp.deviceConfiguration.serialNumber">Serial Number</Translate>
            </span>
          </dt>
          <dd>{deviceConfigurationEntity.serialNumber}</dd>
          <dt>
            <span id="hostName">
              <Translate contentKey="activePortApp.deviceConfiguration.hostName">Host Name</Translate>
            </span>
          </dt>
          <dd>{deviceConfigurationEntity.hostName}</dd>
          <dt>
            <span id="loIp">
              <Translate contentKey="activePortApp.deviceConfiguration.loIp">Lo Ip</Translate>
            </span>
          </dt>
          <dd>{deviceConfigurationEntity.loIp}</dd>
          <dt>
            <span id="firmwareVersion">
              <Translate contentKey="activePortApp.deviceConfiguration.firmwareVersion">Firmware Version</Translate>
            </span>
          </dt>
          <dd>{deviceConfigurationEntity.firmwareVersion}</dd>
          <dt>
            <span id="endpoint">
              <Translate contentKey="activePortApp.deviceConfiguration.endpoint">Endpoint</Translate>
            </span>
          </dt>
          <dd>{deviceConfigurationEntity.endpoint}</dd>
          <dt>
            <span id="restUsername">
              <Translate contentKey="activePortApp.deviceConfiguration.restUsername">Rest Username</Translate>
            </span>
          </dt>
          <dd>{deviceConfigurationEntity.restUsername}</dd>
          <dt>
            <span id="restPassword">
              <Translate contentKey="activePortApp.deviceConfiguration.restPassword">Rest Password</Translate>
            </span>
          </dt>
          <dd>{deviceConfigurationEntity.restPassword}</dd>
          <dt>
            <span id="restEnabled">
              <Translate contentKey="activePortApp.deviceConfiguration.restEnabled">Rest Enabled</Translate>
            </span>
          </dt>
          <dd>{deviceConfigurationEntity.restEnabled ? 'true' : 'false'}</dd>
          <dt>
            <span id="mode">
              <Translate contentKey="activePortApp.deviceConfiguration.mode">Mode</Translate>
            </span>
          </dt>
          <dd>{deviceConfigurationEntity.mode}</dd>
          <dt>
            <span id="defaultRate">
              <Translate contentKey="activePortApp.deviceConfiguration.defaultRate">Default Rate</Translate>
            </span>
          </dt>
          <dd>{deviceConfigurationEntity.defaultRate}</dd>
          <dt>
            <span id="subnet">
              <Translate contentKey="activePortApp.deviceConfiguration.subnet">Subnet</Translate>
            </span>
          </dt>
          <dd>{deviceConfigurationEntity.subnet}</dd>
          <dt>
            <span id="deviceType">
              <Translate contentKey="activePortApp.deviceConfiguration.deviceType">Device Type</Translate>
            </span>
          </dt>
          <dd>{deviceConfigurationEntity.deviceType}</dd>
          <dt>
            <span id="addressSetupType">
              <Translate contentKey="activePortApp.deviceConfiguration.addressSetupType">Address Setup Type</Translate>
            </span>
          </dt>
          <dd>{deviceConfigurationEntity.addressSetupType}</dd>
          <dt>
            <Translate contentKey="activePortApp.deviceConfiguration.ntutype">Ntutype</Translate>
          </dt>
          <dd>{deviceConfigurationEntity.ntutypeModel ? deviceConfigurationEntity.ntutypeModel : ''}</dd>
          <dt>
            <Translate contentKey="activePortApp.deviceConfiguration.configuration">Configuration</Translate>
          </dt>
          <dd>{deviceConfigurationEntity.configurationName ? deviceConfigurationEntity.configurationName : ''}</dd>
          <dt>
            <Translate contentKey="activePortApp.deviceConfiguration.realm">Realm</Translate>
          </dt>
          <dd>{deviceConfigurationEntity.realmSubnet ? deviceConfigurationEntity.realmSubnet : ''}</dd>
        </dl>
        <Button tag={Link} to="/device-configuration" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/device-configuration/${deviceConfigurationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ deviceConfiguration }: IRootState) => ({
  deviceConfigurationEntity: deviceConfiguration.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DeviceConfigurationDetail);
