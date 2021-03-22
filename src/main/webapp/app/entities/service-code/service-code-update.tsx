import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IProviderConfiguration } from 'app/shared/model/provider-configuration.model';
import { getEntities as getProviderConfigurations } from 'app/entities/provider-configuration/provider-configuration.reducer';
import { IServiceType } from 'app/shared/model/service-type.model';
import { getEntities as getServiceTypes } from 'app/entities/service-type/service-type.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './service-code.reducer';
import { IServiceCode } from 'app/shared/model/service-code.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IServiceCodeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ServiceCodeUpdate = (props: IServiceCodeUpdateProps) => {
  const [providerTypeId, setProviderTypeId] = useState('0');
  const [serviceTypeId, setServiceTypeId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { serviceCodeEntity, providerConfigurations, serviceTypes, loading, updating } = props;

  const { command } = serviceCodeEntity;

  const handleClose = () => {
    props.history.push('/service-code' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getProviderConfigurations();
    props.getServiceTypes();
  }, []);

  const onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => props.setBlob(name, data, contentType), isAnImage);
  };

  const clearBlob = name => () => {
    props.setBlob(name, undefined, undefined);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...serviceCodeEntity,
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
          <h2 id="activePortApp.serviceCode.home.createOrEditLabel">
            <Translate contentKey="activePortApp.serviceCode.home.createOrEditLabel">Create or edit a ServiceCode</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : serviceCodeEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="service-code-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="service-code-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="service-code-name">
                  <Translate contentKey="activePortApp.serviceCode.name">Name</Translate>
                </Label>
                <AvField id="service-code-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="commandLabel" for="service-code-command">
                  <Translate contentKey="activePortApp.serviceCode.command">Command</Translate>
                </Label>
                <AvInput id="service-code-command" type="textarea" name="command" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="service-code-description">
                  <Translate contentKey="activePortApp.serviceCode.description">Description</Translate>
                </Label>
                <AvField id="service-code-description" type="text" name="description" />
              </AvGroup>
              <AvGroup check>
                <Label id="enabledLabel">
                  <AvInput id="service-code-enabled" type="checkbox" className="form-check-input" name="enabled" />
                  <Translate contentKey="activePortApp.serviceCode.enabled">Enabled</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="serviceUrlLabel" for="service-code-serviceUrl">
                  <Translate contentKey="activePortApp.serviceCode.serviceUrl">Service Url</Translate>
                </Label>
                <AvField id="service-code-serviceUrl" type="text" name="serviceUrl" />
              </AvGroup>
              <AvGroup>
                <Label id="hostedTypeLabel" for="service-code-hostedType">
                  <Translate contentKey="activePortApp.serviceCode.hostedType">Hosted Type</Translate>
                </Label>
                <AvInput
                  id="service-code-hostedType"
                  type="select"
                  className="form-control"
                  name="hostedType"
                  value={(!isNew && serviceCodeEntity.hostedType) || 'CIRCUIT'}
                >
                  <option value="CIRCUIT">{translate('activePortApp.HostedTypeEnum.CIRCUIT')}</option>
                  <option value="VLAN">{translate('activePortApp.HostedTypeEnum.VLAN')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="creationTypeLabel" for="service-code-creationType">
                  <Translate contentKey="activePortApp.serviceCode.creationType">Creation Type</Translate>
                </Label>
                <AvInput
                  id="service-code-creationType"
                  type="select"
                  className="form-control"
                  name="creationType"
                  value={(!isNew && serviceCodeEntity.creationType) || 'CIRCUITVLAN'}
                >
                  <option value="CIRCUITVLAN">{translate('activePortApp.CreationTypeEnum.CIRCUITVLAN')}</option>
                  <option value="AUTOGENVLAN">{translate('activePortApp.CreationTypeEnum.AUTOGENVLAN')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="tagLabel" for="service-code-tag">
                  <Translate contentKey="activePortApp.serviceCode.tag">Tag</Translate>
                </Label>
                <AvField id="service-code-tag" type="text" name="tag" />
              </AvGroup>
              <AvGroup>
                <Label id="dtoClassLabel" for="service-code-dtoClass">
                  <Translate contentKey="activePortApp.serviceCode.dtoClass">Dto Class</Translate>
                </Label>
                <AvField id="service-code-dtoClass" type="text" name="dtoClass" />
              </AvGroup>
              <AvGroup>
                <Label for="service-code-providerType">
                  <Translate contentKey="activePortApp.serviceCode.providerType">Provider Type</Translate>
                </Label>
                <AvInput id="service-code-providerType" type="select" className="form-control" name="providerTypeId">
                  <option value="" key="0" />
                  {providerConfigurations
                    ? providerConfigurations.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="service-code-serviceType">
                  <Translate contentKey="activePortApp.serviceCode.serviceType">Service Type</Translate>
                </Label>
                <AvInput id="service-code-serviceType" type="select" className="form-control" name="serviceTypeId">
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
              <Button tag={Link} id="cancel-save" to="/service-code" replace color="info">
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
  serviceTypes: storeState.serviceType.entities,
  serviceCodeEntity: storeState.serviceCode.entity,
  loading: storeState.serviceCode.loading,
  updating: storeState.serviceCode.updating,
  updateSuccess: storeState.serviceCode.updateSuccess,
});

const mapDispatchToProps = {
  getProviderConfigurations,
  getServiceTypes,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ServiceCodeUpdate);
