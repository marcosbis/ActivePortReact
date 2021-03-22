import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ntu.reducer';
import { INtu } from 'app/shared/model/ntu.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INtuDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NtuDetail = (props: INtuDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { ntuEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.ntu.detail.title">Ntu</Translate> [<b>{ntuEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.ntu.name">Name</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.name}</dd>
          <dt>
            <span id="hostId">
              <Translate contentKey="activePortApp.ntu.hostId">Host Id</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.hostId}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.ntu.description">Description</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.description}</dd>
          <dt>
            <span id="serialNumber">
              <Translate contentKey="activePortApp.ntu.serialNumber">Serial Number</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.serialNumber}</dd>
          <dt>
            <span id="ipAddress">
              <Translate contentKey="activePortApp.ntu.ipAddress">Ip Address</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.ipAddress}</dd>
          <dt>
            <span id="companyName">
              <Translate contentKey="activePortApp.ntu.companyName">Company Name</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.companyName}</dd>
          <dt>
            <span id="hostName">
              <Translate contentKey="activePortApp.ntu.hostName">Host Name</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.hostName}</dd>
          <dt>
            <span id="loIp">
              <Translate contentKey="activePortApp.ntu.loIp">Lo Ip</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.loIp}</dd>
          <dt>
            <span id="category">
              <Translate contentKey="activePortApp.ntu.category">Category</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.category}</dd>
          <dt>
            <span id="alarmEmailAddresses">
              <Translate contentKey="activePortApp.ntu.alarmEmailAddresses">Alarm Email Addresses</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.alarmEmailAddresses}</dd>
          <dt>
            <span id="metricCollection">
              <Translate contentKey="activePortApp.ntu.metricCollection">Metric Collection</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.metricCollection ? 'true' : 'false'}</dd>
          <dt>
            <span id="securityCollection">
              <Translate contentKey="activePortApp.ntu.securityCollection">Security Collection</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.securityCollection ? 'true' : 'false'}</dd>
          <dt>
            <span id="alarmCollection">
              <Translate contentKey="activePortApp.ntu.alarmCollection">Alarm Collection</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.alarmCollection ? 'true' : 'false'}</dd>
          <dt>
            <span id="trendCollection">
              <Translate contentKey="activePortApp.ntu.trendCollection">Trend Collection</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.trendCollection ? 'true' : 'false'}</dd>
          <dt>
            <span id="syslogCollection">
              <Translate contentKey="activePortApp.ntu.syslogCollection">Syslog Collection</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.syslogCollection ? 'true' : 'false'}</dd>
          <dt>
            <span id="configBackup">
              <Translate contentKey="activePortApp.ntu.configBackup">Config Backup</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.configBackup ? 'true' : 'false'}</dd>
          <dt>
            <span id="updateOneconfig">
              <Translate contentKey="activePortApp.ntu.updateOneconfig">Update Oneconfig</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.updateOneconfig ? 'true' : 'false'}</dd>
          <dt>
            <span id="firmwareVersion">
              <Translate contentKey="activePortApp.ntu.firmwareVersion">Firmware Version</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.firmwareVersion}</dd>
          <dt>
            <span id="runningConfig">
              <Translate contentKey="activePortApp.ntu.runningConfig">Running Config</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.runningConfig}</dd>
          <dt>
            <span id="configId">
              <Translate contentKey="activePortApp.ntu.configId">Config Id</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.configId}</dd>
          <dt>
            <span id="endpoint">
              <Translate contentKey="activePortApp.ntu.endpoint">Endpoint</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.endpoint}</dd>
          <dt>
            <span id="restUsername">
              <Translate contentKey="activePortApp.ntu.restUsername">Rest Username</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.restUsername}</dd>
          <dt>
            <span id="restPassword">
              <Translate contentKey="activePortApp.ntu.restPassword">Rest Password</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.restPassword}</dd>
          <dt>
            <span id="restEnabled">
              <Translate contentKey="activePortApp.ntu.restEnabled">Rest Enabled</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.restEnabled ? 'true' : 'false'}</dd>
          <dt>
            <span id="autoRollback">
              <Translate contentKey="activePortApp.ntu.autoRollback">Auto Rollback</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.autoRollback ? 'true' : 'false'}</dd>
          <dt>
            <span id="mode">
              <Translate contentKey="activePortApp.ntu.mode">Mode</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.mode}</dd>
          <dt>
            <span id="timeZone">
              <Translate contentKey="activePortApp.ntu.timeZone">Time Zone</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.timeZone}</dd>
          <dt>
            <span id="minRate">
              <Translate contentKey="activePortApp.ntu.minRate">Min Rate</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.minRate}</dd>
          <dt>
            <span id="maxRate">
              <Translate contentKey="activePortApp.ntu.maxRate">Max Rate</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.maxRate}</dd>
          <dt>
            <span id="defaultRate">
              <Translate contentKey="activePortApp.ntu.defaultRate">Default Rate</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.defaultRate}</dd>
          <dt>
            <span id="enableBod">
              <Translate contentKey="activePortApp.ntu.enableBod">Enable Bod</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.enableBod ? 'true' : 'false'}</dd>
          <dt>
            <span id="burstTime">
              <Translate contentKey="activePortApp.ntu.burstTime">Burst Time</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.burstTime}</dd>
          <dt>
            <span id="secondlinkPort">
              <Translate contentKey="activePortApp.ntu.secondlinkPort">Secondlink Port</Translate>
            </span>
          </dt>
          <dd>{ntuEntity.secondlinkPort}</dd>
          <dt>
            <Translate contentKey="activePortApp.ntu.deviceConfiguration">Device Configuration</Translate>
          </dt>
          <dd>{ntuEntity.deviceConfigurationSerialNumber ? ntuEntity.deviceConfigurationSerialNumber : ''}</dd>
          <dt>
            <Translate contentKey="activePortApp.ntu.ntutype">Ntutype</Translate>
          </dt>
          <dd>{ntuEntity.ntutypeModel ? ntuEntity.ntutypeModel : ''}</dd>
          <dt>
            <Translate contentKey="activePortApp.ntu.ntutype">Ntutype</Translate>
          </dt>
          <dd>{ntuEntity.ntutypeModel ? ntuEntity.ntutypeModel : ''}</dd>
        </dl>
        <Button tag={Link} to="/ntu" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ntu/${ntuEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ ntu }: IRootState) => ({
  ntuEntity: ntu.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NtuDetail);
