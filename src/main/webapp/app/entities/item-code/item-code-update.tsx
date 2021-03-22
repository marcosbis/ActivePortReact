import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './item-code.reducer';
import { IItemCode } from 'app/shared/model/item-code.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IItemCodeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ItemCodeUpdate = (props: IItemCodeUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { itemCodeEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/item-code' + props.location.search);
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
        ...itemCodeEntity,
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
          <h2 id="activePortApp.itemCode.home.createOrEditLabel">
            <Translate contentKey="activePortApp.itemCode.home.createOrEditLabel">Create or edit a ItemCode</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : itemCodeEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="item-code-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="item-code-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="item-code-name">
                  <Translate contentKey="activePortApp.itemCode.name">Name</Translate>
                </Label>
                <AvField id="item-code-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="codeLabel" for="item-code-code">
                  <Translate contentKey="activePortApp.itemCode.code">Code</Translate>
                </Label>
                <AvField id="item-code-code" type="text" name="code" />
              </AvGroup>
              <AvGroup>
                <Label id="codeRateLabel" for="item-code-codeRate">
                  <Translate contentKey="activePortApp.itemCode.codeRate">Code Rate</Translate>
                </Label>
                <AvField id="item-code-codeRate" type="text" name="codeRate" />
              </AvGroup>
              <AvGroup>
                <Label id="codeActivationLabel" for="item-code-codeActivation">
                  <Translate contentKey="activePortApp.itemCode.codeActivation">Code Activation</Translate>
                </Label>
                <AvField id="item-code-codeActivation" type="text" name="codeActivation" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="item-code-description">
                  <Translate contentKey="activePortApp.itemCode.description">Description</Translate>
                </Label>
                <AvField id="item-code-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="localPriceRateLabel" for="item-code-localPriceRate">
                  <Translate contentKey="activePortApp.itemCode.localPriceRate">Local Price Rate</Translate>
                </Label>
                <AvField id="item-code-localPriceRate" type="string" className="form-control" name="localPriceRate" />
              </AvGroup>
              <AvGroup>
                <Label id="localPriceMontlhyLabel" for="item-code-localPriceMontlhy">
                  <Translate contentKey="activePortApp.itemCode.localPriceMontlhy">Local Price Montlhy</Translate>
                </Label>
                <AvField id="item-code-localPriceMontlhy" type="string" className="form-control" name="localPriceMontlhy" />
              </AvGroup>
              <AvGroup>
                <Label id="localPriceActivationLabel" for="item-code-localPriceActivation">
                  <Translate contentKey="activePortApp.itemCode.localPriceActivation">Local Price Activation</Translate>
                </Label>
                <AvField id="item-code-localPriceActivation" type="string" className="form-control" name="localPriceActivation" />
              </AvGroup>
              <AvGroup>
                <Label id="codeUpLiftLabel" for="item-code-codeUpLift">
                  <Translate contentKey="activePortApp.itemCode.codeUpLift">Code Up Lift</Translate>
                </Label>
                <AvField id="item-code-codeUpLift" type="text" name="codeUpLift" />
              </AvGroup>
              <AvGroup>
                <Label id="upLiftLabel" for="item-code-upLift">
                  <Translate contentKey="activePortApp.itemCode.upLift">Up Lift</Translate>
                </Label>
                <AvField id="item-code-upLift" type="string" className="form-control" name="upLift" />
              </AvGroup>
              <AvGroup check>
                <Label id="useUpLiftLabel">
                  <AvInput id="item-code-useUpLift" type="checkbox" className="form-check-input" name="useUpLift" />
                  <Translate contentKey="activePortApp.itemCode.useUpLift">Use Up Lift</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="useLocalPriceLabel">
                  <AvInput id="item-code-useLocalPrice" type="checkbox" className="form-check-input" name="useLocalPrice" />
                  <Translate contentKey="activePortApp.itemCode.useLocalPrice">Use Local Price</Translate>
                </Label>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/item-code" replace color="info">
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
  itemCodeEntity: storeState.itemCode.entity,
  loading: storeState.itemCode.loading,
  updating: storeState.itemCode.updating,
  updateSuccess: storeState.itemCode.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ItemCodeUpdate);
