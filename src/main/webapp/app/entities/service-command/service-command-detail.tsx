import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './service-command.reducer';
import { IServiceCommand } from 'app/shared/model/service-command.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IServiceCommandDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ServiceCommandDetail = (props: IServiceCommandDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { serviceCommandEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.serviceCommand.detail.title">ServiceCommand</Translate> [<b>{serviceCommandEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.serviceCommand.name">Name</Translate>
            </span>
          </dt>
          <dd>{serviceCommandEntity.name}</dd>
          <dt>
            <span id="command">
              <Translate contentKey="activePortApp.serviceCommand.command">Command</Translate>
            </span>
          </dt>
          <dd>{serviceCommandEntity.command}</dd>
          <dt>
            <span id="onEvent">
              <Translate contentKey="activePortApp.serviceCommand.onEvent">On Event</Translate>
            </span>
          </dt>
          <dd>{serviceCommandEntity.onEvent}</dd>
          <dt>
            <span id="onService">
              <Translate contentKey="activePortApp.serviceCommand.onService">On Service</Translate>
            </span>
          </dt>
          <dd>{serviceCommandEntity.onService}</dd>
          <dt>
            <span id="deviceType">
              <Translate contentKey="activePortApp.serviceCommand.deviceType">Device Type</Translate>
            </span>
          </dt>
          <dd>{serviceCommandEntity.deviceType}</dd>
          <dt>
            <span id="enabled">
              <Translate contentKey="activePortApp.serviceCommand.enabled">Enabled</Translate>
            </span>
          </dt>
          <dd>{serviceCommandEntity.enabled ? 'true' : 'false'}</dd>
          <dt>
            <span id="circuitType">
              <Translate contentKey="activePortApp.serviceCommand.circuitType">Circuit Type</Translate>
            </span>
          </dt>
          <dd>{serviceCommandEntity.circuitType}</dd>
          <dt>
            <span id="tag">
              <Translate contentKey="activePortApp.serviceCommand.tag">Tag</Translate>
            </span>
          </dt>
          <dd>{serviceCommandEntity.tag}</dd>
          <dt>
            <span id="osType">
              <Translate contentKey="activePortApp.serviceCommand.osType">Os Type</Translate>
            </span>
          </dt>
          <dd>{serviceCommandEntity.osType}</dd>
          <dt>
            <span id="entryType">
              <Translate contentKey="activePortApp.serviceCommand.entryType">Entry Type</Translate>
            </span>
          </dt>
          <dd>{serviceCommandEntity.entryType}</dd>
        </dl>
        <Button tag={Link} to="/service-command" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/service-command/${serviceCommandEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ serviceCommand }: IRootState) => ({
  serviceCommandEntity: serviceCommand.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ServiceCommandDetail);
