import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './organization.reducer';
import { IOrganization } from 'app/shared/model/organization.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IOrganizationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OrganizationUpdate = (props: IOrganizationUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { organizationEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/organization' + props.location.search);
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
    if (errors.length === 0) {
      const entity = {
        ...organizationEntity,
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
          <h2 id="activePortApp.organization.home.createOrEditLabel">
            <Translate contentKey="activePortApp.organization.home.createOrEditLabel">Create or edit a Organization</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : organizationEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="organization-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="organization-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="organization-name">
                  <Translate contentKey="activePortApp.organization.name">Name</Translate>
                </Label>
                <AvField id="organization-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="hostIdLabel" for="organization-hostId">
                  <Translate contentKey="activePortApp.organization.hostId">Host Id</Translate>
                </Label>
                <AvField id="organization-hostId" type="text" name="hostId" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="organization-description">
                  <Translate contentKey="activePortApp.organization.description">Description</Translate>
                </Label>
                <AvField id="organization-description" type="text" name="description" />
              </AvGroup>
              <AvGroup check>
                <Label id="billingLabel">
                  <AvInput id="organization-billing" type="checkbox" className="form-check-input" name="billing" />
                  <Translate contentKey="activePortApp.organization.billing">Billing</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="timeZoneLabel" for="organization-timeZone">
                  <Translate contentKey="activePortApp.organization.timeZone">Time Zone</Translate>
                </Label>
                <AvField id="organization-timeZone" type="text" name="timeZone" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/organization" replace color="info">
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
  organizationEntity: storeState.organization.entity,
  loading: storeState.organization.loading,
  updating: storeState.organization.updating,
  updateSuccess: storeState.organization.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OrganizationUpdate);
