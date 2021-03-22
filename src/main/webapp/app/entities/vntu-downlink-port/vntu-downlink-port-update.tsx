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
import { getEntity, updateEntity, createEntity, reset } from './vntu-downlink-port.reducer';
import { IVntuDownlinkPort } from 'app/shared/model/vntu-downlink-port.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IVntuDownlinkPortUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const VntuDownlinkPortUpdate = (props: IVntuDownlinkPortUpdateProps) => {
  const [centralSwitchId, setCentralSwitchId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { vntuDownlinkPortEntity, centralSwitches, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/vntu-downlink-port' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCentralSwitches();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...vntuDownlinkPortEntity,
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
          <h2 id="activePortApp.vntuDownlinkPort.home.createOrEditLabel">
            <Translate contentKey="activePortApp.vntuDownlinkPort.home.createOrEditLabel">Create or edit a VntuDownlinkPort</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : vntuDownlinkPortEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="vntu-downlink-port-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="vntu-downlink-port-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="vntu-downlink-port-name">
                  <Translate contentKey="activePortApp.vntuDownlinkPort.name">Name</Translate>
                </Label>
                <AvField id="vntu-downlink-port-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="uidLabel" for="vntu-downlink-port-uid">
                  <Translate contentKey="activePortApp.vntuDownlinkPort.uid">Uid</Translate>
                </Label>
                <AvField id="vntu-downlink-port-uid" type="text" name="uid" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="vntu-downlink-port-description">
                  <Translate contentKey="activePortApp.vntuDownlinkPort.description">Description</Translate>
                </Label>
                <AvField id="vntu-downlink-port-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="assignedOrgNameLabel" for="vntu-downlink-port-assignedOrgName">
                  <Translate contentKey="activePortApp.vntuDownlinkPort.assignedOrgName">Assigned Org Name</Translate>
                </Label>
                <AvField id="vntu-downlink-port-assignedOrgName" type="text" name="assignedOrgName" />
              </AvGroup>
              <AvGroup>
                <Label id="assignedTenantNameLabel" for="vntu-downlink-port-assignedTenantName">
                  <Translate contentKey="activePortApp.vntuDownlinkPort.assignedTenantName">Assigned Tenant Name</Translate>
                </Label>
                <AvField id="vntu-downlink-port-assignedTenantName" type="text" name="assignedTenantName" />
              </AvGroup>
              <AvGroup>
                <Label id="assignedOrgIdLabel" for="vntu-downlink-port-assignedOrgId">
                  <Translate contentKey="activePortApp.vntuDownlinkPort.assignedOrgId">Assigned Org Id</Translate>
                </Label>
                <AvField id="vntu-downlink-port-assignedOrgId" type="text" name="assignedOrgId" />
              </AvGroup>
              <AvGroup>
                <Label id="assignedTenantIdLabel" for="vntu-downlink-port-assignedTenantId">
                  <Translate contentKey="activePortApp.vntuDownlinkPort.assignedTenantId">Assigned Tenant Id</Translate>
                </Label>
                <AvField id="vntu-downlink-port-assignedTenantId" type="text" name="assignedTenantId" />
              </AvGroup>
              <AvGroup>
                <Label id="assignedVntuIdLabel" for="vntu-downlink-port-assignedVntuId">
                  <Translate contentKey="activePortApp.vntuDownlinkPort.assignedVntuId">Assigned Vntu Id</Translate>
                </Label>
                <AvField id="vntu-downlink-port-assignedVntuId" type="string" className="form-control" name="assignedVntuId" />
              </AvGroup>
              <AvGroup>
                <Label id="assignedVntuNameLabel" for="vntu-downlink-port-assignedVntuName">
                  <Translate contentKey="activePortApp.vntuDownlinkPort.assignedVntuName">Assigned Vntu Name</Translate>
                </Label>
                <AvField id="vntu-downlink-port-assignedVntuName" type="text" name="assignedVntuName" />
              </AvGroup>
              <AvGroup>
                <Label for="vntu-downlink-port-centralSwitch">
                  <Translate contentKey="activePortApp.vntuDownlinkPort.centralSwitch">Central Switch</Translate>
                </Label>
                <AvInput id="vntu-downlink-port-centralSwitch" type="select" className="form-control" name="centralSwitchId">
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
              <Button tag={Link} id="cancel-save" to="/vntu-downlink-port" replace color="info">
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
  vntuDownlinkPortEntity: storeState.vntuDownlinkPort.entity,
  loading: storeState.vntuDownlinkPort.loading,
  updating: storeState.vntuDownlinkPort.updating,
  updateSuccess: storeState.vntuDownlinkPort.updateSuccess,
});

const mapDispatchToProps = {
  getCentralSwitches,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(VntuDownlinkPortUpdate);
