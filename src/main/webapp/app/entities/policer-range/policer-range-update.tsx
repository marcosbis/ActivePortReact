import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPolicerSchedule } from 'app/shared/model/policer-schedule.model';
import { getEntities as getPolicerSchedules } from 'app/entities/policer-schedule/policer-schedule.reducer';
import { getEntity, updateEntity, createEntity, reset } from './policer-range.reducer';
import { IPolicerRange } from 'app/shared/model/policer-range.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPolicerRangeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PolicerRangeUpdate = (props: IPolicerRangeUpdateProps) => {
  const [policerScheduleId, setPolicerScheduleId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { policerRangeEntity, policerSchedules, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/policer-range' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getPolicerSchedules();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.startPolicer = convertDateTimeToServer(values.startPolicer);
    values.endPolicer = convertDateTimeToServer(values.endPolicer);

    if (errors.length === 0) {
      const entity = {
        ...policerRangeEntity,
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
          <h2 id="activePortApp.policerRange.home.createOrEditLabel">
            <Translate contentKey="activePortApp.policerRange.home.createOrEditLabel">Create or edit a PolicerRange</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : policerRangeEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="policer-range-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="policer-range-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="policer-range-name">
                  <Translate contentKey="activePortApp.policerRange.name">Name</Translate>
                </Label>
                <AvField id="policer-range-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="burstLabel" for="policer-range-burst">
                  <Translate contentKey="activePortApp.policerRange.burst">Burst</Translate>
                </Label>
                <AvField id="policer-range-burst" type="string" className="form-control" name="burst" />
              </AvGroup>
              <AvGroup>
                <Label id="bandwidthLabel" for="policer-range-bandwidth">
                  <Translate contentKey="activePortApp.policerRange.bandwidth">Bandwidth</Translate>
                </Label>
                <AvField id="policer-range-bandwidth" type="string" className="form-control" name="bandwidth" />
              </AvGroup>
              <AvGroup>
                <Label id="startPolicerLabel" for="policer-range-startPolicer">
                  <Translate contentKey="activePortApp.policerRange.startPolicer">Start Policer</Translate>
                </Label>
                <AvInput
                  id="policer-range-startPolicer"
                  type="datetime-local"
                  className="form-control"
                  name="startPolicer"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.policerRangeEntity.startPolicer)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="endPolicerLabel" for="policer-range-endPolicer">
                  <Translate contentKey="activePortApp.policerRange.endPolicer">End Policer</Translate>
                </Label>
                <AvInput
                  id="policer-range-endPolicer"
                  type="datetime-local"
                  className="form-control"
                  name="endPolicer"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.policerRangeEntity.endPolicer)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="rangeTypeLabel" for="policer-range-rangeType">
                  <Translate contentKey="activePortApp.policerRange.rangeType">Range Type</Translate>
                </Label>
                <AvInput
                  id="policer-range-rangeType"
                  type="select"
                  className="form-control"
                  name="rangeType"
                  value={(!isNew && policerRangeEntity.rangeType) || 'EVERYDAY'}
                >
                  <option value="EVERYDAY">{translate('activePortApp.RangeTypeEnum.EVERYDAY')}</option>
                  <option value="BETWEEN">{translate('activePortApp.RangeTypeEnum.BETWEEN')}</option>
                  <option value="WEEKDAYS">{translate('activePortApp.RangeTypeEnum.WEEKDAYS')}</option>
                  <option value="WEEKENDS">{translate('activePortApp.RangeTypeEnum.WEEKENDS')}</option>
                  <option value="DAYS">{translate('activePortApp.RangeTypeEnum.DAYS')}</option>
                  <option value="FROM_TO_DAY">{translate('activePortApp.RangeTypeEnum.FROM_TO_DAY')}</option>
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/policer-range" replace color="info">
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
  policerSchedules: storeState.policerSchedule.entities,
  policerRangeEntity: storeState.policerRange.entity,
  loading: storeState.policerRange.loading,
  updating: storeState.policerRange.updating,
  updateSuccess: storeState.policerRange.updateSuccess,
});

const mapDispatchToProps = {
  getPolicerSchedules,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PolicerRangeUpdate);
