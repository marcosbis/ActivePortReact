import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IProviderConfiguration } from 'app/shared/model/provider-configuration.model';
import { getEntities as getProviderConfigurations } from 'app/entities/provider-configuration/provider-configuration.reducer';
import { getEntity, updateEntity, createEntity, reset } from './service-type.reducer';
import { IServiceType } from 'app/shared/model/service-type.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IServiceTypeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ServiceTypeUpdate = (props: IServiceTypeUpdateProps) => {
  const [providerId, setProviderId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { serviceTypeEntity, providerConfigurations, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/service-type' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getProviderConfigurations();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...serviceTypeEntity,
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
          <h2 id="activePortApp.serviceType.home.createOrEditLabel">
            <Translate contentKey="activePortApp.serviceType.home.createOrEditLabel">Create or edit a ServiceType</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : serviceTypeEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="service-type-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="service-type-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="codeLabel" for="service-type-code">
                  <Translate contentKey="activePortApp.serviceType.code">Code</Translate>
                </Label>
                <AvField id="service-type-code" type="text" name="code" />
              </AvGroup>
              <AvGroup>
                <Label id="nameLabel" for="service-type-name">
                  <Translate contentKey="activePortApp.serviceType.name">Name</Translate>
                </Label>
                <AvField id="service-type-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="service-type-description">
                  <Translate contentKey="activePortApp.serviceType.description">Description</Translate>
                </Label>
                <AvField id="service-type-description" type="text" name="description" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/service-type" replace color="info">
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
  providerConfigurations: storeState.providerConfiguration.entities,
  serviceTypeEntity: storeState.serviceType.entity,
  loading: storeState.serviceType.loading,
  updating: storeState.serviceType.updating,
  updateSuccess: storeState.serviceType.updateSuccess,
});

const mapDispatchToProps = {
  getProviderConfigurations,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ServiceTypeUpdate);
