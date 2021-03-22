import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './address-allocation.reducer';
import { IAddressAllocation } from 'app/shared/model/address-allocation.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAddressAllocationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AddressAllocationUpdate = (props: IAddressAllocationUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { addressAllocationEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/address-allocation' + props.location.search);
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
        ...addressAllocationEntity,
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
          <h2 id="activePortApp.addressAllocation.home.createOrEditLabel">
            <Translate contentKey="activePortApp.addressAllocation.home.createOrEditLabel">Create or edit a AddressAllocation</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : addressAllocationEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="address-allocation-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="address-allocation-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="subnetLabel" for="address-allocation-subnet">
                  <Translate contentKey="activePortApp.addressAllocation.subnet">Subnet</Translate>
                </Label>
                <AvField id="address-allocation-subnet" type="text" name="subnet" />
              </AvGroup>
              <AvGroup>
                <Label id="deviceNameLabel" for="address-allocation-deviceName">
                  <Translate contentKey="activePortApp.addressAllocation.deviceName">Device Name</Translate>
                </Label>
                <AvField id="address-allocation-deviceName" type="text" name="deviceName" />
              </AvGroup>
              <AvGroup>
                <Label id="deviceModeLabel" for="address-allocation-deviceMode">
                  <Translate contentKey="activePortApp.addressAllocation.deviceMode">Device Mode</Translate>
                </Label>
                <AvInput
                  id="address-allocation-deviceMode"
                  type="select"
                  className="form-control"
                  name="deviceMode"
                  value={(!isNew && addressAllocationEntity.deviceMode) || 'DEMO'}
                >
                  <option value="DEMO">{translate('activePortApp.NtuModeEnum.DEMO')}</option>
                  <option value="EDGE">{translate('activePortApp.NtuModeEnum.EDGE')}</option>
                  <option value="CLOUD">{translate('activePortApp.NtuModeEnum.CLOUD')}</option>
                  <option value="MANAGED">{translate('activePortApp.NtuModeEnum.MANAGED')}</option>
                  <option value="BRIDGE">{translate('activePortApp.NtuModeEnum.BRIDGE')}</option>
                  <option value="SD_WAN">{translate('activePortApp.NtuModeEnum.SD_WAN')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="deviceIdLabel" for="address-allocation-deviceId">
                  <Translate contentKey="activePortApp.addressAllocation.deviceId">Device Id</Translate>
                </Label>
                <AvField id="address-allocation-deviceId" type="string" className="form-control" name="deviceId" />
              </AvGroup>
              <AvGroup>
                <Label id="deviceTypeLabel" for="address-allocation-deviceType">
                  <Translate contentKey="activePortApp.addressAllocation.deviceType">Device Type</Translate>
                </Label>
                <AvInput
                  id="address-allocation-deviceType"
                  type="select"
                  className="form-control"
                  name="deviceType"
                  value={(!isNew && addressAllocationEntity.deviceType) || 'EDGE'}
                >
                  <option value="EDGE">{translate('activePortApp.PortServiceTypeEnum.EDGE')}</option>
                  <option value="SWITCH">{translate('activePortApp.PortServiceTypeEnum.SWITCH')}</option>
                  <option value="FIREWALL">{translate('activePortApp.PortServiceTypeEnum.FIREWALL')}</option>
                  <option value="NTU">{translate('activePortApp.PortServiceTypeEnum.NTU')}</option>
                  <option value="PE">{translate('activePortApp.PortServiceTypeEnum.PE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="address-allocation-description">
                  <Translate contentKey="activePortApp.addressAllocation.description">Description</Translate>
                </Label>
                <AvField id="address-allocation-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="allocationTypeLabel" for="address-allocation-allocationType">
                  <Translate contentKey="activePortApp.addressAllocation.allocationType">Allocation Type</Translate>
                </Label>
                <AvInput
                  id="address-allocation-allocationType"
                  type="select"
                  className="form-control"
                  name="allocationType"
                  value={(!isNew && addressAllocationEntity.allocationType) || 'RESERVED'}
                >
                  <option value="RESERVED">{translate('activePortApp.AllocationTypeEnum.RESERVED')}</option>
                  <option value="AUTO">{translate('activePortApp.AllocationTypeEnum.AUTO')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="serialNumberLabel" for="address-allocation-serialNumber">
                  <Translate contentKey="activePortApp.addressAllocation.serialNumber">Serial Number</Translate>
                </Label>
                <AvField id="address-allocation-serialNumber" type="text" name="serialNumber" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/address-allocation" replace color="info">
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
  addressAllocationEntity: storeState.addressAllocation.entity,
  loading: storeState.addressAllocation.loading,
  updating: storeState.addressAllocation.updating,
  updateSuccess: storeState.addressAllocation.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AddressAllocationUpdate);
