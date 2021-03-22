import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IServiceConfiguration } from 'app/shared/model/service-configuration.model';
import { getEntities as getServiceConfigurations } from 'app/entities/service-configuration/service-configuration.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './service-command.reducer';
import { IServiceCommand } from 'app/shared/model/service-command.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IServiceCommandUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ServiceCommandUpdate = (props: IServiceCommandUpdateProps) => {
  const [serviceConfigurationId, setServiceConfigurationId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { serviceCommandEntity, serviceConfigurations, loading, updating } = props;

  const { command } = serviceCommandEntity;

  const handleClose = () => {
    props.history.push('/service-command' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

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
        ...serviceCommandEntity,
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
          <h2 id="activePortApp.serviceCommand.home.createOrEditLabel">
            <Translate contentKey="activePortApp.serviceCommand.home.createOrEditLabel">Create or edit a ServiceCommand</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : serviceCommandEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="service-command-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="service-command-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="service-command-name">
                  <Translate contentKey="activePortApp.serviceCommand.name">Name</Translate>
                </Label>
                <AvField id="service-command-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="commandLabel" for="service-command-command">
                  <Translate contentKey="activePortApp.serviceCommand.command">Command</Translate>
                </Label>
                <AvInput id="service-command-command" type="textarea" name="command" />
              </AvGroup>
              <AvGroup>
                <Label id="onEventLabel" for="service-command-onEvent">
                  <Translate contentKey="activePortApp.serviceCommand.onEvent">On Event</Translate>
                </Label>
                <AvInput
                  id="service-command-onEvent"
                  type="select"
                  className="form-control"
                  name="onEvent"
                  value={(!isNew && serviceCommandEntity.onEvent) || 'BEFORE_CREATE_SERVICE'}
                >
                  <option value="BEFORE_CREATE_SERVICE">{translate('activePortApp.OnEventTypeEnum.BEFORE_CREATE_SERVICE')}</option>
                  <option value="CREATE_SERVICE">{translate('activePortApp.OnEventTypeEnum.CREATE_SERVICE')}</option>
                  <option value="DISABLE_SERVICE_FROM_NTU">{translate('activePortApp.OnEventTypeEnum.DISABLE_SERVICE_FROM_NTU')}</option>
                  <option value="DELETE_SERVICE">{translate('activePortApp.OnEventTypeEnum.DELETE_SERVICE')}</option>
                  <option value="UPDATE_SERVICE">{translate('activePortApp.OnEventTypeEnum.UPDATE_SERVICE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="onServiceLabel" for="service-command-onService">
                  <Translate contentKey="activePortApp.serviceCommand.onService">On Service</Translate>
                </Label>
                <AvInput
                  id="service-command-onService"
                  type="select"
                  className="form-control"
                  name="onService"
                  value={(!isNew && serviceCommandEntity.onService) || 'ANY'}
                >
                  <option value="ANY">{translate('activePortApp.ServiceTypeEnum.ANY')}</option>
                  <option value="AWS">{translate('activePortApp.ServiceTypeEnum.AWS')}</option>
                  <option value="IX">{translate('activePortApp.ServiceTypeEnum.IX')}</option>
                  <option value="VXC">{translate('activePortApp.ServiceTypeEnum.VXC')}</option>
                  <option value="AZURE">{translate('activePortApp.ServiceTypeEnum.AZURE')}</option>
                  <option value="AZURE_PRIVATE">{translate('activePortApp.ServiceTypeEnum.AZURE_PRIVATE')}</option>
                  <option value="AZURE_PUBLIC">{translate('activePortApp.ServiceTypeEnum.AZURE_PUBLIC')}</option>
                  <option value="AZURE_MICROSOFT">{translate('activePortApp.ServiceTypeEnum.AZURE_MICROSOFT')}</option>
                  <option value="OFFICE">{translate('activePortApp.ServiceTypeEnum.OFFICE')}</option>
                  <option value="INTERNET">{translate('activePortApp.ServiceTypeEnum.INTERNET')}</option>
                  <option value="ZETTA_INTERNET">{translate('activePortApp.ServiceTypeEnum.ZETTA_INTERNET')}</option>
                  <option value="DNS_FILTER">{translate('activePortApp.ServiceTypeEnum.DNS_FILTER')}</option>
                  <option value="ZETTA_SERVICE">{translate('activePortApp.ServiceTypeEnum.ZETTA_SERVICE')}</option>
                  <option value="INTERNET_LAYER3">{translate('activePortApp.ServiceTypeEnum.INTERNET_LAYER3')}</option>
                  <option value="MCON">{translate('activePortApp.ServiceTypeEnum.MCON')}</option>
                  <option value="ALIBABA">{translate('activePortApp.ServiceTypeEnum.ALIBABA')}</option>
                  <option value="IBM">{translate('activePortApp.ServiceTypeEnum.IBM')}</option>
                  <option value="GOOGLE">{translate('activePortApp.ServiceTypeEnum.GOOGLE')}</option>
                  <option value="ORACLE">{translate('activePortApp.ServiceTypeEnum.ORACLE')}</option>
                  <option value="XC">{translate('activePortApp.ServiceTypeEnum.XC')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="deviceTypeLabel" for="service-command-deviceType">
                  <Translate contentKey="activePortApp.serviceCommand.deviceType">Device Type</Translate>
                </Label>
                <AvInput
                  id="service-command-deviceType"
                  type="select"
                  className="form-control"
                  name="deviceType"
                  value={(!isNew && serviceCommandEntity.deviceType) || 'NTU'}
                >
                  <option value="NTU">{translate('activePortApp.NtuSerieEnum.NTU')}</option>
                  <option value="PE">{translate('activePortApp.NtuSerieEnum.PE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup check>
                <Label id="enabledLabel">
                  <AvInput id="service-command-enabled" type="checkbox" className="form-check-input" name="enabled" />
                  <Translate contentKey="activePortApp.serviceCommand.enabled">Enabled</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="circuitTypeLabel" for="service-command-circuitType">
                  <Translate contentKey="activePortApp.serviceCommand.circuitType">Circuit Type</Translate>
                </Label>
                <AvInput
                  id="service-command-circuitType"
                  type="select"
                  className="form-control"
                  name="circuitType"
                  value={(!isNew && serviceCommandEntity.circuitType) || 'CIRCUIT'}
                >
                  <option value="CIRCUIT">{translate('activePortApp.FilterCommandTypeEnum.CIRCUIT')}</option>
                  <option value="ROUTING_ROUTE">{translate('activePortApp.FilterCommandTypeEnum.ROUTING_ROUTE')}</option>
                  <option value="ROUTING_NAT">{translate('activePortApp.FilterCommandTypeEnum.ROUTING_NAT')}</option>
                  <option value="ANY">{translate('activePortApp.FilterCommandTypeEnum.ANY')}</option>
                  <option value="POLICER">{translate('activePortApp.FilterCommandTypeEnum.POLICER')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="tagLabel" for="service-command-tag">
                  <Translate contentKey="activePortApp.serviceCommand.tag">Tag</Translate>
                </Label>
                <AvField id="service-command-tag" type="text" name="tag" />
              </AvGroup>
              <AvGroup>
                <Label id="osTypeLabel" for="service-command-osType">
                  <Translate contentKey="activePortApp.serviceCommand.osType">Os Type</Translate>
                </Label>
                <AvInput
                  id="service-command-osType"
                  type="select"
                  className="form-control"
                  name="osType"
                  value={(!isNew && serviceCommandEntity.osType) || 'JUNOS'}
                >
                  <option value="JUNOS">{translate('activePortApp.OsTypeEnum.JUNOS')}</option>
                  <option value="MIKROTIK">{translate('activePortApp.OsTypeEnum.MIKROTIK')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="entryTypeLabel" for="service-command-entryType">
                  <Translate contentKey="activePortApp.serviceCommand.entryType">Entry Type</Translate>
                </Label>
                <AvInput
                  id="service-command-entryType"
                  type="select"
                  className="form-control"
                  name="entryType"
                  value={(!isNew && serviceCommandEntity.entryType) || 'SYSTEM'}
                >
                  <option value="SYSTEM">{translate('activePortApp.EntityTypeEnum.SYSTEM')}</option>
                  <option value="SETTING">{translate('activePortApp.EntityTypeEnum.SETTING')}</option>
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/service-command" replace color="info">
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
  serviceConfigurations: storeState.serviceConfiguration.entities,
  serviceCommandEntity: storeState.serviceCommand.entity,
  loading: storeState.serviceCommand.loading,
  updating: storeState.serviceCommand.updating,
  updateSuccess: storeState.serviceCommand.updateSuccess,
});

const mapDispatchToProps = {
  getServiceConfigurations,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ServiceCommandUpdate);
