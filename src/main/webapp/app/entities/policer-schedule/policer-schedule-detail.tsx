import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './policer-schedule.reducer';
import { IPolicerSchedule } from 'app/shared/model/policer-schedule.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPolicerScheduleDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PolicerScheduleDetail = (props: IPolicerScheduleDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { policerScheduleEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.policerSchedule.detail.title">PolicerSchedule</Translate> [<b>{policerScheduleEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.policerSchedule.name">Name</Translate>
            </span>
          </dt>
          <dd>{policerScheduleEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.policerSchedule.description">Description</Translate>
            </span>
          </dt>
          <dd>{policerScheduleEntity.description}</dd>
          <dt>
            <span id="days">
              <Translate contentKey="activePortApp.policerSchedule.days">Days</Translate>
            </span>
          </dt>
          <dd>{policerScheduleEntity.days}</dd>
          <dt>
            <Translate contentKey="activePortApp.policerSchedule.ntu">Ntu</Translate>
          </dt>
          <dd>{policerScheduleEntity.ntuName ? policerScheduleEntity.ntuName : ''}</dd>
          <dt>
            <Translate contentKey="activePortApp.policerSchedule.policerRange">Policer Range</Translate>
          </dt>
          <dd>
            {policerScheduleEntity.policerRanges
              ? policerScheduleEntity.policerRanges.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.name}</a>
                    {policerScheduleEntity.policerRanges && i === policerScheduleEntity.policerRanges.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/policer-schedule" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/policer-schedule/${policerScheduleEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ policerSchedule }: IRootState) => ({
  policerScheduleEntity: policerSchedule.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PolicerScheduleDetail);
