import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './notification-event.reducer';
import { INotificationEvent } from 'app/shared/model/notification-event.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INotificationEventDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NotificationEventDetail = (props: INotificationEventDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { notificationEventEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.notificationEvent.detail.title">NotificationEvent</Translate> [
          <b>{notificationEventEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="ntuName">
              <Translate contentKey="activePortApp.notificationEvent.ntuName">Ntu Name</Translate>
            </span>
          </dt>
          <dd>{notificationEventEntity.ntuName}</dd>
          <dt>
            <span id="ntuId">
              <Translate contentKey="activePortApp.notificationEvent.ntuId">Ntu Id</Translate>
            </span>
          </dt>
          <dd>{notificationEventEntity.ntuId}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="activePortApp.notificationEvent.status">Status</Translate>
            </span>
          </dt>
          <dd>{notificationEventEntity.status}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="activePortApp.notificationEvent.type">Type</Translate>
            </span>
          </dt>
          <dd>{notificationEventEntity.type}</dd>
          <dt>
            <span id="message">
              <Translate contentKey="activePortApp.notificationEvent.message">Message</Translate>
            </span>
          </dt>
          <dd>{notificationEventEntity.message}</dd>
          <dt>
            <span id="serviceName">
              <Translate contentKey="activePortApp.notificationEvent.serviceName">Service Name</Translate>
            </span>
          </dt>
          <dd>{notificationEventEntity.serviceName}</dd>
          <dt>
            <span id="serviceId">
              <Translate contentKey="activePortApp.notificationEvent.serviceId">Service Id</Translate>
            </span>
          </dt>
          <dd>{notificationEventEntity.serviceId}</dd>
          <dt>
            <span id="vxcId">
              <Translate contentKey="activePortApp.notificationEvent.vxcId">Vxc Id</Translate>
            </span>
          </dt>
          <dd>{notificationEventEntity.vxcId}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="activePortApp.notificationEvent.uuid">Uuid</Translate>
            </span>
          </dt>
          <dd>{notificationEventEntity.uuid}</dd>
          <dt>
            <span id="user">
              <Translate contentKey="activePortApp.notificationEvent.user">User</Translate>
            </span>
          </dt>
          <dd>{notificationEventEntity.user}</dd>
          <dt>
            <span id="errorMessage">
              <Translate contentKey="activePortApp.notificationEvent.errorMessage">Error Message</Translate>
            </span>
          </dt>
          <dd>{notificationEventEntity.errorMessage}</dd>
          <dt>
            <Translate contentKey="activePortApp.notificationEvent.job">Job</Translate>
          </dt>
          <dd>{notificationEventEntity.jobId ? notificationEventEntity.jobId : ''}</dd>
        </dl>
        <Button tag={Link} to="/notification-event" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/notification-event/${notificationEventEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ notificationEvent }: IRootState) => ({
  notificationEventEntity: notificationEvent.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NotificationEventDetail);
