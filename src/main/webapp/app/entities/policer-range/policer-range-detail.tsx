import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './policer-range.reducer';
import { IPolicerRange } from 'app/shared/model/policer-range.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPolicerRangeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PolicerRangeDetail = (props: IPolicerRangeDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { policerRangeEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.policerRange.detail.title">PolicerRange</Translate> [<b>{policerRangeEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.policerRange.name">Name</Translate>
            </span>
          </dt>
          <dd>{policerRangeEntity.name}</dd>
          <dt>
            <span id="burst">
              <Translate contentKey="activePortApp.policerRange.burst">Burst</Translate>
            </span>
          </dt>
          <dd>{policerRangeEntity.burst}</dd>
          <dt>
            <span id="bandwidth">
              <Translate contentKey="activePortApp.policerRange.bandwidth">Bandwidth</Translate>
            </span>
          </dt>
          <dd>{policerRangeEntity.bandwidth}</dd>
          <dt>
            <span id="startPolicer">
              <Translate contentKey="activePortApp.policerRange.startPolicer">Start Policer</Translate>
            </span>
          </dt>
          <dd>
            {policerRangeEntity.startPolicer ? (
              <TextFormat value={policerRangeEntity.startPolicer} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="endPolicer">
              <Translate contentKey="activePortApp.policerRange.endPolicer">End Policer</Translate>
            </span>
          </dt>
          <dd>
            {policerRangeEntity.endPolicer ? (
              <TextFormat value={policerRangeEntity.endPolicer} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="rangeType">
              <Translate contentKey="activePortApp.policerRange.rangeType">Range Type</Translate>
            </span>
          </dt>
          <dd>{policerRangeEntity.rangeType}</dd>
        </dl>
        <Button tag={Link} to="/policer-range" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/policer-range/${policerRangeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ policerRange }: IRootState) => ({
  policerRangeEntity: policerRange.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PolicerRangeDetail);
