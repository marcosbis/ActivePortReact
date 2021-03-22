import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './service-type.reducer';
import { IServiceType } from 'app/shared/model/service-type.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IServiceTypeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ServiceTypeDetail = (props: IServiceTypeDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { serviceTypeEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.serviceType.detail.title">ServiceType</Translate> [<b>{serviceTypeEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="code">
              <Translate contentKey="activePortApp.serviceType.code">Code</Translate>
            </span>
          </dt>
          <dd>{serviceTypeEntity.code}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.serviceType.name">Name</Translate>
            </span>
          </dt>
          <dd>{serviceTypeEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.serviceType.description">Description</Translate>
            </span>
          </dt>
          <dd>{serviceTypeEntity.description}</dd>
        </dl>
        <Button tag={Link} to="/service-type" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/service-type/${serviceTypeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ serviceType }: IRootState) => ({
  serviceTypeEntity: serviceType.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ServiceTypeDetail);
