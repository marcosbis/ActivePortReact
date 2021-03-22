import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IThirdPartyApi } from 'app/shared/model/third-party-api.model';
import { getEntities as getThirdPartyApis } from 'app/entities/third-party-api/third-party-api.reducer';
import { getEntity, updateEntity, createEntity, reset } from './provider-port.reducer';
import { IProviderPort } from 'app/shared/model/provider-port.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IProviderPortUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProviderPortUpdate = (props: IProviderPortUpdateProps) => {
  const [thirdPartyApiId, setThirdPartyApiId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { providerPortEntity, thirdPartyApis, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/provider-port' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getThirdPartyApis();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...providerPortEntity,
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
          <h2 id="activePortApp.providerPort.home.createOrEditLabel">
            <Translate contentKey="activePortApp.providerPort.home.createOrEditLabel">Create or edit a ProviderPort</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : providerPortEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="provider-port-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="provider-port-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="provider-port-name">
                  <Translate contentKey="activePortApp.providerPort.name">Name</Translate>
                </Label>
                <AvField
                  id="provider-port-name"
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
                <Label id="uidLabel" for="provider-port-uid">
                  <Translate contentKey="activePortApp.providerPort.uid">Uid</Translate>
                </Label>
                <AvField id="provider-port-uid" type="text" name="uid" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="provider-port-description">
                  <Translate contentKey="activePortApp.providerPort.description">Description</Translate>
                </Label>
                <AvField id="provider-port-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="typeLabel" for="provider-port-type">
                  <Translate contentKey="activePortApp.providerPort.type">Type</Translate>
                </Label>
                <AvInput
                  id="provider-port-type"
                  type="select"
                  className="form-control"
                  name="type"
                  value={(!isNew && providerPortEntity.type) || 'MEGAPORT'}
                >
                  <option value="MEGAPORT">{translate('activePortApp.PartnerTypeEnum.MEGAPORT')}</option>
                  <option value="ISP">{translate('activePortApp.PartnerTypeEnum.ISP')}</option>
                  <option value="ZETTA">{translate('activePortApp.PartnerTypeEnum.ZETTA')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="connectionLabel" for="provider-port-connection">
                  <Translate contentKey="activePortApp.providerPort.connection">Connection</Translate>
                </Label>
                <AvInput
                  id="provider-port-connection"
                  type="select"
                  className="form-control"
                  name="connection"
                  value={(!isNew && providerPortEntity.connection) || 'LAYER2'}
                >
                  <option value="LAYER2">{translate('activePortApp.ConnetionTypeEnum.LAYER2')}</option>
                  <option value="LAYER3">{translate('activePortApp.ConnetionTypeEnum.LAYER3')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="portTypeLabel" for="provider-port-portType">
                  <Translate contentKey="activePortApp.providerPort.portType">Port Type</Translate>
                </Label>
                <AvInput
                  id="provider-port-portType"
                  type="select"
                  className="form-control"
                  name="portType"
                  value={(!isNew && providerPortEntity.portType) || 'PUBLIC'}
                >
                  <option value="PUBLIC">{translate('activePortApp.AwsTypeEnum.PUBLIC')}</option>
                  <option value="PRIVATE">{translate('activePortApp.AwsTypeEnum.PRIVATE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="portIdLabel" for="provider-port-portId">
                  <Translate contentKey="activePortApp.providerPort.portId">Port Id</Translate>
                </Label>
                <AvField id="provider-port-portId" type="text" name="portId" />
              </AvGroup>
              <AvGroup>
                <Label id="marketLabel" for="provider-port-market">
                  <Translate contentKey="activePortApp.providerPort.market">Market</Translate>
                </Label>
                <AvField id="provider-port-market" type="text" name="market" />
              </AvGroup>
              <AvGroup>
                <Label id="locationIdLabel" for="provider-port-locationId">
                  <Translate contentKey="activePortApp.providerPort.locationId">Location Id</Translate>
                </Label>
                <AvField id="provider-port-locationId" type="string" className="form-control" name="locationId" />
              </AvGroup>
              <AvGroup>
                <Label for="provider-port-thirdPartyApi">
                  <Translate contentKey="activePortApp.providerPort.thirdPartyApi">Third Party Api</Translate>
                </Label>
                <AvInput id="provider-port-thirdPartyApi" type="select" className="form-control" name="thirdPartyApiId">
                  <option value="" key="0" />
                  {thirdPartyApis
                    ? thirdPartyApis.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/provider-port" replace color="info">
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
  thirdPartyApis: storeState.thirdPartyApi.entities,
  providerPortEntity: storeState.providerPort.entity,
  loading: storeState.providerPort.loading,
  updating: storeState.providerPort.updating,
  updateSuccess: storeState.providerPort.updateSuccess,
});

const mapDispatchToProps = {
  getThirdPartyApis,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProviderPortUpdate);
