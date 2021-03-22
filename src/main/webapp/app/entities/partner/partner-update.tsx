import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICentralSwitch } from 'app/shared/model/central-switch.model';
import { getEntities as getCentralSwitches } from 'app/entities/central-switch/central-switch.reducer';
import { IProviderConfiguration } from 'app/shared/model/provider-configuration.model';
import { getEntities as getProviderConfigurations } from 'app/entities/provider-configuration/provider-configuration.reducer';
import { getEntity, updateEntity, createEntity, reset } from './partner.reducer';
import { IPartner } from 'app/shared/model/partner.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPartnerUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PartnerUpdate = (props: IPartnerUpdateProps) => {
  const [centralSwitchId, setCentralSwitchId] = useState('0');
  const [providerCodeId, setProviderCodeId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { partnerEntity, centralSwitches, providerConfigurations, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/partner' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCentralSwitches();
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
        ...partnerEntity,
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
          <h2 id="activePortApp.partner.home.createOrEditLabel">
            <Translate contentKey="activePortApp.partner.home.createOrEditLabel">Create or edit a Partner</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : partnerEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="partner-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="partner-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="partner-name">
                  <Translate contentKey="activePortApp.partner.name">Name</Translate>
                </Label>
                <AvField
                  id="partner-name"
                  type="text"
                  name="name"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    minLength: { value: 3, errorMessage: translate('entity.validation.minlength', { min: 3 }) },
                    maxLength: { value: 30, errorMessage: translate('entity.validation.maxlength', { max: 30 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="emailLabel" for="partner-email">
                  <Translate contentKey="activePortApp.partner.email">Email</Translate>
                </Label>
                <AvField id="partner-email" type="text" name="email" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="partner-description">
                  <Translate contentKey="activePortApp.partner.description">Description</Translate>
                </Label>
                <AvField id="partner-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="typeLabel" for="partner-type">
                  <Translate contentKey="activePortApp.partner.type">Type</Translate>
                </Label>
                <AvInput
                  id="partner-type"
                  type="select"
                  className="form-control"
                  name="type"
                  value={(!isNew && partnerEntity.type) || 'MEGAPORT'}
                >
                  <option value="MEGAPORT">{translate('activePortApp.PartnerTypeEnum.MEGAPORT')}</option>
                  <option value="ISP">{translate('activePortApp.PartnerTypeEnum.ISP')}</option>
                  <option value="ZETTA">{translate('activePortApp.PartnerTypeEnum.ZETTA')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="connectionLabel" for="partner-connection">
                  <Translate contentKey="activePortApp.partner.connection">Connection</Translate>
                </Label>
                <AvInput
                  id="partner-connection"
                  type="select"
                  className="form-control"
                  name="connection"
                  value={(!isNew && partnerEntity.connection) || 'LAYER2'}
                >
                  <option value="LAYER2">{translate('activePortApp.ConnetionTypeEnum.LAYER2')}</option>
                  <option value="LAYER3">{translate('activePortApp.ConnetionTypeEnum.LAYER3')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="portTypeLabel" for="partner-portType">
                  <Translate contentKey="activePortApp.partner.portType">Port Type</Translate>
                </Label>
                <AvInput
                  id="partner-portType"
                  type="select"
                  className="form-control"
                  name="portType"
                  value={(!isNew && partnerEntity.portType) || 'PUBLIC'}
                >
                  <option value="PUBLIC">{translate('activePortApp.AwsTypeEnum.PUBLIC')}</option>
                  <option value="PRIVATE">{translate('activePortApp.AwsTypeEnum.PRIVATE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="portLabel" for="partner-port">
                  <Translate contentKey="activePortApp.partner.port">Port</Translate>
                </Label>
                <AvField id="partner-port" type="text" name="port" />
              </AvGroup>
              <AvGroup>
                <Label id="marketLabel" for="partner-market">
                  <Translate contentKey="activePortApp.partner.market">Market</Translate>
                </Label>
                <AvField id="partner-market" type="text" name="market" />
              </AvGroup>
              <AvGroup>
                <Label id="locationIdLabel" for="partner-locationId">
                  <Translate contentKey="activePortApp.partner.locationId">Location Id</Translate>
                </Label>
                <AvField id="partner-locationId" type="string" className="form-control" name="locationId" />
              </AvGroup>
              <AvGroup check>
                <Label id="vxcpermittedLabel">
                  <AvInput id="partner-vxcpermitted" type="checkbox" className="form-check-input" name="vxcpermitted" />
                  <Translate contentKey="activePortApp.partner.vxcpermitted">Vxcpermitted</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="locationIxLabel" for="partner-locationIx">
                  <Translate contentKey="activePortApp.partner.locationIx">Location Ix</Translate>
                </Label>
                <AvField id="partner-locationIx" type="text" name="locationIx" />
              </AvGroup>
              <AvGroup>
                <Label id="vlanPortLabel" for="partner-vlanPort">
                  <Translate contentKey="activePortApp.partner.vlanPort">Vlan Port</Translate>
                </Label>
                <AvField id="partner-vlanPort" type="text" name="vlanPort" />
              </AvGroup>
              <AvGroup>
                <Label for="partner-centralSwitch">
                  <Translate contentKey="activePortApp.partner.centralSwitch">Central Switch</Translate>
                </Label>
                <AvInput id="partner-centralSwitch" type="select" className="form-control" name="centralSwitchId">
                  <option value="" key="0" />
                  {centralSwitches
                    ? centralSwitches.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="partner-providerCode">
                  <Translate contentKey="activePortApp.partner.providerCode">Provider Code</Translate>
                </Label>
                <AvInput id="partner-providerCode" type="select" className="form-control" name="providerCodeId">
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
              <Button tag={Link} id="cancel-save" to="/partner" replace color="info">
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
  centralSwitches: storeState.centralSwitch.entities,
  providerConfigurations: storeState.providerConfiguration.entities,
  partnerEntity: storeState.partner.entity,
  loading: storeState.partner.loading,
  updating: storeState.partner.updating,
  updateSuccess: storeState.partner.updateSuccess,
});

const mapDispatchToProps = {
  getCentralSwitches,
  getProviderConfigurations,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PartnerUpdate);
