import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tile-tenant-configuration.reducer';
import { ITileTenantConfiguration } from 'app/shared/model/tile-tenant-configuration.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITileTenantConfigurationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TileTenantConfigurationDetail = (props: ITileTenantConfigurationDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { tileTenantConfigurationEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.tileTenantConfiguration.detail.title">TileTenantConfiguration</Translate> [
          <b>{tileTenantConfigurationEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="tenantId">
              <Translate contentKey="activePortApp.tileTenantConfiguration.tenantId">Tenant Id</Translate>
            </span>
          </dt>
          <dd>{tileTenantConfigurationEntity.tenantId}</dd>
          <dt>
            <span id="orgId">
              <Translate contentKey="activePortApp.tileTenantConfiguration.orgId">Org Id</Translate>
            </span>
          </dt>
          <dd>{tileTenantConfigurationEntity.orgId}</dd>
          <dt>
            <Translate contentKey="activePortApp.tileTenantConfiguration.tileConfiguration">Tile Configuration</Translate>
          </dt>
          <dd>{tileTenantConfigurationEntity.tileConfigurationName ? tileTenantConfigurationEntity.tileConfigurationName : ''}</dd>
        </dl>
        <Button tag={Link} to="/tile-tenant-configuration" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/tile-tenant-configuration/${tileTenantConfigurationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ tileTenantConfiguration }: IRootState) => ({
  tileTenantConfigurationEntity: tileTenantConfiguration.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TileTenantConfigurationDetail);
