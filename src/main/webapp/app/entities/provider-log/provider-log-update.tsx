import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './provider-log.reducer';
import { IProviderLog } from 'app/shared/model/provider-log.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IProviderLogUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProviderLogUpdate = (props: IProviderLogUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { providerLogEntity, loading, updating } = props;

  const { log, request, responseLog } = providerLogEntity;

  const handleClose = () => {
    props.history.push('/provider-log' + props.location.search);
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
    if (errors.length === 0) {
      const entity = {
        ...providerLogEntity,
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
          <h2 id="activePortApp.providerLog.home.createOrEditLabel">
            <Translate contentKey="activePortApp.providerLog.home.createOrEditLabel">Create or edit a ProviderLog</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : providerLogEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="provider-log-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="provider-log-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="orgIdLabel" for="provider-log-orgId">
                  <Translate contentKey="activePortApp.providerLog.orgId">Org Id</Translate>
                </Label>
                <AvField id="provider-log-orgId" type="text" name="orgId" />
              </AvGroup>
              <AvGroup>
                <Label id="tenantIdLabel" for="provider-log-tenantId">
                  <Translate contentKey="activePortApp.providerLog.tenantId">Tenant Id</Translate>
                </Label>
                <AvField id="provider-log-tenantId" type="text" name="tenantId" />
              </AvGroup>
              <AvGroup>
                <Label id="serviceIdLabel" for="provider-log-serviceId">
                  <Translate contentKey="activePortApp.providerLog.serviceId">Service Id</Translate>
                </Label>
                <AvField id="provider-log-serviceId" type="string" className="form-control" name="serviceId" />
              </AvGroup>
              <AvGroup>
                <Label id="logLabel" for="provider-log-log">
                  <Translate contentKey="activePortApp.providerLog.log">Log</Translate>
                </Label>
                <AvInput id="provider-log-log" type="textarea" name="log" />
              </AvGroup>
              <AvGroup>
                <Label id="typeLabel" for="provider-log-type">
                  <Translate contentKey="activePortApp.providerLog.type">Type</Translate>
                </Label>
                <AvField id="provider-log-type" type="text" name="type" />
              </AvGroup>
              <AvGroup>
                <Label id="jobUidLabel" for="provider-log-jobUid">
                  <Translate contentKey="activePortApp.providerLog.jobUid">Job Uid</Translate>
                </Label>
                <AvField id="provider-log-jobUid" type="text" name="jobUid" />
              </AvGroup>
              <AvGroup>
                <Label id="ntuIdLabel" for="provider-log-ntuId">
                  <Translate contentKey="activePortApp.providerLog.ntuId">Ntu Id</Translate>
                </Label>
                <AvField id="provider-log-ntuId" type="string" className="form-control" name="ntuId" />
              </AvGroup>
              <AvGroup>
                <Label id="requestLabel" for="provider-log-request">
                  <Translate contentKey="activePortApp.providerLog.request">Request</Translate>
                </Label>
                <AvInput id="provider-log-request" type="textarea" name="request" />
              </AvGroup>
              <AvGroup>
                <Label id="responseLogLabel" for="provider-log-responseLog">
                  <Translate contentKey="activePortApp.providerLog.responseLog">Response Log</Translate>
                </Label>
                <AvInput id="provider-log-responseLog" type="textarea" name="responseLog" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/provider-log" replace color="info">
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
  providerLogEntity: storeState.providerLog.entity,
  loading: storeState.providerLog.loading,
  updating: storeState.providerLog.updating,
  updateSuccess: storeState.providerLog.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(ProviderLogUpdate);
