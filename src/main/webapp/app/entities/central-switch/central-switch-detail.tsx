import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './central-switch.reducer';
import { ICentralSwitch } from 'app/shared/model/central-switch.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICentralSwitchDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CentralSwitchDetail = (props: ICentralSwitchDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { centralSwitchEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.centralSwitch.detail.title">CentralSwitch</Translate> [<b>{centralSwitchEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.centralSwitch.name">Name</Translate>
            </span>
          </dt>
          <dd>{centralSwitchEntity.name}</dd>
          <dt>
            <span id="hostId">
              <Translate contentKey="activePortApp.centralSwitch.hostId">Host Id</Translate>
            </span>
          </dt>
          <dd>{centralSwitchEntity.hostId}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.centralSwitch.description">Description</Translate>
            </span>
          </dt>
          <dd>{centralSwitchEntity.description}</dd>
          <dt>
            <span id="serialNumber">
              <Translate contentKey="activePortApp.centralSwitch.serialNumber">Serial Number</Translate>
            </span>
          </dt>
          <dd>{centralSwitchEntity.serialNumber}</dd>
          <dt>
            <span id="ipAddress">
              <Translate contentKey="activePortApp.centralSwitch.ipAddress">Ip Address</Translate>
            </span>
          </dt>
          <dd>{centralSwitchEntity.ipAddress}</dd>
          <dt>
            <span id="companyName">
              <Translate contentKey="activePortApp.centralSwitch.companyName">Company Name</Translate>
            </span>
          </dt>
          <dd>{centralSwitchEntity.companyName}</dd>
          <dt>
            <span id="hostName">
              <Translate contentKey="activePortApp.centralSwitch.hostName">Host Name</Translate>
            </span>
          </dt>
          <dd>{centralSwitchEntity.hostName}</dd>
          <dt>
            <span id="configBackup">
              <Translate contentKey="activePortApp.centralSwitch.configBackup">Config Backup</Translate>
            </span>
          </dt>
          <dd>{centralSwitchEntity.configBackup ? 'true' : 'false'}</dd>
          <dt>
            <span id="poolVlanStart">
              <Translate contentKey="activePortApp.centralSwitch.poolVlanStart">Pool Vlan Start</Translate>
            </span>
          </dt>
          <dd>{centralSwitchEntity.poolVlanStart}</dd>
          <dt>
            <span id="poolVlanEnd">
              <Translate contentKey="activePortApp.centralSwitch.poolVlanEnd">Pool Vlan End</Translate>
            </span>
          </dt>
          <dd>{centralSwitchEntity.poolVlanEnd}</dd>
          <dt>
            <span id="endpoint">
              <Translate contentKey="activePortApp.centralSwitch.endpoint">Endpoint</Translate>
            </span>
          </dt>
          <dd>{centralSwitchEntity.endpoint}</dd>
          <dt>
            <span id="restUsername">
              <Translate contentKey="activePortApp.centralSwitch.restUsername">Rest Username</Translate>
            </span>
          </dt>
          <dd>{centralSwitchEntity.restUsername}</dd>
          <dt>
            <span id="restPassword">
              <Translate contentKey="activePortApp.centralSwitch.restPassword">Rest Password</Translate>
            </span>
          </dt>
          <dd>{centralSwitchEntity.restPassword}</dd>
          <dt>
            <span id="restEnabled">
              <Translate contentKey="activePortApp.centralSwitch.restEnabled">Rest Enabled</Translate>
            </span>
          </dt>
          <dd>{centralSwitchEntity.restEnabled ? 'true' : 'false'}</dd>
          <dt>
            <span id="autoRollback">
              <Translate contentKey="activePortApp.centralSwitch.autoRollback">Auto Rollback</Translate>
            </span>
          </dt>
          <dd>{centralSwitchEntity.autoRollback ? 'true' : 'false'}</dd>
          <dt>
            <span id="feign">
              <Translate contentKey="activePortApp.centralSwitch.feign">Feign</Translate>
            </span>
          </dt>
          <dd>{centralSwitchEntity.feign ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="activePortApp.centralSwitch.location">Location</Translate>
          </dt>
          <dd>{centralSwitchEntity.locationCode ? centralSwitchEntity.locationCode : ''}</dd>
          <dt>
            <Translate contentKey="activePortApp.centralSwitch.ntutype">Ntutype</Translate>
          </dt>
          <dd>{centralSwitchEntity.ntutypeModel ? centralSwitchEntity.ntutypeModel : ''}</dd>
          <dt>
            <Translate contentKey="activePortApp.centralSwitch.ntutype">Ntutype</Translate>
          </dt>
          <dd>{centralSwitchEntity.ntutypeModel ? centralSwitchEntity.ntutypeModel : ''}</dd>
        </dl>
        <Button tag={Link} to="/central-switch" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/central-switch/${centralSwitchEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ centralSwitch }: IRootState) => ({
  centralSwitchEntity: centralSwitch.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CentralSwitchDetail);
