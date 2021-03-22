import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tenant.reducer';
import { ITenant } from 'app/shared/model/tenant.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITenantDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TenantDetail = (props: ITenantDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { tenantEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.tenant.detail.title">Tenant</Translate> [<b>{tenantEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.tenant.name">Name</Translate>
            </span>
          </dt>
          <dd>{tenantEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.tenant.description">Description</Translate>
            </span>
          </dt>
          <dd>{tenantEntity.description}</dd>
          <dt>
            <span id="tenantId">
              <Translate contentKey="activePortApp.tenant.tenantId">Tenant Id</Translate>
            </span>
          </dt>
          <dd>{tenantEntity.tenantId}</dd>
          <dt>
            <span id="disableAccess">
              <Translate contentKey="activePortApp.tenant.disableAccess">Disable Access</Translate>
            </span>
          </dt>
          <dd>{tenantEntity.disableAccess ? 'true' : 'false'}</dd>
          <dt>
            <span id="ilmDays">
              <Translate contentKey="activePortApp.tenant.ilmDays">Ilm Days</Translate>
            </span>
          </dt>
          <dd>{tenantEntity.ilmDays}</dd>
          <dt>
            <span id="slmDays">
              <Translate contentKey="activePortApp.tenant.slmDays">Slm Days</Translate>
            </span>
          </dt>
          <dd>{tenantEntity.slmDays ? <TextFormat value={tenantEntity.slmDays} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
        </dl>
        <Button tag={Link} to="/tenant" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/tenant/${tenantEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ tenant }: IRootState) => ({
  tenantEntity: tenant.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TenantDetail);
