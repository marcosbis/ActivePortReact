import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './juno-command-log.reducer';
import { IJunoCommandLog } from 'app/shared/model/juno-command-log.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IJunoCommandLogUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const JunoCommandLogUpdate = (props: IJunoCommandLogUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { junoCommandLogEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/juno-command-log' + props.location.search);
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
    values.executedDate = convertDateTimeToServer(values.executedDate);

    if (errors.length === 0) {
      const entity = {
        ...junoCommandLogEntity,
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
          <h2 id="activePortApp.junoCommandLog.home.createOrEditLabel">
            <Translate contentKey="activePortApp.junoCommandLog.home.createOrEditLabel">Create or edit a JunoCommandLog</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : junoCommandLogEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="juno-command-log-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="juno-command-log-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="ntuIdLabel" for="juno-command-log-ntuId">
                  <Translate contentKey="activePortApp.junoCommandLog.ntuId">Ntu Id</Translate>
                </Label>
                <AvField id="juno-command-log-ntuId" type="string" className="form-control" name="ntuId" />
              </AvGroup>
              <AvGroup>
                <Label id="switchIdLabel" for="juno-command-log-switchId">
                  <Translate contentKey="activePortApp.junoCommandLog.switchId">Switch Id</Translate>
                </Label>
                <AvField id="juno-command-log-switchId" type="string" className="form-control" name="switchId" />
              </AvGroup>
              <AvGroup>
                <Label id="serviceIdLabel" for="juno-command-log-serviceId">
                  <Translate contentKey="activePortApp.junoCommandLog.serviceId">Service Id</Translate>
                </Label>
                <AvField id="juno-command-log-serviceId" type="string" className="form-control" name="serviceId" />
              </AvGroup>
              <AvGroup>
                <Label id="vxcIdLabel" for="juno-command-log-vxcId">
                  <Translate contentKey="activePortApp.junoCommandLog.vxcId">Vxc Id</Translate>
                </Label>
                <AvField id="juno-command-log-vxcId" type="string" className="form-control" name="vxcId" />
              </AvGroup>
              <AvGroup>
                <Label id="deviceUrlLabel" for="juno-command-log-deviceUrl">
                  <Translate contentKey="activePortApp.junoCommandLog.deviceUrl">Device Url</Translate>
                </Label>
                <AvField id="juno-command-log-deviceUrl" type="text" name="deviceUrl" />
              </AvGroup>
              <AvGroup>
                <Label id="uuidLabel" for="juno-command-log-uuid">
                  <Translate contentKey="activePortApp.junoCommandLog.uuid">Uuid</Translate>
                </Label>
                <AvField id="juno-command-log-uuid" type="text" name="uuid" />
              </AvGroup>
              <AvGroup>
                <Label id="commandLabel" for="juno-command-log-command">
                  <Translate contentKey="activePortApp.junoCommandLog.command">Command</Translate>
                </Label>
                <AvField id="juno-command-log-command" type="text" name="command" />
              </AvGroup>
              <AvGroup>
                <Label id="cmdResponseLabel" for="juno-command-log-cmdResponse">
                  <Translate contentKey="activePortApp.junoCommandLog.cmdResponse">Cmd Response</Translate>
                </Label>
                <AvField id="juno-command-log-cmdResponse" type="text" name="cmdResponse" />
              </AvGroup>
              <AvGroup>
                <Label id="executedDateLabel" for="juno-command-log-executedDate">
                  <Translate contentKey="activePortApp.junoCommandLog.executedDate">Executed Date</Translate>
                </Label>
                <AvInput
                  id="juno-command-log-executedDate"
                  type="datetime-local"
                  className="form-control"
                  name="executedDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.junoCommandLogEntity.executedDate)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="eventTypeLabel" for="juno-command-log-eventType">
                  <Translate contentKey="activePortApp.junoCommandLog.eventType">Event Type</Translate>
                </Label>
                <AvInput
                  id="juno-command-log-eventType"
                  type="select"
                  className="form-control"
                  name="eventType"
                  value={(!isNew && junoCommandLogEntity.eventType) || 'FIREWALL_ACTIVATE'}
                >
                  <option value="FIREWALL_ACTIVATE">{translate('activePortApp.EventTypeEnum.FIREWALL_ACTIVATE')}</option>
                  <option value="FIREWALL_DEACTIVATE">{translate('activePortApp.EventTypeEnum.FIREWALL_DEACTIVATE')}</option>
                  <option value="DELETE_SERVICE">{translate('activePortApp.EventTypeEnum.DELETE_SERVICE')}</option>
                  <option value="CHANGE_PORT">{translate('activePortApp.EventTypeEnum.CHANGE_PORT')}</option>
                  <option value="RETAGG_VLAN">{translate('activePortApp.EventTypeEnum.RETAGG_VLAN')}</option>
                  <option value="PORT_SPEED_CHANGE">{translate('activePortApp.EventTypeEnum.PORT_SPEED_CHANGE')}</option>
                  <option value="SERVICE_CREATE">{translate('activePortApp.EventTypeEnum.SERVICE_CREATE')}</option>
                  <option value="SERVICE_CREATE_FAILED">{translate('activePortApp.EventTypeEnum.SERVICE_CREATE_FAILED')}</option>
                  <option value="MANAGED_FIREWALL_CREATE">{translate('activePortApp.EventTypeEnum.MANAGED_FIREWALL_CREATE')}</option>
                  <option value="MANAGED_FIREWALL_DELETE">{translate('activePortApp.EventTypeEnum.MANAGED_FIREWALL_DELETE')}</option>
                  <option value="MANAGED_FIREWALL_UPDATE">{translate('activePortApp.EventTypeEnum.MANAGED_FIREWALL_UPDATE')}</option>
                  <option value="MANAGED_FIREWALL_DECOMMISSION">
                    {translate('activePortApp.EventTypeEnum.MANAGED_FIREWALL_DECOMMISSION')}
                  </option>
                  <option value="REQUEST_DECOMMISSION">{translate('activePortApp.EventTypeEnum.REQUEST_DECOMMISSION')}</option>
                  <option value="DISABLE_SERVICE_FROM_NTU">{translate('activePortApp.EventTypeEnum.DISABLE_SERVICE_FROM_NTU')}</option>
                  <option value="RULE_SET_UPDATE">{translate('activePortApp.EventTypeEnum.RULE_SET_UPDATE')}</option>
                  <option value="RULE_SET_CREATE">{translate('activePortApp.EventTypeEnum.RULE_SET_CREATE')}</option>
                  <option value="RULE_SET_DELETE">{translate('activePortApp.EventTypeEnum.RULE_SET_DELETE')}</option>
                  <option value="RULE_SET_REORDER">{translate('activePortApp.EventTypeEnum.RULE_SET_REORDER')}</option>
                  <option value="SERVICE_UPDATE">{translate('activePortApp.EventTypeEnum.SERVICE_UPDATE')}</option>
                  <option value="POOL_UPDATE">{translate('activePortApp.EventTypeEnum.POOL_UPDATE')}</option>
                  <option value="POOL_CREATE">{translate('activePortApp.EventTypeEnum.POOL_CREATE')}</option>
                  <option value="POOL_DELETE">{translate('activePortApp.EventTypeEnum.POOL_DELETE')}</option>
                  <option value="ON_COMMIT">{translate('activePortApp.EventTypeEnum.ON_COMMIT')}</option>
                  <option value="SERVICE_AND_PORT_CHANGE">{translate('activePortApp.EventTypeEnum.SERVICE_AND_PORT_CHANGE')}</option>
                  <option value="EBGP_FILTERS_UPDATE">{translate('activePortApp.EventTypeEnum.EBGP_FILTERS_UPDATE')}</option>
                  <option value="STATIC_ROUTE_CREATE">{translate('activePortApp.EventTypeEnum.STATIC_ROUTE_CREATE')}</option>
                  <option value="STATIC_ROUTE_UPDATE">{translate('activePortApp.EventTypeEnum.STATIC_ROUTE_UPDATE')}</option>
                  <option value="STATIC_ROUTE_DELETE">{translate('activePortApp.EventTypeEnum.STATIC_ROUTE_DELETE')}</option>
                  <option value="UPDATE_RATE">{translate('activePortApp.EventTypeEnum.UPDATE_RATE')}</option>
                  <option value="DEFAULT_POLICY_UPDATE">{translate('activePortApp.EventTypeEnum.DEFAULT_POLICY_UPDATE')}</option>
                  <option value="POLICY_CREATE">{translate('activePortApp.EventTypeEnum.POLICY_CREATE')}</option>
                  <option value="POLICY_DELETE">{translate('activePortApp.EventTypeEnum.POLICY_DELETE')}</option>
                  <option value="POLICY_UPDATE">{translate('activePortApp.EventTypeEnum.POLICY_UPDATE')}</option>
                  <option value="APPLICATION_UPDATE">{translate('activePortApp.EventTypeEnum.APPLICATION_UPDATE')}</option>
                  <option value="APPLICATION_DELETE">{translate('activePortApp.EventTypeEnum.APPLICATION_DELETE')}</option>
                  <option value="APPLICATION_CREATE">{translate('activePortApp.EventTypeEnum.APPLICATION_CREATE')}</option>
                  <option value="POLICY_REORDER">{translate('activePortApp.EventTypeEnum.POLICY_REORDER')}</option>
                  <option value="ZONE_CREATE">{translate('activePortApp.EventTypeEnum.ZONE_CREATE')}</option>
                  <option value="ZONE_UPDATE">{translate('activePortApp.EventTypeEnum.ZONE_UPDATE')}</option>
                  <option value="ZONE_DELETE">{translate('activePortApp.EventTypeEnum.ZONE_DELETE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="userLabel" for="juno-command-log-user">
                  <Translate contentKey="activePortApp.junoCommandLog.user">User</Translate>
                </Label>
                <AvField id="juno-command-log-user" type="text" name="user" />
              </AvGroup>
              <AvGroup>
                <Label id="deviceNameLabel" for="juno-command-log-deviceName">
                  <Translate contentKey="activePortApp.junoCommandLog.deviceName">Device Name</Translate>
                </Label>
                <AvField id="juno-command-log-deviceName" type="text" name="deviceName" />
              </AvGroup>
              <AvGroup>
                <Label id="targetTypeLabel" for="juno-command-log-targetType">
                  <Translate contentKey="activePortApp.junoCommandLog.targetType">Target Type</Translate>
                </Label>
                <AvInput
                  id="juno-command-log-targetType"
                  type="select"
                  className="form-control"
                  name="targetType"
                  value={(!isNew && junoCommandLogEntity.targetType) || 'NTU'}
                >
                  <option value="NTU">{translate('activePortApp.DeviceTargetTypeEnum.NTU')}</option>
                  <option value="FIREWALL">{translate('activePortApp.DeviceTargetTypeEnum.FIREWALL')}</option>
                  <option value="SWITCH">{translate('activePortApp.DeviceTargetTypeEnum.SWITCH')}</option>
                  <option value="DEMO">{translate('activePortApp.DeviceTargetTypeEnum.DEMO')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup check>
                <Label id="hasErrorsLabel">
                  <AvInput id="juno-command-log-hasErrors" type="checkbox" className="form-check-input" name="hasErrors" />
                  <Translate contentKey="activePortApp.junoCommandLog.hasErrors">Has Errors</Translate>
                </Label>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/juno-command-log" replace color="info">
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
  junoCommandLogEntity: storeState.junoCommandLog.entity,
  loading: storeState.junoCommandLog.loading,
  updating: storeState.junoCommandLog.updating,
  updateSuccess: storeState.junoCommandLog.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(JunoCommandLogUpdate);
