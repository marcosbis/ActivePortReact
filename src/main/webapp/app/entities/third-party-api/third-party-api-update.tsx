import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './third-party-api.reducer';
import { IThirdPartyApi } from 'app/shared/model/third-party-api.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IThirdPartyApiUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ThirdPartyApiUpdate = (props: IThirdPartyApiUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { thirdPartyApiEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/third-party-api' + props.location.search);
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
        ...thirdPartyApiEntity,
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
          <h2 id="activePortApp.thirdPartyApi.home.createOrEditLabel">
            <Translate contentKey="activePortApp.thirdPartyApi.home.createOrEditLabel">Create or edit a ThirdPartyApi</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : thirdPartyApiEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="third-party-api-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="third-party-api-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="third-party-api-name">
                  <Translate contentKey="activePortApp.thirdPartyApi.name">Name</Translate>
                </Label>
                <AvField
                  id="third-party-api-name"
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
                <Label id="descriptionLabel" for="third-party-api-description">
                  <Translate contentKey="activePortApp.thirdPartyApi.description">Description</Translate>
                </Label>
                <AvField id="third-party-api-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="apiLabel" for="third-party-api-api">
                  <Translate contentKey="activePortApp.thirdPartyApi.api">Api</Translate>
                </Label>
                <AvInput
                  id="third-party-api-api"
                  type="select"
                  className="form-control"
                  name="api"
                  value={(!isNew && thirdPartyApiEntity.api) || 'MEGAPORT'}
                >
                  <option value="MEGAPORT">{translate('activePortApp.ApiType.MEGAPORT')}</option>
                  <option value="XERO">{translate('activePortApp.ApiType.XERO')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="stageLabel" for="third-party-api-stage">
                  <Translate contentKey="activePortApp.thirdPartyApi.stage">Stage</Translate>
                </Label>
                <AvField id="third-party-api-stage" type="text" name="stage" />
              </AvGroup>
              <AvGroup>
                <Label id="usernameLabel" for="third-party-api-username">
                  <Translate contentKey="activePortApp.thirdPartyApi.username">Username</Translate>
                </Label>
                <AvField id="third-party-api-username" type="text" name="username" />
              </AvGroup>
              <AvGroup>
                <Label id="secretLabel" for="third-party-api-secret">
                  <Translate contentKey="activePortApp.thirdPartyApi.secret">Secret</Translate>
                </Label>
                <AvField id="third-party-api-secret" type="text" name="secret" />
              </AvGroup>
              <AvGroup>
                <Label id="privateKeyCertLabel" for="third-party-api-privateKeyCert">
                  <Translate contentKey="activePortApp.thirdPartyApi.privateKeyCert">Private Key Cert</Translate>
                </Label>
                <AvField id="third-party-api-privateKeyCert" type="text" name="privateKeyCert" />
              </AvGroup>
              <AvGroup>
                <Label id="privateKeyPasswordLabel" for="third-party-api-privateKeyPassword">
                  <Translate contentKey="activePortApp.thirdPartyApi.privateKeyPassword">Private Key Password</Translate>
                </Label>
                <AvField id="third-party-api-privateKeyPassword" type="text" name="privateKeyPassword" />
              </AvGroup>
              <AvGroup>
                <Label id="billingUidLabel" for="third-party-api-billingUid">
                  <Translate contentKey="activePortApp.thirdPartyApi.billingUid">Billing Uid</Translate>
                </Label>
                <AvField id="third-party-api-billingUid" type="text" name="billingUid" />
              </AvGroup>
              <AvGroup>
                <Label id="productUidLabel" for="third-party-api-productUid">
                  <Translate contentKey="activePortApp.thirdPartyApi.productUid">Product Uid</Translate>
                </Label>
                <AvField id="third-party-api-productUid" type="text" name="productUid" />
              </AvGroup>
              <AvGroup>
                <Label id="endpointLabel" for="third-party-api-endpoint">
                  <Translate contentKey="activePortApp.thirdPartyApi.endpoint">Endpoint</Translate>
                </Label>
                <AvField id="third-party-api-endpoint" type="text" name="endpoint" />
              </AvGroup>
              <AvGroup>
                <Label id="allowSharedPortsUidLabel" for="third-party-api-allowSharedPortsUid">
                  <Translate contentKey="activePortApp.thirdPartyApi.allowSharedPortsUid">Allow Shared Ports Uid</Translate>
                </Label>
                <AvField id="third-party-api-allowSharedPortsUid" type="text" name="allowSharedPortsUid" />
              </AvGroup>
              <AvGroup>
                <Label id="connectionTypeLabel" for="third-party-api-connectionType">
                  <Translate contentKey="activePortApp.thirdPartyApi.connectionType">Connection Type</Translate>
                </Label>
                <AvInput
                  id="third-party-api-connectionType"
                  type="select"
                  className="form-control"
                  name="connectionType"
                  value={(!isNew && thirdPartyApiEntity.connectionType) || 'SHARED'}
                >
                  <option value="SHARED">{translate('activePortApp.PortApiTypeEnum.SHARED')}</option>
                  <option value="PRIVATE">{translate('activePortApp.PortApiTypeEnum.PRIVATE')}</option>
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/third-party-api" replace color="info">
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
  thirdPartyApiEntity: storeState.thirdPartyApi.entity,
  loading: storeState.thirdPartyApi.loading,
  updating: storeState.thirdPartyApi.updating,
  updateSuccess: storeState.thirdPartyApi.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ThirdPartyApiUpdate);
