import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './provider-log.reducer';
import { IProviderLog } from 'app/shared/model/provider-log.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProviderLogDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProviderLogDetail = (props: IProviderLogDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { providerLogEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.providerLog.detail.title">ProviderLog</Translate> [<b>{providerLogEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="orgId">
              <Translate contentKey="activePortApp.providerLog.orgId">Org Id</Translate>
            </span>
          </dt>
          <dd>{providerLogEntity.orgId}</dd>
          <dt>
            <span id="tenantId">
              <Translate contentKey="activePortApp.providerLog.tenantId">Tenant Id</Translate>
            </span>
          </dt>
          <dd>{providerLogEntity.tenantId}</dd>
          <dt>
            <span id="serviceId">
              <Translate contentKey="activePortApp.providerLog.serviceId">Service Id</Translate>
            </span>
          </dt>
          <dd>{providerLogEntity.serviceId}</dd>
          <dt>
            <span id="log">
              <Translate contentKey="activePortApp.providerLog.log">Log</Translate>
            </span>
          </dt>
          <dd>{providerLogEntity.log}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="activePortApp.providerLog.type">Type</Translate>
            </span>
          </dt>
          <dd>{providerLogEntity.type}</dd>
          <dt>
            <span id="jobUid">
              <Translate contentKey="activePortApp.providerLog.jobUid">Job Uid</Translate>
            </span>
          </dt>
          <dd>{providerLogEntity.jobUid}</dd>
          <dt>
            <span id="ntuId">
              <Translate contentKey="activePortApp.providerLog.ntuId">Ntu Id</Translate>
            </span>
          </dt>
          <dd>{providerLogEntity.ntuId}</dd>
          <dt>
            <span id="request">
              <Translate contentKey="activePortApp.providerLog.request">Request</Translate>
            </span>
          </dt>
          <dd>{providerLogEntity.request}</dd>
          <dt>
            <span id="responseLog">
              <Translate contentKey="activePortApp.providerLog.responseLog">Response Log</Translate>
            </span>
          </dt>
          <dd>{providerLogEntity.responseLog}</dd>
        </dl>
        <Button tag={Link} to="/provider-log" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/provider-log/${providerLogEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ providerLog }: IRootState) => ({
  providerLogEntity: providerLog.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProviderLogDetail);
