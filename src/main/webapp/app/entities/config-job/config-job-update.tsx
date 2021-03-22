import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './config-job.reducer';
import { IConfigJob } from 'app/shared/model/config-job.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IConfigJobUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ConfigJobUpdate = (props: IConfigJobUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { configJobEntity, loading, updating } = props;

  const { command, errorMessage } = configJobEntity;

  const handleClose = () => {
    props.history.push('/config-job' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  const onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => props.setBlob(name, data, contentType), isAnImage);
  };

  const clearBlob = name => () => {
    props.setBlob(name, undefined, undefined);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.executed = convertDateTimeToServer(values.executed);

    if (errors.length === 0) {
      const entity = {
        ...configJobEntity,
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
          <h2 id="activePortApp.configJob.home.createOrEditLabel">
            <Translate contentKey="activePortApp.configJob.home.createOrEditLabel">Create or edit a ConfigJob</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : configJobEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="config-job-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="config-job-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="hostIdLabel" for="config-job-hostId">
                  <Translate contentKey="activePortApp.configJob.hostId">Host Id</Translate>
                </Label>
                <AvField id="config-job-hostId" type="text" name="hostId" />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="config-job-status">
                  <Translate contentKey="activePortApp.configJob.status">Status</Translate>
                </Label>
                <AvField id="config-job-status" type="text" name="status" />
              </AvGroup>
              <AvGroup>
                <Label id="uuidLabel" for="config-job-uuid">
                  <Translate contentKey="activePortApp.configJob.uuid">Uuid</Translate>
                </Label>
                <AvField id="config-job-uuid" type="text" name="uuid" />
              </AvGroup>
              <AvGroup>
                <Label id="messageLabel" for="config-job-message">
                  <Translate contentKey="activePortApp.configJob.message">Message</Translate>
                </Label>
                <AvField id="config-job-message" type="text" name="message" />
              </AvGroup>
              <AvGroup>
                <Label id="commandLabel" for="config-job-command">
                  <Translate contentKey="activePortApp.configJob.command">Command</Translate>
                </Label>
                <AvInput id="config-job-command" type="textarea" name="command" />
              </AvGroup>
              <AvGroup>
                <Label id="executedLabel" for="config-job-executed">
                  <Translate contentKey="activePortApp.configJob.executed">Executed</Translate>
                </Label>
                <AvInput
                  id="config-job-executed"
                  type="datetime-local"
                  className="form-control"
                  name="executed"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.configJobEntity.executed)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="executedStatusLabel" for="config-job-executedStatus">
                  <Translate contentKey="activePortApp.configJob.executedStatus">Executed Status</Translate>
                </Label>
                <AvField id="config-job-executedStatus" type="text" name="executedStatus" />
              </AvGroup>
              <AvGroup>
                <Label id="executedMessageLabel" for="config-job-executedMessage">
                  <Translate contentKey="activePortApp.configJob.executedMessage">Executed Message</Translate>
                </Label>
                <AvField id="config-job-executedMessage" type="text" name="executedMessage" />
              </AvGroup>
              <AvGroup>
                <Label id="ntuIdLabel" for="config-job-ntuId">
                  <Translate contentKey="activePortApp.configJob.ntuId">Ntu Id</Translate>
                </Label>
                <AvField id="config-job-ntuId" type="string" className="form-control" name="ntuId" />
              </AvGroup>
              <AvGroup>
                <Label id="eventTypeLabel" for="config-job-eventType">
                  <Translate contentKey="activePortApp.configJob.eventType">Event Type</Translate>
                </Label>
                <AvInput
                  id="config-job-eventType"
                  type="select"
                  className="form-control"
                  name="eventType"
                  value={(!isNew && configJobEntity.eventType) || 'FIREWALL_ACTIVATE'}
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
                <Label id="userLabel" for="config-job-user">
                  <Translate contentKey="activePortApp.configJob.user">User</Translate>
                </Label>
                <AvField id="config-job-user" type="text" name="user" />
              </AvGroup>
              <AvGroup>
                <Label id="errorMessageLabel" for="config-job-errorMessage">
                  <Translate contentKey="activePortApp.configJob.errorMessage">Error Message</Translate>
                </Label>
                <AvInput id="config-job-errorMessage" type="textarea" name="errorMessage" />
              </AvGroup>
              <AvGroup>
                <Label id="callbackUrlLabel" for="config-job-callbackUrl">
                  <Translate contentKey="activePortApp.configJob.callbackUrl">Callback Url</Translate>
                </Label>
                <AvField id="config-job-callbackUrl" type="text" name="callbackUrl" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/config-job" replace color="info">
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
  configJobEntity: storeState.configJob.entity,
  loading: storeState.configJob.loading,
  updating: storeState.configJob.updating,
  updateSuccess: storeState.configJob.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ConfigJobUpdate);
