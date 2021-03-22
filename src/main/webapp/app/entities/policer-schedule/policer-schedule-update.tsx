import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { INtu } from 'app/shared/model/ntu.model';
import { getEntities as getNtus } from 'app/entities/ntu/ntu.reducer';
import { IPolicerRange } from 'app/shared/model/policer-range.model';
import { getEntities as getPolicerRanges } from 'app/entities/policer-range/policer-range.reducer';
import { getEntity, updateEntity, createEntity, reset } from './policer-schedule.reducer';
import { IPolicerSchedule } from 'app/shared/model/policer-schedule.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPolicerScheduleUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PolicerScheduleUpdate = (props: IPolicerScheduleUpdateProps) => {
  const [idspolicerRange, setIdspolicerRange] = useState([]);
  const [ntuId, setNtuId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { policerScheduleEntity, ntus, policerRanges, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/policer-schedule' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getNtus();
    props.getPolicerRanges();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...policerScheduleEntity,
        ...values,
        policerRanges: mapIdList(values.policerRanges),
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
          <h2 id="activePortApp.policerSchedule.home.createOrEditLabel">
            <Translate contentKey="activePortApp.policerSchedule.home.createOrEditLabel">Create or edit a PolicerSchedule</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : policerScheduleEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="policer-schedule-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="policer-schedule-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="policer-schedule-name">
                  <Translate contentKey="activePortApp.policerSchedule.name">Name</Translate>
                </Label>
                <AvField id="policer-schedule-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="policer-schedule-description">
                  <Translate contentKey="activePortApp.policerSchedule.description">Description</Translate>
                </Label>
                <AvField id="policer-schedule-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="daysLabel" for="policer-schedule-days">
                  <Translate contentKey="activePortApp.policerSchedule.days">Days</Translate>
                </Label>
                <AvInput
                  id="policer-schedule-days"
                  type="select"
                  className="form-control"
                  name="days"
                  value={(!isNew && policerScheduleEntity.days) || 'MONDAY'}
                >
                  <option value="MONDAY">{translate('activePortApp.ScheduleDayEnum.MONDAY')}</option>
                  <option value="TUESDAY">{translate('activePortApp.ScheduleDayEnum.TUESDAY')}</option>
                  <option value="WEDNESDAY">{translate('activePortApp.ScheduleDayEnum.WEDNESDAY')}</option>
                  <option value="THURSDAY">{translate('activePortApp.ScheduleDayEnum.THURSDAY')}</option>
                  <option value="FRIDAY">{translate('activePortApp.ScheduleDayEnum.FRIDAY')}</option>
                  <option value="SATURDAY">{translate('activePortApp.ScheduleDayEnum.SATURDAY')}</option>
                  <option value="SUNDAY">{translate('activePortApp.ScheduleDayEnum.SUNDAY')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="policer-schedule-ntu">
                  <Translate contentKey="activePortApp.policerSchedule.ntu">Ntu</Translate>
                </Label>
                <AvInput id="policer-schedule-ntu" type="select" className="form-control" name="ntuId">
                  <option value="" key="0" />
                  {ntus
                    ? ntus.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="policer-schedule-policerRange">
                  <Translate contentKey="activePortApp.policerSchedule.policerRange">Policer Range</Translate>
                </Label>
                <AvInput
                  id="policer-schedule-policerRange"
                  type="select"
                  multiple
                  className="form-control"
                  name="policerRanges"
                  value={policerScheduleEntity.policerRanges && policerScheduleEntity.policerRanges.map(e => e.id)}
                >
                  <option value="" key="0" />
                  {policerRanges
                    ? policerRanges.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/policer-schedule" replace color="info">
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
  ntus: storeState.ntu.entities,
  policerRanges: storeState.policerRange.entities,
  policerScheduleEntity: storeState.policerSchedule.entity,
  loading: storeState.policerSchedule.loading,
  updating: storeState.policerSchedule.updating,
  updateSuccess: storeState.policerSchedule.updateSuccess,
});

const mapDispatchToProps = {
  getNtus,
  getPolicerRanges,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PolicerScheduleUpdate);
