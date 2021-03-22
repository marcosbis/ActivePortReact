import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IServiceType } from 'app/shared/model/service-type.model';
import { getEntities as getServiceTypes } from 'app/entities/service-type/service-type.reducer';
import { IServiceConfiguration } from 'app/shared/model/service-configuration.model';
import { getEntities as getServiceConfigurations } from 'app/entities/service-configuration/service-configuration.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './tile-configuration.reducer';
import { ITileConfiguration } from 'app/shared/model/tile-configuration.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITileConfigurationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TileConfigurationUpdate = (props: ITileConfigurationUpdateProps) => {
  const [idsservices, setIdsservices] = useState([]);
  const [serviceTypeId, setServiceTypeId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { tileConfigurationEntity, serviceTypes, serviceConfigurations, loading, updating } = props;

  const { command } = tileConfigurationEntity;

  const handleClose = () => {
    props.history.push('/tile-configuration' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getServiceTypes();
    props.getServiceConfigurations();
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
        ...tileConfigurationEntity,
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
          <h2 id="activePortApp.tileConfiguration.home.createOrEditLabel">
            <Translate contentKey="activePortApp.tileConfiguration.home.createOrEditLabel">Create or edit a TileConfiguration</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : tileConfigurationEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="tile-configuration-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="tile-configuration-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="tile-configuration-name">
                  <Translate contentKey="activePortApp.tileConfiguration.name">Name</Translate>
                </Label>
                <AvField id="tile-configuration-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="tile-configuration-description">
                  <Translate contentKey="activePortApp.tileConfiguration.description">Description</Translate>
                </Label>
                <AvField id="tile-configuration-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="commandLabel" for="tile-configuration-command">
                  <Translate contentKey="activePortApp.tileConfiguration.command">Command</Translate>
                </Label>
                <AvInput id="tile-configuration-command" type="textarea" name="command" />
              </AvGroup>
              <AvGroup>
                <Label for="tile-configuration-serviceType">
                  <Translate contentKey="activePortApp.tileConfiguration.serviceType">Service Type</Translate>
                </Label>
                <AvInput id="tile-configuration-serviceType" type="select" className="form-control" name="serviceTypeId">
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
              <AvGroup>
                <Label for="tile-configuration-services">
                  <Translate contentKey="activePortApp.tileConfiguration.services">Services</Translate>
                </Label>
                <AvInput
                  id="tile-configuration-services"
                  type="select"
                  multiple
                  className="form-control"
                  name="services"
                  value={tileConfigurationEntity.services && tileConfigurationEntity.services.map(e => e.id)}
                >
                  <option value="" key="0" />
                  {serviceConfigurations
                    ? serviceConfigurations.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/tile-configuration" replace color="info">
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
  serviceConfigurations: storeState.serviceConfiguration.entities,
  tileConfigurationEntity: storeState.tileConfiguration.entity,
  loading: storeState.tileConfiguration.loading,
  updating: storeState.tileConfiguration.updating,
  updateSuccess: storeState.tileConfiguration.updateSuccess,
});

const mapDispatchToProps = {
  getServiceTypes,
  getServiceConfigurations,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TileConfigurationUpdate);
