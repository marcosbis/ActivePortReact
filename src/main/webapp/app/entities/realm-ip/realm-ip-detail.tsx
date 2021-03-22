import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './realm-ip.reducer';
import { IRealmIp } from 'app/shared/model/realm-ip.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRealmIpDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RealmIpDetail = (props: IRealmIpDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { realmIpEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.realmIp.detail.title">RealmIp</Translate> [<b>{realmIpEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="subnet">
              <Translate contentKey="activePortApp.realmIp.subnet">Subnet</Translate>
            </span>
          </dt>
          <dd>{realmIpEntity.subnet}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.realmIp.name">Name</Translate>
            </span>
          </dt>
          <dd>{realmIpEntity.name}</dd>
          <dt>
            <span id="desciption">
              <Translate contentKey="activePortApp.realmIp.desciption">Desciption</Translate>
            </span>
          </dt>
          <dd>{realmIpEntity.desciption}</dd>
          <dt>
            <span id="mask">
              <Translate contentKey="activePortApp.realmIp.mask">Mask</Translate>
            </span>
          </dt>
          <dd>{realmIpEntity.mask}</dd>
          <dt>
            <span id="subnetSize">
              <Translate contentKey="activePortApp.realmIp.subnetSize">Subnet Size</Translate>
            </span>
          </dt>
          <dd>{realmIpEntity.subnetSize}</dd>
          <dt>
            <span id="firstIp">
              <Translate contentKey="activePortApp.realmIp.firstIp">First Ip</Translate>
            </span>
          </dt>
          <dd>{realmIpEntity.firstIp}</dd>
          <dt>
            <span id="lastIp">
              <Translate contentKey="activePortApp.realmIp.lastIp">Last Ip</Translate>
            </span>
          </dt>
          <dd>{realmIpEntity.lastIp}</dd>
          <dt>
            <span id="broadcast">
              <Translate contentKey="activePortApp.realmIp.broadcast">Broadcast</Translate>
            </span>
          </dt>
          <dd>{realmIpEntity.broadcast}</dd>
          <dt>
            <span id="cir">
              <Translate contentKey="activePortApp.realmIp.cir">Cir</Translate>
            </span>
          </dt>
          <dd>{realmIpEntity.cir}</dd>
          <dt>
            <span id="ipsecGateway">
              <Translate contentKey="activePortApp.realmIp.ipsecGateway">Ipsec Gateway</Translate>
            </span>
          </dt>
          <dd>{realmIpEntity.ipsecGateway}</dd>
          <dt>
            <Translate contentKey="activePortApp.realmIp.location">Location</Translate>
          </dt>
          <dd>{realmIpEntity.locationCode ? realmIpEntity.locationCode : ''}</dd>
        </dl>
        <Button tag={Link} to="/realm-ip" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/realm-ip/${realmIpEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ realmIp }: IRootState) => ({
  realmIpEntity: realmIp.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RealmIpDetail);
