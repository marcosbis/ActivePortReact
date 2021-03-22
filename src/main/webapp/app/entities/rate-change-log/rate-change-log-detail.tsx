import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './rate-change-log.reducer';
import { IRateChangeLog } from 'app/shared/model/rate-change-log.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRateChangeLogDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RateChangeLogDetail = (props: IRateChangeLogDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { rateChangeLogEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.rateChangeLog.detail.title">RateChangeLog</Translate> [<b>{rateChangeLogEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="previuosRate">
              <Translate contentKey="activePortApp.rateChangeLog.previuosRate">Previuos Rate</Translate>
            </span>
          </dt>
          <dd>{rateChangeLogEntity.previuosRate}</dd>
          <dt>
            <span id="rate">
              <Translate contentKey="activePortApp.rateChangeLog.rate">Rate</Translate>
            </span>
          </dt>
          <dd>{rateChangeLogEntity.rate}</dd>
          <dt>
            <Translate contentKey="activePortApp.rateChangeLog.userService">User Service</Translate>
          </dt>
          <dd>{rateChangeLogEntity.userServiceName ? rateChangeLogEntity.userServiceName : ''}</dd>
        </dl>
        <Button tag={Link} to="/rate-change-log" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/rate-change-log/${rateChangeLogEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ rateChangeLog }: IRootState) => ({
  rateChangeLogEntity: rateChangeLog.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RateChangeLogDetail);
