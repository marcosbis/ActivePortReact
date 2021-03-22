import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './config-job.reducer';
import { IConfigJob } from 'app/shared/model/config-job.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IConfigJobDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ConfigJobDetail = (props: IConfigJobDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { configJobEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.configJob.detail.title">ConfigJob</Translate> [<b>{configJobEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="hostId">
              <Translate contentKey="activePortApp.configJob.hostId">Host Id</Translate>
            </span>
          </dt>
          <dd>{configJobEntity.hostId}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="activePortApp.configJob.status">Status</Translate>
            </span>
          </dt>
          <dd>{configJobEntity.status}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="activePortApp.configJob.uuid">Uuid</Translate>
            </span>
          </dt>
          <dd>{configJobEntity.uuid}</dd>
          <dt>
            <span id="message">
              <Translate contentKey="activePortApp.configJob.message">Message</Translate>
            </span>
          </dt>
          <dd>{configJobEntity.message}</dd>
          <dt>
            <span id="command">
              <Translate contentKey="activePortApp.configJob.command">Command</Translate>
            </span>
          </dt>
          <dd>{configJobEntity.command}</dd>
          <dt>
            <span id="executed">
              <Translate contentKey="activePortApp.configJob.executed">Executed</Translate>
            </span>
          </dt>
          <dd>{configJobEntity.executed ? <TextFormat value={configJobEntity.executed} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="executedStatus">
              <Translate contentKey="activePortApp.configJob.executedStatus">Executed Status</Translate>
            </span>
          </dt>
          <dd>{configJobEntity.executedStatus}</dd>
          <dt>
            <span id="executedMessage">
              <Translate contentKey="activePortApp.configJob.executedMessage">Executed Message</Translate>
            </span>
          </dt>
          <dd>{configJobEntity.executedMessage}</dd>
          <dt>
            <span id="ntuId">
              <Translate contentKey="activePortApp.configJob.ntuId">Ntu Id</Translate>
            </span>
          </dt>
          <dd>{configJobEntity.ntuId}</dd>
          <dt>
            <span id="eventType">
              <Translate contentKey="activePortApp.configJob.eventType">Event Type</Translate>
            </span>
          </dt>
          <dd>{configJobEntity.eventType}</dd>
          <dt>
            <span id="user">
              <Translate contentKey="activePortApp.configJob.user">User</Translate>
            </span>
          </dt>
          <dd>{configJobEntity.user}</dd>
          <dt>
            <span id="errorMessage">
              <Translate contentKey="activePortApp.configJob.errorMessage">Error Message</Translate>
            </span>
          </dt>
          <dd>{configJobEntity.errorMessage}</dd>
          <dt>
            <span id="callbackUrl">
              <Translate contentKey="activePortApp.configJob.callbackUrl">Callback Url</Translate>
            </span>
          </dt>
          <dd>{configJobEntity.callbackUrl}</dd>
        </dl>
        <Button tag={Link} to="/config-job" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/config-job/${configJobEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ configJob }: IRootState) => ({
  configJobEntity: configJob.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ConfigJobDetail);
