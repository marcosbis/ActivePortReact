import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IConfigJob } from 'app/shared/model/config-job.model';
import { getEntities as getConfigJobs } from 'app/entities/config-job/config-job.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './notification-event.reducer';
import { INotificationEvent } from 'app/shared/model/notification-event.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface INotificationEventUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NotificationEventUpdate = (props: INotificationEventUpdateProps) => {
  const [jobId, setJobId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { notificationEventEntity, configJobs, loading, updating } = props;

  const { errorMessage } = notificationEventEntity;

  const handleClose = () => {
    props.history.push('/notification-event' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getConfigJobs();
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
    if (errors.length === 0) {
      const entity = {
        ...notificationEventEntity,
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
          <h2 id="activePortApp.notificationEvent.home.createOrEditLabel">
            <Translate contentKey="activePortApp.notificationEvent.home.createOrEditLabel">Create or edit a NotificationEvent</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : notificationEventEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="notification-event-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="notification-event-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="ntuNameLabel" for="notification-event-ntuName">
                  <Translate contentKey="activePortApp.notificationEvent.ntuName">Ntu Name</Translate>
                </Label>
                <AvField id="notification-event-ntuName" type="text" name="ntuName" />
              </AvGroup>
              <AvGroup>
                <Label id="ntuIdLabel" for="notification-event-ntuId">
                  <Translate contentKey="activePortApp.notificationEvent.ntuId">Ntu Id</Translate>
                </Label>
                <AvField id="notification-event-ntuId" type="string" className="form-control" name="ntuId" />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="notification-event-status">
                  <Translate contentKey="activePortApp.notificationEvent.status">Status</Translate>
                </Label>
                <AvInput
                  id="notification-event-status"
                  type="select"
                  className="form-control"
                  name="status"
                  value={(!isNew && notificationEventEntity.status) || 'COMPLETED'}
                >
                  <option value="COMPLETED">{translate('activePortApp.EventStatusEnum.COMPLETED')}</option>
                  <option value="REQUEST">{translate('activePortApp.EventStatusEnum.REQUEST')}</option>
                  <option value="FAILED">{translate('activePortApp.EventStatusEnum.FAILED')}</option>
                  <option value="ERROR">{translate('activePortApp.EventStatusEnum.ERROR')}</option>
                  <option value="PROGRESSING">{translate('activePortApp.EventStatusEnum.PROGRESSING')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="typeLabel" for="notification-event-type">
                  <Translate contentKey="activePortApp.notificationEvent.type">Type</Translate>
                </Label>
                <AvInput
                  id="notification-event-type"
                  type="select"
                  className="form-control"
                  name="type"
                  value={(!isNew && notificationEventEntity.type) || 'FIREWALL_ACTIVATE'}
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
                <Label id="messageLabel" for="notification-event-message">
                  <Translate contentKey="activePortApp.notificationEvent.message">Message</Translate>
                </Label>
                <AvField id="notification-event-message" type="text" name="message" />
              </AvGroup>
              <AvGroup>
                <Label id="serviceNameLabel" for="notification-event-serviceName">
                  <Translate contentKey="activePortApp.notificationEvent.serviceName">Service Name</Translate>
                </Label>
                <AvField id="notification-event-serviceName" type="text" name="serviceName" />
              </AvGroup>
              <AvGroup>
                <Label id="serviceIdLabel" for="notification-event-serviceId">
                  <Translate contentKey="activePortApp.notificationEvent.serviceId">Service Id</Translate>
                </Label>
                <AvField id="notification-event-serviceId" type="string" className="form-control" name="serviceId" />
              </AvGroup>
              <AvGroup>
                <Label id="vxcIdLabel" for="notification-event-vxcId">
                  <Translate contentKey="activePortApp.notificationEvent.vxcId">Vxc Id</Translate>
                </Label>
                <AvField id="notification-event-vxcId" type="string" className="form-control" name="vxcId" />
              </AvGroup>
              <AvGroup>
                <Label id="uuidLabel" for="notification-event-uuid">
                  <Translate contentKey="activePortApp.notificationEvent.uuid">Uuid</Translate>
                </Label>
                <AvField id="notification-event-uuid" type="text" name="uuid" />
              </AvGroup>
              <AvGroup>
                <Label id="userLabel" for="notification-event-user">
                  <Translate contentKey="activePortApp.notificationEvent.user">User</Translate>
                </Label>
                <AvField id="notification-event-user" type="text" name="user" />
              </AvGroup>
              <AvGroup>
                <Label id="errorMessageLabel" for="notification-event-errorMessage">
                  <Translate contentKey="activePortApp.notificationEvent.errorMessage">Error Message</Translate>
                </Label>
                <AvInput id="notification-event-errorMessage" type="textarea" name="errorMessage" />
              </AvGroup>
              <AvGroup>
                <Label for="notification-event-job">
                  <Translate contentKey="activePortApp.notificationEvent.job">Job</Translate>
                </Label>
                <AvInput id="notification-event-job" type="select" className="form-control" name="jobId">
                  <option value="" key="0" />
                  {configJobs
                    ? configJobs.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/notification-event" replace color="info">
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
  configJobs: storeState.configJob.entities,
  notificationEventEntity: storeState.notificationEvent.entity,
  loading: storeState.notificationEvent.loading,
  updating: storeState.notificationEvent.updating,
  updateSuccess: storeState.notificationEvent.updateSuccess,
});

const mapDispatchToProps = {
  getConfigJobs,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NotificationEventUpdate);
