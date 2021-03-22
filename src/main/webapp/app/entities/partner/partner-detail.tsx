import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './partner.reducer';
import { IPartner } from 'app/shared/model/partner.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPartnerDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PartnerDetail = (props: IPartnerDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { partnerEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.partner.detail.title">Partner</Translate> [<b>{partnerEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.partner.name">Name</Translate>
            </span>
          </dt>
          <dd>{partnerEntity.name}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="activePortApp.partner.email">Email</Translate>
            </span>
          </dt>
          <dd>{partnerEntity.email}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.partner.description">Description</Translate>
            </span>
          </dt>
          <dd>{partnerEntity.description}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="activePortApp.partner.type">Type</Translate>
            </span>
          </dt>
          <dd>{partnerEntity.type}</dd>
          <dt>
            <span id="connection">
              <Translate contentKey="activePortApp.partner.connection">Connection</Translate>
            </span>
          </dt>
          <dd>{partnerEntity.connection}</dd>
          <dt>
            <span id="portType">
              <Translate contentKey="activePortApp.partner.portType">Port Type</Translate>
            </span>
          </dt>
          <dd>{partnerEntity.portType}</dd>
          <dt>
            <span id="port">
              <Translate contentKey="activePortApp.partner.port">Port</Translate>
            </span>
          </dt>
          <dd>{partnerEntity.port}</dd>
          <dt>
            <span id="market">
              <Translate contentKey="activePortApp.partner.market">Market</Translate>
            </span>
          </dt>
          <dd>{partnerEntity.market}</dd>
          <dt>
            <span id="locationId">
              <Translate contentKey="activePortApp.partner.locationId">Location Id</Translate>
            </span>
          </dt>
          <dd>{partnerEntity.locationId}</dd>
          <dt>
            <span id="vxcpermitted">
              <Translate contentKey="activePortApp.partner.vxcpermitted">Vxcpermitted</Translate>
            </span>
          </dt>
          <dd>{partnerEntity.vxcpermitted ? 'true' : 'false'}</dd>
          <dt>
            <span id="locationIx">
              <Translate contentKey="activePortApp.partner.locationIx">Location Ix</Translate>
            </span>
          </dt>
          <dd>{partnerEntity.locationIx}</dd>
          <dt>
            <span id="vlanPort">
              <Translate contentKey="activePortApp.partner.vlanPort">Vlan Port</Translate>
            </span>
          </dt>
          <dd>{partnerEntity.vlanPort}</dd>
          <dt>
            <Translate contentKey="activePortApp.partner.centralSwitch">Central Switch</Translate>
          </dt>
          <dd>{partnerEntity.centralSwitchName ? partnerEntity.centralSwitchName : ''}</dd>
          <dt>
            <Translate contentKey="activePortApp.partner.providerCode">Provider Code</Translate>
          </dt>
          <dd>{partnerEntity.providerCodeName ? partnerEntity.providerCodeName : ''}</dd>
        </dl>
        <Button tag={Link} to="/partner" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/partner/${partnerEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ partner }: IRootState) => ({
  partnerEntity: partner.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PartnerDetail);
