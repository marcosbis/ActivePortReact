import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './billing-system.reducer';
import { IBillingSystem } from 'app/shared/model/billing-system.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBillingSystemUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BillingSystemUpdate = (props: IBillingSystemUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { billingSystemEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/billing-system' + props.location.search);
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
    values.startBilling = convertDateTimeToServer(values.startBilling);

    if (errors.length === 0) {
      const entity = {
        ...billingSystemEntity,
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
          <h2 id="activePortApp.billingSystem.home.createOrEditLabel">
            <Translate contentKey="activePortApp.billingSystem.home.createOrEditLabel">Create or edit a BillingSystem</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : billingSystemEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="billing-system-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="billing-system-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="billing-system-name">
                  <Translate contentKey="activePortApp.billingSystem.name">Name</Translate>
                </Label>
                <AvField
                  id="billing-system-name"
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
                <Label id="descriptionLabel" for="billing-system-description">
                  <Translate contentKey="activePortApp.billingSystem.description">Description</Translate>
                </Label>
                <AvField id="billing-system-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="apiLabel" for="billing-system-api">
                  <Translate contentKey="activePortApp.billingSystem.api">Api</Translate>
                </Label>
                <AvInput
                  id="billing-system-api"
                  type="select"
                  className="form-control"
                  name="api"
                  value={(!isNew && billingSystemEntity.api) || 'XERO'}
                >
                  <option value="XERO">{translate('activePortApp.BillingTypeEnum.XERO')}</option>
                  <option value="HOSTBILL">{translate('activePortApp.BillingTypeEnum.HOSTBILL')}</option>
                  <option value="LOCAL">{translate('activePortApp.BillingTypeEnum.LOCAL')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="stageLabel" for="billing-system-stage">
                  <Translate contentKey="activePortApp.billingSystem.stage">Stage</Translate>
                </Label>
                <AvField id="billing-system-stage" type="text" name="stage" />
              </AvGroup>
              <AvGroup>
                <Label id="usernameLabel" for="billing-system-username">
                  <Translate contentKey="activePortApp.billingSystem.username">Username</Translate>
                </Label>
                <AvField id="billing-system-username" type="text" name="username" />
              </AvGroup>
              <AvGroup>
                <Label id="secretLabel" for="billing-system-secret">
                  <Translate contentKey="activePortApp.billingSystem.secret">Secret</Translate>
                </Label>
                <AvField id="billing-system-secret" type="text" name="secret" />
              </AvGroup>
              <AvGroup>
                <Label id="privateKeyCertLabel" for="billing-system-privateKeyCert">
                  <Translate contentKey="activePortApp.billingSystem.privateKeyCert">Private Key Cert</Translate>
                </Label>
                <AvField id="billing-system-privateKeyCert" type="text" name="privateKeyCert" />
              </AvGroup>
              <AvGroup>
                <Label id="privateKeyPasswordLabel" for="billing-system-privateKeyPassword">
                  <Translate contentKey="activePortApp.billingSystem.privateKeyPassword">Private Key Password</Translate>
                </Label>
                <AvField id="billing-system-privateKeyPassword" type="text" name="privateKeyPassword" />
              </AvGroup>
              <AvGroup>
                <Label id="billingUidLabel" for="billing-system-billingUid">
                  <Translate contentKey="activePortApp.billingSystem.billingUid">Billing Uid</Translate>
                </Label>
                <AvField id="billing-system-billingUid" type="text" name="billingUid" />
              </AvGroup>
              <AvGroup>
                <Label id="startBillingLabel" for="billing-system-startBilling">
                  <Translate contentKey="activePortApp.billingSystem.startBilling">Start Billing</Translate>
                </Label>
                <AvInput
                  id="billing-system-startBilling"
                  type="datetime-local"
                  className="form-control"
                  name="startBilling"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.billingSystemEntity.startBilling)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="currencyCodeLabel" for="billing-system-currencyCode">
                  <Translate contentKey="activePortApp.billingSystem.currencyCode">Currency Code</Translate>
                </Label>
                <AvField id="billing-system-currencyCode" type="text" name="currencyCode" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/billing-system" replace color="info">
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
  billingSystemEntity: storeState.billingSystem.entity,
  loading: storeState.billingSystem.loading,
  updating: storeState.billingSystem.updating,
  updateSuccess: storeState.billingSystem.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BillingSystemUpdate);
