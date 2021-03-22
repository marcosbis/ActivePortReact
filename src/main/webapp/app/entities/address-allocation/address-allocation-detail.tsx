import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './address-allocation.reducer';
import { IAddressAllocation } from 'app/shared/model/address-allocation.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAddressAllocationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AddressAllocationDetail = (props: IAddressAllocationDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { addressAllocationEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.addressAllocation.detail.title">AddressAllocation</Translate> [
          <b>{addressAllocationEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="subnet">
              <Translate contentKey="activePortApp.addressAllocation.subnet">Subnet</Translate>
            </span>
          </dt>
          <dd>{addressAllocationEntity.subnet}</dd>
          <dt>
            <span id="deviceName">
              <Translate contentKey="activePortApp.addressAllocation.deviceName">Device Name</Translate>
            </span>
          </dt>
          <dd>{addressAllocationEntity.deviceName}</dd>
          <dt>
            <span id="deviceMode">
              <Translate contentKey="activePortApp.addressAllocation.deviceMode">Device Mode</Translate>
            </span>
          </dt>
          <dd>{addressAllocationEntity.deviceMode}</dd>
          <dt>
            <span id="deviceId">
              <Translate contentKey="activePortApp.addressAllocation.deviceId">Device Id</Translate>
            </span>
          </dt>
          <dd>{addressAllocationEntity.deviceId}</dd>
          <dt>
            <span id="deviceType">
              <Translate contentKey="activePortApp.addressAllocation.deviceType">Device Type</Translate>
            </span>
          </dt>
          <dd>{addressAllocationEntity.deviceType}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.addressAllocation.description">Description</Translate>
            </span>
          </dt>
          <dd>{addressAllocationEntity.description}</dd>
          <dt>
            <span id="allocationType">
              <Translate contentKey="activePortApp.addressAllocation.allocationType">Allocation Type</Translate>
            </span>
          </dt>
          <dd>{addressAllocationEntity.allocationType}</dd>
          <dt>
            <span id="serialNumber">
              <Translate contentKey="activePortApp.addressAllocation.serialNumber">Serial Number</Translate>
            </span>
          </dt>
          <dd>{addressAllocationEntity.serialNumber}</dd>
        </dl>
        <Button tag={Link} to="/address-allocation" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/address-allocation/${addressAllocationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ addressAllocation }: IRootState) => ({
  addressAllocationEntity: addressAllocation.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AddressAllocationDetail);
