import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './organization.reducer';
import { IOrganization } from 'app/shared/model/organization.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOrganizationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OrganizationDetail = (props: IOrganizationDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { organizationEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.organization.detail.title">Organization</Translate> [<b>{organizationEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.organization.name">Name</Translate>
            </span>
          </dt>
          <dd>{organizationEntity.name}</dd>
          <dt>
            <span id="hostId">
              <Translate contentKey="activePortApp.organization.hostId">Host Id</Translate>
            </span>
          </dt>
          <dd>{organizationEntity.hostId}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.organization.description">Description</Translate>
            </span>
          </dt>
          <dd>{organizationEntity.description}</dd>
          <dt>
            <span id="billing">
              <Translate contentKey="activePortApp.organization.billing">Billing</Translate>
            </span>
          </dt>
          <dd>{organizationEntity.billing ? 'true' : 'false'}</dd>
          <dt>
            <span id="timeZone">
              <Translate contentKey="activePortApp.organization.timeZone">Time Zone</Translate>
            </span>
          </dt>
          <dd>{organizationEntity.timeZone}</dd>
        </dl>
        <Button tag={Link} to="/organization" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/organization/${organizationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ organization }: IRootState) => ({
  organizationEntity: organization.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OrganizationDetail);
