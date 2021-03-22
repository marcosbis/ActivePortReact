import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './ntu-config.reducer';
import { INtuConfig } from 'app/shared/model/ntu-config.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface INtuConfigUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NtuConfigUpdate = (props: INtuConfigUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { ntuConfigEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/ntu-config' + props.location.search);
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
        ...ntuConfigEntity,
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
          <h2 id="activePortApp.ntuConfig.home.createOrEditLabel">
            <Translate contentKey="activePortApp.ntuConfig.home.createOrEditLabel">Create or edit a NtuConfig</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : ntuConfigEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="ntu-config-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="ntu-config-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="serialNumberLabel" for="ntu-config-serialNumber">
                  <Translate contentKey="activePortApp.ntuConfig.serialNumber">Serial Number</Translate>
                </Label>
                <AvField id="ntu-config-serialNumber" type="text" name="serialNumber" />
              </AvGroup>
              <AvGroup>
                <Label id="nameLabel" for="ntu-config-name">
                  <Translate contentKey="activePortApp.ntuConfig.name">Name</Translate>
                </Label>
                <AvField id="ntu-config-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="firmwareVersionLabel" for="ntu-config-firmwareVersion">
                  <Translate contentKey="activePortApp.ntuConfig.firmwareVersion">Firmware Version</Translate>
                </Label>
                <AvField id="ntu-config-firmwareVersion" type="text" name="firmwareVersion" />
              </AvGroup>
              <AvGroup>
                <Label id="ntuIdLabel" for="ntu-config-ntuId">
                  <Translate contentKey="activePortApp.ntuConfig.ntuId">Ntu Id</Translate>
                </Label>
                <AvField id="ntu-config-ntuId" type="string" className="form-control" name="ntuId" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/ntu-config" replace color="info">
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
  ntuConfigEntity: storeState.ntuConfig.entity,
  loading: storeState.ntuConfig.loading,
  updating: storeState.ntuConfig.updating,
  updateSuccess: storeState.ntuConfig.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NtuConfigUpdate);
