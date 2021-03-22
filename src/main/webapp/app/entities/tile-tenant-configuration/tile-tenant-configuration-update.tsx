import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITileConfiguration } from 'app/shared/model/tile-configuration.model';
import { getEntities as getTileConfigurations } from 'app/entities/tile-configuration/tile-configuration.reducer';
import { getEntity, updateEntity, createEntity, reset } from './tile-tenant-configuration.reducer';
import { ITileTenantConfiguration } from 'app/shared/model/tile-tenant-configuration.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITileTenantConfigurationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TileTenantConfigurationUpdate = (props: ITileTenantConfigurationUpdateProps) => {
  const [tileConfigurationId, setTileConfigurationId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { tileTenantConfigurationEntity, tileConfigurations, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/tile-tenant-configuration' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getTileConfigurations();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...tileTenantConfigurationEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="activePortApp.tileTenantConfiguration.home.createOrEditLabel">
            <Translate contentKey="activePortApp.tileTenantConfiguration.home.createOrEditLabel">
              Create or edit a TileTenantConfiguration
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : tileTenantConfigurationEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="tile-tenant-configuration-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="tile-tenant-configuration-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="tenantIdLabel" for="tile-tenant-configuration-tenantId">
                  <Translate contentKey="activePortApp.tileTenantConfiguration.tenantId">Tenant Id</Translate>
                </Label>
                <AvField id="tile-tenant-configuration-tenantId" type="text" name="tenantId" />
              </AvGroup>
              <AvGroup>
                <Label id="orgIdLabel" for="tile-tenant-configuration-orgId">
                  <Translate contentKey="activePortApp.tileTenantConfiguration.orgId">Org Id</Translate>
                </Label>
                <AvField id="tile-tenant-configuration-orgId" type="text" name="orgId" />
              </AvGroup>
              <AvGroup>
                <Label for="tile-tenant-configuration-tileConfiguration">
                  <Translate contentKey="activePortApp.tileTenantConfiguration.tileConfiguration">Tile Configuration</Translate>
                </Label>
                <AvInput id="tile-tenant-configuration-tileConfiguration" type="select" className="form-control" name="tileConfigurationId">
                  <option value="" key="0" />
                  {tileConfigurations
                    ? tileConfigurations.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/tile-tenant-configuration" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  tileConfigurations: storeState.tileConfiguration.entities,
  tileTenantConfigurationEntity: storeState.tileTenantConfiguration.entity,
  loading: storeState.tileTenantConfiguration.loading,
  updating: storeState.tileTenantConfiguration.updating,
  updateSuccess: storeState.tileTenantConfiguration.updateSuccess,
});

const mapDispatchToProps = {
  getTileConfigurations,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TileTenantConfigurationUpdate);
