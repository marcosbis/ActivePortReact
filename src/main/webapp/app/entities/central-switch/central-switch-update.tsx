import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ILocation } from 'app/shared/model/location.model';
import { getEntities as getLocations } from 'app/entities/location/location.reducer';
import { INtuType } from 'app/shared/model/ntu-type.model';
import { getEntities as getNtuTypes } from 'app/entities/ntu-type/ntu-type.reducer';
import { getEntity, updateEntity, createEntity, reset } from './central-switch.reducer';
import { ICentralSwitch } from 'app/shared/model/central-switch.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICentralSwitchUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CentralSwitchUpdate = (props: ICentralSwitchUpdateProps) => {
  const [locationId, setLocationId] = useState('0');
  const [ntutypeId, setNtutypeId] = useState('0');
  const [ntutypeId, setNtutypeId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { centralSwitchEntity, locations, ntuTypes, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/central-switch' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getLocations();
    props.getNtuTypes();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...centralSwitchEntity,
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
          <h2 id="activePortApp.centralSwitch.home.createOrEditLabel">
            <Translate contentKey="activePortApp.centralSwitch.home.createOrEditLabel">Create or edit a CentralSwitch</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : centralSwitchEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="central-switch-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="central-switch-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="central-switch-name">
                  <Translate contentKey="activePortApp.centralSwitch.name">Name</Translate>
                </Label>
                <AvField id="central-switch-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="hostIdLabel" for="central-switch-hostId">
                  <Translate contentKey="activePortApp.centralSwitch.hostId">Host Id</Translate>
                </Label>
                <AvField id="central-switch-hostId" type="text" name="hostId" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="central-switch-description">
                  <Translate contentKey="activePortApp.centralSwitch.description">Description</Translate>
                </Label>
                <AvField id="central-switch-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="serialNumberLabel" for="central-switch-serialNumber">
                  <Translate contentKey="activePortApp.centralSwitch.serialNumber">Serial Number</Translate>
                </Label>
                <AvField id="central-switch-serialNumber" type="text" name="serialNumber" />
              </AvGroup>
              <AvGroup>
                <Label id="ipAddressLabel" for="central-switch-ipAddress">
                  <Translate contentKey="activePortApp.centralSwitch.ipAddress">Ip Address</Translate>
                </Label>
                <AvField id="central-switch-ipAddress" type="text" name="ipAddress" />
              </AvGroup>
              <AvGroup>
                <Label id="companyNameLabel" for="central-switch-companyName">
                  <Translate contentKey="activePortApp.centralSwitch.companyName">Company Name</Translate>
                </Label>
                <AvField id="central-switch-companyName" type="text" name="companyName" />
              </AvGroup>
              <AvGroup>
                <Label id="hostNameLabel" for="central-switch-hostName">
                  <Translate contentKey="activePortApp.centralSwitch.hostName">Host Name</Translate>
                </Label>
                <AvField id="central-switch-hostName" type="text" name="hostName" />
              </AvGroup>
              <AvGroup check>
                <Label id="configBackupLabel">
                  <AvInput id="central-switch-configBackup" type="checkbox" className="form-check-input" name="configBackup" />
                  <Translate contentKey="activePortApp.centralSwitch.configBackup">Config Backup</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="poolVlanStartLabel" for="central-switch-poolVlanStart">
                  <Translate contentKey="activePortApp.centralSwitch.poolVlanStart">Pool Vlan Start</Translate>
                </Label>
                <AvField id="central-switch-poolVlanStart" type="string" className="form-control" name="poolVlanStart" />
              </AvGroup>
              <AvGroup>
                <Label id="poolVlanEndLabel" for="central-switch-poolVlanEnd">
                  <Translate contentKey="activePortApp.centralSwitch.poolVlanEnd">Pool Vlan End</Translate>
                </Label>
                <AvField id="central-switch-poolVlanEnd" type="string" className="form-control" name="poolVlanEnd" />
              </AvGroup>
              <AvGroup>
                <Label id="endpointLabel" for="central-switch-endpoint">
                  <Translate contentKey="activePortApp.centralSwitch.endpoint">Endpoint</Translate>
                </Label>
                <AvField id="central-switch-endpoint" type="text" name="endpoint" />
              </AvGroup>
              <AvGroup>
                <Label id="restUsernameLabel" for="central-switch-restUsername">
                  <Translate contentKey="activePortApp.centralSwitch.restUsername">Rest Username</Translate>
                </Label>
                <AvField id="central-switch-restUsername" type="text" name="restUsername" />
              </AvGroup>
              <AvGroup>
                <Label id="restPasswordLabel" for="central-switch-restPassword">
                  <Translate contentKey="activePortApp.centralSwitch.restPassword">Rest Password</Translate>
                </Label>
                <AvField id="central-switch-restPassword" type="text" name="restPassword" />
              </AvGroup>
              <AvGroup check>
                <Label id="restEnabledLabel">
                  <AvInput id="central-switch-restEnabled" type="checkbox" className="form-check-input" name="restEnabled" />
                  <Translate contentKey="activePortApp.centralSwitch.restEnabled">Rest Enabled</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="autoRollbackLabel">
                  <AvInput id="central-switch-autoRollback" type="checkbox" className="form-check-input" name="autoRollback" />
                  <Translate contentKey="activePortApp.centralSwitch.autoRollback">Auto Rollback</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="feignLabel">
                  <AvInput id="central-switch-feign" type="checkbox" className="form-check-input" name="feign" />
                  <Translate contentKey="activePortApp.centralSwitch.feign">Feign</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="central-switch-location">
                  <Translate contentKey="activePortApp.centralSwitch.location">Location</Translate>
                </Label>
                <AvInput id="central-switch-location" type="select" className="form-control" name="locationId">
                  <option value="" key="0" />
                  {locations
                    ? locations.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.code}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="central-switch-ntutype">
                  <Translate contentKey="activePortApp.centralSwitch.ntutype">Ntutype</Translate>
                </Label>
                <AvInput id="central-switch-ntutype" type="select" className="form-control" name="ntutypeId">
                  <option value="" key="0" />
                  {ntuTypes
                    ? ntuTypes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.model}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="central-switch-ntutype">
                  <Translate contentKey="activePortApp.centralSwitch.ntutype">Ntutype</Translate>
                </Label>
                <AvInput id="central-switch-ntutype" type="select" className="form-control" name="ntutypeId">
                  <option value="" key="0" />
                  {ntuTypes
                    ? ntuTypes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.model}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/central-switch" replace color="info">
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
  locations: storeState.location.entities,
  ntuTypes: storeState.ntuType.entities,
  centralSwitchEntity: storeState.centralSwitch.entity,
  loading: storeState.centralSwitch.loading,
  updating: storeState.centralSwitch.updating,
  updateSuccess: storeState.centralSwitch.updateSuccess,
});

const mapDispatchToProps = {
  getLocations,
  getNtuTypes,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CentralSwitchUpdate);
