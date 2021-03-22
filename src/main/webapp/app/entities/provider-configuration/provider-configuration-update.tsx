import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IServiceType } from 'app/shared/model/service-type.model';
import { getEntities as getServiceTypes } from 'app/entities/service-type/service-type.reducer';
import { getEntity, updateEntity, createEntity, reset } from './provider-configuration.reducer';
import { IProviderConfiguration } from 'app/shared/model/provider-configuration.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IProviderConfigurationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProviderConfigurationUpdate = (props: IProviderConfigurationUpdateProps) => {
  const [idsservices, setIdsservices] = useState([]);
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { providerConfigurationEntity, serviceTypes, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/provider-configuration' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getServiceTypes();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...providerConfigurationEntity,
        ...values,
        services: mapIdList(values.services),
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
          <h2 id="activePortApp.providerConfiguration.home.createOrEditLabel">
            <Translate contentKey="activePortApp.providerConfiguration.home.createOrEditLabel">
              Create or edit a ProviderConfiguration
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : providerConfigurationEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="provider-configuration-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="provider-configuration-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="provider-configuration-name">
                  <Translate contentKey="activePortApp.providerConfiguration.name">Name</Translate>
                </Label>
                <AvField id="provider-configuration-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="provider-configuration-description">
                  <Translate contentKey="activePortApp.providerConfiguration.description">Description</Translate>
                </Label>
                <AvField id="provider-configuration-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="typeLabel" for="provider-configuration-type">
                  <Translate contentKey="activePortApp.providerConfiguration.type">Type</Translate>
                </Label>
                <AvInput
                  id="provider-configuration-type"
                  type="select"
                  className="form-control"
                  name="type"
                  value={(!isNew && providerConfigurationEntity.type) || 'MEGAPORT'}
                >
                  <option value="MEGAPORT">{translate('activePortApp.PartnerTypeEnum.MEGAPORT')}</option>
                  <option value="ISP">{translate('activePortApp.PartnerTypeEnum.ISP')}</option>
                  <option value="ZETTA">{translate('activePortApp.PartnerTypeEnum.ZETTA')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="apiTypeLabel" for="provider-configuration-apiType">
                  <Translate contentKey="activePortApp.providerConfiguration.apiType">Api Type</Translate>
                </Label>
                <AvInput
                  id="provider-configuration-apiType"
                  type="select"
                  className="form-control"
                  name="apiType"
                  value={(!isNew && providerConfigurationEntity.apiType) || 'NONE'}
                >
                  <option value="NONE">{translate('activePortApp.ApiTypeEnum.NONE')}</option>
                  <option value="CLIENT">{translate('activePortApp.ApiTypeEnum.CLIENT')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup check>
                <Label id="hasPortIdLabel">
                  <AvInput id="provider-configuration-hasPortId" type="checkbox" className="form-check-input" name="hasPortId" />
                  <Translate contentKey="activePortApp.providerConfiguration.hasPortId">Has Port Id</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="provider-configuration-services">
                  <Translate contentKey="activePortApp.providerConfiguration.services">Services</Translate>
                </Label>
                <AvInput
                  id="provider-configuration-services"
                  type="select"
                  multiple
                  className="form-control"
                  name="services"
                  value={providerConfigurationEntity.services && providerConfigurationEntity.services.map(e => e.id)}
                >
                  <option value="" key="0" />
                  {serviceTypes
                    ? serviceTypes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.code}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/provider-configuration" replace color="info">
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
  serviceTypes: storeState.serviceType.entities,
  providerConfigurationEntity: storeState.providerConfiguration.entity,
  loading: storeState.providerConfiguration.loading,
  updating: storeState.providerConfiguration.updating,
  updateSuccess: storeState.providerConfiguration.updateSuccess,
});

const mapDispatchToProps = {
  getServiceTypes,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProviderConfigurationUpdate);
