import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IServiceConfiguration } from 'app/shared/model/service-configuration.model';
import { getEntities as getServiceConfigurations } from 'app/entities/service-configuration/service-configuration.reducer';
import { getEntity, updateEntity, createEntity, reset } from './circuit-vlan.reducer';
import { ICircuitVlan } from 'app/shared/model/circuit-vlan.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICircuitVlanUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CircuitVlanUpdate = (props: ICircuitVlanUpdateProps) => {
  const [serviceConfigurationId, setServiceConfigurationId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { circuitVlanEntity, serviceConfigurations, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/circuit-vlan' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getServiceConfigurations();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...circuitVlanEntity,
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
          <h2 id="activePortApp.circuitVlan.home.createOrEditLabel">
            <Translate contentKey="activePortApp.circuitVlan.home.createOrEditLabel">Create or edit a CircuitVlan</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : circuitVlanEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="circuit-vlan-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="circuit-vlan-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="zoneLabel" for="circuit-vlan-zone">
                  <Translate contentKey="activePortApp.circuitVlan.zone">Zone</Translate>
                </Label>
                <AvField id="circuit-vlan-zone" type="text" name="zone" />
              </AvGroup>
              <AvGroup>
                <Label id="serviceKeyLabel" for="circuit-vlan-serviceKey">
                  <Translate contentKey="activePortApp.circuitVlan.serviceKey">Service Key</Translate>
                </Label>
                <AvField id="circuit-vlan-serviceKey" type="text" name="serviceKey" />
              </AvGroup>
              <AvGroup>
                <Label id="vlanIdLabel" for="circuit-vlan-vlanId">
                  <Translate contentKey="activePortApp.circuitVlan.vlanId">Vlan Id</Translate>
                </Label>
                <AvField id="circuit-vlan-vlanId" type="string" className="form-control" name="vlanId" />
              </AvGroup>
              <AvGroup>
                <Label id="rdLabel" for="circuit-vlan-rd">
                  <Translate contentKey="activePortApp.circuitVlan.rd">Rd</Translate>
                </Label>
                <AvField id="circuit-vlan-rd" type="text" name="rd" />
              </AvGroup>
              <AvGroup>
                <Label id="serviceIdLabel" for="circuit-vlan-serviceId">
                  <Translate contentKey="activePortApp.circuitVlan.serviceId">Service Id</Translate>
                </Label>
                <AvField id="circuit-vlan-serviceId" type="string" className="form-control" name="serviceId" />
              </AvGroup>
              <AvGroup>
                <Label id="tenantNameLabel" for="circuit-vlan-tenantName">
                  <Translate contentKey="activePortApp.circuitVlan.tenantName">Tenant Name</Translate>
                </Label>
                <AvField id="circuit-vlan-tenantName" type="text" name="tenantName" />
              </AvGroup>
              <AvGroup>
                <Label id="childServiceIdLabel" for="circuit-vlan-childServiceId">
                  <Translate contentKey="activePortApp.circuitVlan.childServiceId">Child Service Id</Translate>
                </Label>
                <AvField id="circuit-vlan-childServiceId" type="string" className="form-control" name="childServiceId" />
              </AvGroup>
              <AvGroup>
                <Label id="childNtuIdLabel" for="circuit-vlan-childNtuId">
                  <Translate contentKey="activePortApp.circuitVlan.childNtuId">Child Ntu Id</Translate>
                </Label>
                <AvField id="circuit-vlan-childNtuId" type="string" className="form-control" name="childNtuId" />
              </AvGroup>
              <AvGroup>
                <Label id="realmIpLabel" for="circuit-vlan-realmIp">
                  <Translate contentKey="activePortApp.circuitVlan.realmIp">Realm Ip</Translate>
                </Label>
                <AvField id="circuit-vlan-realmIp" type="text" name="realmIp" />
              </AvGroup>
              <AvGroup>
                <Label id="internetTypeLabel" for="circuit-vlan-internetType">
                  <Translate contentKey="activePortApp.circuitVlan.internetType">Internet Type</Translate>
                </Label>
                <AvInput
                  id="circuit-vlan-internetType"
                  type="select"
                  className="form-control"
                  name="internetType"
                  value={(!isNew && circuitVlanEntity.internetType) || 'LAYER2'}
                >
                  <option value="LAYER2">{translate('activePortApp.InternetTypeEnum.LAYER2')}</option>
                  <option value="STATIC_ADDRESS">{translate('activePortApp.InternetTypeEnum.STATIC_ADDRESS')}</option>
                  <option value="PPPOE">{translate('activePortApp.InternetTypeEnum.PPPOE')}</option>
                  <option value="DHCP">{translate('activePortApp.InternetTypeEnum.DHCP')}</option>
                  <option value="BGP">{translate('activePortApp.InternetTypeEnum.BGP')}</option>
                  <option value="XC">{translate('activePortApp.InternetTypeEnum.XC')}</option>
                  <option value="VXC">{translate('activePortApp.InternetTypeEnum.VXC')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="typeLabel" for="circuit-vlan-type">
                  <Translate contentKey="activePortApp.circuitVlan.type">Type</Translate>
                </Label>
                <AvInput
                  id="circuit-vlan-type"
                  type="select"
                  className="form-control"
                  name="type"
                  value={(!isNew && circuitVlanEntity.type) || 'AWS'}
                >
                  <option value="AWS">{translate('activePortApp.ConnectTypeEnum.AWS')}</option>
                  <option value="IX">{translate('activePortApp.ConnectTypeEnum.IX')}</option>
                  <option value="VXC">{translate('activePortApp.ConnectTypeEnum.VXC')}</option>
                  <option value="AZURE">{translate('activePortApp.ConnectTypeEnum.AZURE')}</option>
                  <option value="AZURE_PRIVATE">{translate('activePortApp.ConnectTypeEnum.AZURE_PRIVATE')}</option>
                  <option value="AZURE_PUBLIC">{translate('activePortApp.ConnectTypeEnum.AZURE_PUBLIC')}</option>
                  <option value="AZURE_MICROSOFT">{translate('activePortApp.ConnectTypeEnum.AZURE_MICROSOFT')}</option>
                  <option value="OFFICE">{translate('activePortApp.ConnectTypeEnum.OFFICE')}</option>
                  <option value="INTERNET">{translate('activePortApp.ConnectTypeEnum.INTERNET')}</option>
                  <option value="ZETTA_INTERNET">{translate('activePortApp.ConnectTypeEnum.ZETTA_INTERNET')}</option>
                  <option value="DNS_FILTER">{translate('activePortApp.ConnectTypeEnum.DNS_FILTER')}</option>
                  <option value="ZETTA_SERVICE">{translate('activePortApp.ConnectTypeEnum.ZETTA_SERVICE')}</option>
                  <option value="INTERNET_LAYER3">{translate('activePortApp.ConnectTypeEnum.INTERNET_LAYER3')}</option>
                  <option value="MCON">{translate('activePortApp.ConnectTypeEnum.MCON')}</option>
                  <option value="ALIBABA">{translate('activePortApp.ConnectTypeEnum.ALIBABA')}</option>
                  <option value="IBM">{translate('activePortApp.ConnectTypeEnum.IBM')}</option>
                  <option value="GOOGLE">{translate('activePortApp.ConnectTypeEnum.GOOGLE')}</option>
                  <option value="ORACLE">{translate('activePortApp.ConnectTypeEnum.ORACLE')}</option>
                  <option value="XC">{translate('activePortApp.ConnectTypeEnum.XC')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="circuit-vlan-serviceConfiguration">
                  <Translate contentKey="activePortApp.circuitVlan.serviceConfiguration">Service Configuration</Translate>
                </Label>
                <AvInput id="circuit-vlan-serviceConfiguration" type="select" className="form-control" name="serviceConfigurationId">
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
              <Button tag={Link} id="cancel-save" to="/circuit-vlan" replace color="info">
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
  circuitVlanEntity: storeState.circuitVlan.entity,
  loading: storeState.circuitVlan.loading,
  updating: storeState.circuitVlan.updating,
  updateSuccess: storeState.circuitVlan.updateSuccess,
});

const mapDispatchToProps = {
  getServiceConfigurations,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CircuitVlanUpdate);
