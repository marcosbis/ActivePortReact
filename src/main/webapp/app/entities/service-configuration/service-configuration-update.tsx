import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IServiceCode } from 'app/shared/model/service-code.model';
import { getEntities as getServiceCodes } from 'app/entities/service-code/service-code.reducer';
import { IPartner } from 'app/shared/model/partner.model';
import { getEntities as getPartners } from 'app/entities/partner/partner.reducer';
import { IItemCode } from 'app/shared/model/item-code.model';
import { getEntities as getItemCodes } from 'app/entities/item-code/item-code.reducer';
import { IServiceCommand } from 'app/shared/model/service-command.model';
import { getEntities as getServiceCommands } from 'app/entities/service-command/service-command.reducer';
import { ITileConfiguration } from 'app/shared/model/tile-configuration.model';
import { getEntities as getTileConfigurations } from 'app/entities/tile-configuration/tile-configuration.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './service-configuration.reducer';
import { IServiceConfiguration } from 'app/shared/model/service-configuration.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IServiceConfigurationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ServiceConfigurationUpdate = (props: IServiceConfigurationUpdateProps) => {
  const [idscommands, setIdscommands] = useState([]);
  const [serviceCodeId, setServiceCodeId] = useState('0');
  const [providerId, setProviderId] = useState('0');
  const [priceCodeId, setPriceCodeId] = useState('0');
  const [tileConfigurationId, setTileConfigurationId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { serviceConfigurationEntity, serviceCodes, partners, itemCodes, serviceCommands, tileConfigurations, loading, updating } = props;

  const { command } = serviceConfigurationEntity;

  const handleClose = () => {
    props.history.push('/service-configuration' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getServiceCodes();
    props.getPartners();
    props.getItemCodes();
    props.getServiceCommands();
    props.getTileConfigurations();
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
        ...serviceConfigurationEntity,
        ...values,
        commands: mapIdList(values.commands),
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
          <h2 id="activePortApp.serviceConfiguration.home.createOrEditLabel">
            <Translate contentKey="activePortApp.serviceConfiguration.home.createOrEditLabel">
              Create or edit a ServiceConfiguration
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : serviceConfigurationEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="service-configuration-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="service-configuration-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="service-configuration-name">
                  <Translate contentKey="activePortApp.serviceConfiguration.name">Name</Translate>
                </Label>
                <AvField id="service-configuration-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="service-configuration-description">
                  <Translate contentKey="activePortApp.serviceConfiguration.description">Description</Translate>
                </Label>
                <AvField id="service-configuration-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="tenantTypeLabel" for="service-configuration-tenantType">
                  <Translate contentKey="activePortApp.serviceConfiguration.tenantType">Tenant Type</Translate>
                </Label>
                <AvInput
                  id="service-configuration-tenantType"
                  type="select"
                  className="form-control"
                  name="tenantType"
                  value={(!isNew && serviceConfigurationEntity.tenantType) || 'ALL'}
                >
                  <option value="ALL">{translate('activePortApp.TenantTypeEnum.ALL')}</option>
                  <option value="SINGLE">{translate('activePortApp.TenantTypeEnum.SINGLE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="commandLabel" for="service-configuration-command">
                  <Translate contentKey="activePortApp.serviceConfiguration.command">Command</Translate>
                </Label>
                <AvInput id="service-configuration-command" type="textarea" name="command" />
              </AvGroup>
              <AvGroup>
                <Label id="testLabel" for="service-configuration-test">
                  <Translate contentKey="activePortApp.serviceConfiguration.test">Test</Translate>
                </Label>
                <AvField id="service-configuration-test" type="text" name="test" />
              </AvGroup>
              <AvGroup check>
                <Label id="useDefaultCommandsLabel">
                  <AvInput
                    id="service-configuration-useDefaultCommands"
                    type="checkbox"
                    className="form-check-input"
                    name="useDefaultCommands"
                  />
                  <Translate contentKey="activePortApp.serviceConfiguration.useDefaultCommands">Use Default Commands</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="service-configuration-serviceCode">
                  <Translate contentKey="activePortApp.serviceConfiguration.serviceCode">Service Code</Translate>
                </Label>
                <AvInput id="service-configuration-serviceCode" type="select" className="form-control" name="serviceCodeId">
                  <option value="" key="0" />
                  {serviceCodes
                    ? serviceCodes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="service-configuration-provider">
                  <Translate contentKey="activePortApp.serviceConfiguration.provider">Provider</Translate>
                </Label>
                <AvInput id="service-configuration-provider" type="select" className="form-control" name="providerId">
                  <option value="" key="0" />
                  {partners
                    ? partners.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="service-configuration-priceCode">
                  <Translate contentKey="activePortApp.serviceConfiguration.priceCode">Price Code</Translate>
                </Label>
                <AvInput id="service-configuration-priceCode" type="select" className="form-control" name="priceCodeId">
                  <option value="" key="0" />
                  {itemCodes
                    ? itemCodes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="service-configuration-commands">
                  <Translate contentKey="activePortApp.serviceConfiguration.commands">Commands</Translate>
                </Label>
                <AvInput
                  id="service-configuration-commands"
                  type="select"
                  multiple
                  className="form-control"
                  name="commands"
                  value={serviceConfigurationEntity.commands && serviceConfigurationEntity.commands.map(e => e.id)}
                >
                  <option value="" key="0" />
                  {serviceCommands
                    ? serviceCommands.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/service-configuration" replace color="info">
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
  serviceCodes: storeState.serviceCode.entities,
  partners: storeState.partner.entities,
  itemCodes: storeState.itemCode.entities,
  serviceCommands: storeState.serviceCommand.entities,
  tileConfigurations: storeState.tileConfiguration.entities,
  serviceConfigurationEntity: storeState.serviceConfiguration.entity,
  loading: storeState.serviceConfiguration.loading,
  updating: storeState.serviceConfiguration.updating,
  updateSuccess: storeState.serviceConfiguration.updateSuccess,
});

const mapDispatchToProps = {
  getServiceCodes,
  getPartners,
  getItemCodes,
  getServiceCommands,
  getTileConfigurations,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ServiceConfigurationUpdate);
