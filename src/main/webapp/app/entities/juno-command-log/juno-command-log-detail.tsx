import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './juno-command-log.reducer';
import { IJunoCommandLog } from 'app/shared/model/juno-command-log.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IJunoCommandLogDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const JunoCommandLogDetail = (props: IJunoCommandLogDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { junoCommandLogEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.junoCommandLog.detail.title">JunoCommandLog</Translate> [<b>{junoCommandLogEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="ntuId">
              <Translate contentKey="activePortApp.junoCommandLog.ntuId">Ntu Id</Translate>
            </span>
          </dt>
          <dd>{junoCommandLogEntity.ntuId}</dd>
          <dt>
            <span id="switchId">
              <Translate contentKey="activePortApp.junoCommandLog.switchId">Switch Id</Translate>
            </span>
          </dt>
          <dd>{junoCommandLogEntity.switchId}</dd>
          <dt>
            <span id="serviceId">
              <Translate contentKey="activePortApp.junoCommandLog.serviceId">Service Id</Translate>
            </span>
          </dt>
          <dd>{junoCommandLogEntity.serviceId}</dd>
          <dt>
            <span id="vxcId">
              <Translate contentKey="activePortApp.junoCommandLog.vxcId">Vxc Id</Translate>
            </span>
          </dt>
          <dd>{junoCommandLogEntity.vxcId}</dd>
          <dt>
            <span id="deviceUrl">
              <Translate contentKey="activePortApp.junoCommandLog.deviceUrl">Device Url</Translate>
            </span>
          </dt>
          <dd>{junoCommandLogEntity.deviceUrl}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="activePortApp.junoCommandLog.uuid">Uuid</Translate>
            </span>
          </dt>
          <dd>{junoCommandLogEntity.uuid}</dd>
          <dt>
            <span id="command">
              <Translate contentKey="activePortApp.junoCommandLog.command">Command</Translate>
            </span>
          </dt>
          <dd>{junoCommandLogEntity.command}</dd>
          <dt>
            <span id="cmdResponse">
              <Translate contentKey="activePortApp.junoCommandLog.cmdResponse">Cmd Response</Translate>
            </span>
          </dt>
          <dd>{junoCommandLogEntity.cmdResponse}</dd>
          <dt>
            <span id="executedDate">
              <Translate contentKey="activePortApp.junoCommandLog.executedDate">Executed Date</Translate>
            </span>
          </dt>
          <dd>
            {junoCommandLogEntity.executedDate ? (
              <TextFormat value={junoCommandLogEntity.executedDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="eventType">
              <Translate contentKey="activePortApp.junoCommandLog.eventType">Event Type</Translate>
            </span>
          </dt>
          <dd>{junoCommandLogEntity.eventType}</dd>
          <dt>
            <span id="user">
              <Translate contentKey="activePortApp.junoCommandLog.user">User</Translate>
            </span>
          </dt>
          <dd>{junoCommandLogEntity.user}</dd>
          <dt>
            <span id="deviceName">
              <Translate contentKey="activePortApp.junoCommandLog.deviceName">Device Name</Translate>
            </span>
          </dt>
          <dd>{junoCommandLogEntity.deviceName}</dd>
          <dt>
            <span id="targetType">
              <Translate contentKey="activePortApp.junoCommandLog.targetType">Target Type</Translate>
            </span>
          </dt>
          <dd>{junoCommandLogEntity.targetType}</dd>
          <dt>
            <span id="hasErrors">
              <Translate contentKey="activePortApp.junoCommandLog.hasErrors">Has Errors</Translate>
            </span>
          </dt>
          <dd>{junoCommandLogEntity.hasErrors ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/juno-command-log" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/juno-command-log/${junoCommandLogEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ junoCommandLog }: IRootState) => ({
  junoCommandLogEntity: junoCommandLog.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(JunoCommandLogDetail);
