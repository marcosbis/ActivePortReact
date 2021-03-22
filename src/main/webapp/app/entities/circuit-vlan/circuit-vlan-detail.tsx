import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './circuit-vlan.reducer';
import { ICircuitVlan } from 'app/shared/model/circuit-vlan.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICircuitVlanDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CircuitVlanDetail = (props: ICircuitVlanDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { circuitVlanEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.circuitVlan.detail.title">CircuitVlan</Translate> [<b>{circuitVlanEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="zone">
              <Translate contentKey="activePortApp.circuitVlan.zone">Zone</Translate>
            </span>
          </dt>
          <dd>{circuitVlanEntity.zone}</dd>
          <dt>
            <span id="serviceKey">
              <Translate contentKey="activePortApp.circuitVlan.serviceKey">Service Key</Translate>
            </span>
          </dt>
          <dd>{circuitVlanEntity.serviceKey}</dd>
          <dt>
            <span id="vlanId">
              <Translate contentKey="activePortApp.circuitVlan.vlanId">Vlan Id</Translate>
            </span>
          </dt>
          <dd>{circuitVlanEntity.vlanId}</dd>
          <dt>
            <span id="rd">
              <Translate contentKey="activePortApp.circuitVlan.rd">Rd</Translate>
            </span>
          </dt>
          <dd>{circuitVlanEntity.rd}</dd>
          <dt>
            <span id="serviceId">
              <Translate contentKey="activePortApp.circuitVlan.serviceId">Service Id</Translate>
            </span>
          </dt>
          <dd>{circuitVlanEntity.serviceId}</dd>
          <dt>
            <span id="tenantName">
              <Translate contentKey="activePortApp.circuitVlan.tenantName">Tenant Name</Translate>
            </span>
          </dt>
          <dd>{circuitVlanEntity.tenantName}</dd>
          <dt>
            <span id="childServiceId">
              <Translate contentKey="activePortApp.circuitVlan.childServiceId">Child Service Id</Translate>
            </span>
          </dt>
          <dd>{circuitVlanEntity.childServiceId}</dd>
          <dt>
            <span id="childNtuId">
              <Translate contentKey="activePortApp.circuitVlan.childNtuId">Child Ntu Id</Translate>
            </span>
          </dt>
          <dd>{circuitVlanEntity.childNtuId}</dd>
          <dt>
            <span id="realmIp">
              <Translate contentKey="activePortApp.circuitVlan.realmIp">Realm Ip</Translate>
            </span>
          </dt>
          <dd>{circuitVlanEntity.realmIp}</dd>
          <dt>
            <span id="internetType">
              <Translate contentKey="activePortApp.circuitVlan.internetType">Internet Type</Translate>
            </span>
          </dt>
          <dd>{circuitVlanEntity.internetType}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="activePortApp.circuitVlan.type">Type</Translate>
            </span>
          </dt>
          <dd>{circuitVlanEntity.type}</dd>
          <dt>
            <Translate contentKey="activePortApp.circuitVlan.serviceConfiguration">Service Configuration</Translate>
          </dt>
          <dd>{circuitVlanEntity.serviceConfigurationName ? circuitVlanEntity.serviceConfigurationName : ''}</dd>
        </dl>
        <Button tag={Link} to="/circuit-vlan" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/circuit-vlan/${circuitVlanEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ circuitVlan }: IRootState) => ({
  circuitVlanEntity: circuitVlan.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CircuitVlanDetail);
