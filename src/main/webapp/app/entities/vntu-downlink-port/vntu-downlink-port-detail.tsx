import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './vntu-downlink-port.reducer';
import { IVntuDownlinkPort } from 'app/shared/model/vntu-downlink-port.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IVntuDownlinkPortDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const VntuDownlinkPortDetail = (props: IVntuDownlinkPortDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { vntuDownlinkPortEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.vntuDownlinkPort.detail.title">VntuDownlinkPort</Translate> [
          <b>{vntuDownlinkPortEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.vntuDownlinkPort.name">Name</Translate>
            </span>
          </dt>
          <dd>{vntuDownlinkPortEntity.name}</dd>
          <dt>
            <span id="uid">
              <Translate contentKey="activePortApp.vntuDownlinkPort.uid">Uid</Translate>
            </span>
          </dt>
          <dd>{vntuDownlinkPortEntity.uid}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.vntuDownlinkPort.description">Description</Translate>
            </span>
          </dt>
          <dd>{vntuDownlinkPortEntity.description}</dd>
          <dt>
            <span id="assignedOrgName">
              <Translate contentKey="activePortApp.vntuDownlinkPort.assignedOrgName">Assigned Org Name</Translate>
            </span>
          </dt>
          <dd>{vntuDownlinkPortEntity.assignedOrgName}</dd>
          <dt>
            <span id="assignedTenantName">
              <Translate contentKey="activePortApp.vntuDownlinkPort.assignedTenantName">Assigned Tenant Name</Translate>
            </span>
          </dt>
          <dd>{vntuDownlinkPortEntity.assignedTenantName}</dd>
          <dt>
            <span id="assignedOrgId">
              <Translate contentKey="activePortApp.vntuDownlinkPort.assignedOrgId">Assigned Org Id</Translate>
            </span>
          </dt>
          <dd>{vntuDownlinkPortEntity.assignedOrgId}</dd>
          <dt>
            <span id="assignedTenantId">
              <Translate contentKey="activePortApp.vntuDownlinkPort.assignedTenantId">Assigned Tenant Id</Translate>
            </span>
          </dt>
          <dd>{vntuDownlinkPortEntity.assignedTenantId}</dd>
          <dt>
            <span id="assignedVntuId">
              <Translate contentKey="activePortApp.vntuDownlinkPort.assignedVntuId">Assigned Vntu Id</Translate>
            </span>
          </dt>
          <dd>{vntuDownlinkPortEntity.assignedVntuId}</dd>
          <dt>
            <span id="assignedVntuName">
              <Translate contentKey="activePortApp.vntuDownlinkPort.assignedVntuName">Assigned Vntu Name</Translate>
            </span>
          </dt>
          <dd>{vntuDownlinkPortEntity.assignedVntuName}</dd>
          <dt>
            <Translate contentKey="activePortApp.vntuDownlinkPort.centralSwitch">Central Switch</Translate>
          </dt>
          <dd>{vntuDownlinkPortEntity.centralSwitchName ? vntuDownlinkPortEntity.centralSwitchName : ''}</dd>
        </dl>
        <Button tag={Link} to="/vntu-downlink-port" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/vntu-downlink-port/${vntuDownlinkPortEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ vntuDownlinkPort }: IRootState) => ({
  vntuDownlinkPortEntity: vntuDownlinkPort.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(VntuDownlinkPortDetail);
