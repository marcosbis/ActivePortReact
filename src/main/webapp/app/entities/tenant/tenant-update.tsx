import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './tenant.reducer';
import { ITenant } from 'app/shared/model/tenant.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITenantUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TenantUpdate = (props: ITenantUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { tenantEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/tenant' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.slmDays = convertDateTimeToServer(values.slmDays);

    if (errors.length === 0) {
      const entity = {
        ...tenantEntity,
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
          <h2 id="activePortApp.tenant.home.createOrEditLabel">
            <Translate contentKey="activePortApp.tenant.home.createOrEditLabel">Create or edit a Tenant</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : tenantEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="tenant-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="tenant-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="tenant-name">
                  <Translate contentKey="activePortApp.tenant.name">Name</Translate>
                </Label>
                <AvField id="tenant-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="tenant-description">
                  <Translate contentKey="activePortApp.tenant.description">Description</Translate>
                </Label>
                <AvField id="tenant-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="tenantIdLabel" for="tenant-tenantId">
                  <Translate contentKey="activePortApp.tenant.tenantId">Tenant Id</Translate>
                </Label>
                <AvField id="tenant-tenantId" type="text" name="tenantId" />
              </AvGroup>
              <AvGroup check>
                <Label id="disableAccessLabel">
                  <AvInput id="tenant-disableAccess" type="checkbox" className="form-check-input" name="disableAccess" />
                  <Translate contentKey="activePortApp.tenant.disableAccess">Disable Access</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="ilmDaysLabel" for="tenant-ilmDays">
                  <Translate contentKey="activePortApp.tenant.ilmDays">Ilm Days</Translate>
                </Label>
                <AvField id="tenant-ilmDays" type="string" className="form-control" name="ilmDays" />
              </AvGroup>
              <AvGroup>
                <Label id="slmDaysLabel" for="tenant-slmDays">
                  <Translate contentKey="activePortApp.tenant.slmDays">Slm Days</Translate>
                </Label>
                <AvInput
                  id="tenant-slmDays"
                  type="datetime-local"
                  className="form-control"
                  name="slmDays"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.tenantEntity.slmDays)}
                />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/tenant" replace color="info">
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
  tenantEntity: storeState.tenant.entity,
  loading: storeState.tenant.loading,
  updating: storeState.tenant.updating,
  updateSuccess: storeState.tenant.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TenantUpdate);
