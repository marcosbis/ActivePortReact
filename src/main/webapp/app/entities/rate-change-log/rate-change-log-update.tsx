import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUserService } from 'app/shared/model/user-service.model';
import { getEntities as getUserServices } from 'app/entities/user-service/user-service.reducer';
import { getEntity, updateEntity, createEntity, reset } from './rate-change-log.reducer';
import { IRateChangeLog } from 'app/shared/model/rate-change-log.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IRateChangeLogUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RateChangeLogUpdate = (props: IRateChangeLogUpdateProps) => {
  const [userServiceId, setUserServiceId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { rateChangeLogEntity, userServices, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/rate-change-log' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getUserServices();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...rateChangeLogEntity,
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
          <h2 id="activePortApp.rateChangeLog.home.createOrEditLabel">
            <Translate contentKey="activePortApp.rateChangeLog.home.createOrEditLabel">Create or edit a RateChangeLog</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : rateChangeLogEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="rate-change-log-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="rate-change-log-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="previuosRateLabel" for="rate-change-log-previuosRate">
                  <Translate contentKey="activePortApp.rateChangeLog.previuosRate">Previuos Rate</Translate>
                </Label>
                <AvField id="rate-change-log-previuosRate" type="string" className="form-control" name="previuosRate" />
              </AvGroup>
              <AvGroup>
                <Label id="rateLabel" for="rate-change-log-rate">
                  <Translate contentKey="activePortApp.rateChangeLog.rate">Rate</Translate>
                </Label>
                <AvField id="rate-change-log-rate" type="string" className="form-control" name="rate" />
              </AvGroup>
              <AvGroup>
                <Label for="rate-change-log-userService">
                  <Translate contentKey="activePortApp.rateChangeLog.userService">User Service</Translate>
                </Label>
                <AvInput id="rate-change-log-userService" type="select" className="form-control" name="userServiceId">
                  <option value="" key="0" />
                  {userServices
                    ? userServices.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/rate-change-log" replace color="info">
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
  userServices: storeState.userService.entities,
  rateChangeLogEntity: storeState.rateChangeLog.entity,
  loading: storeState.rateChangeLog.loading,
  updating: storeState.rateChangeLog.updating,
  updateSuccess: storeState.rateChangeLog.updateSuccess,
});

const mapDispatchToProps = {
  getUserServices,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RateChangeLogUpdate);
