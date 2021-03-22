import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './market-place.reducer';
import { IMarketPlace } from 'app/shared/model/market-place.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMarketPlaceDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MarketPlaceDetail = (props: IMarketPlaceDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { marketPlaceEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.marketPlace.detail.title">MarketPlace</Translate> [<b>{marketPlaceEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="companyUid">
              <Translate contentKey="activePortApp.marketPlace.companyUid">Company Uid</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.companyUid}</dd>
          <dt>
            <span id="companyName">
              <Translate contentKey="activePortApp.marketPlace.companyName">Company Name</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.companyName}</dd>
          <dt>
            <span id="partnerUid">
              <Translate contentKey="activePortApp.marketPlace.partnerUid">Partner Uid</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.partnerUid}</dd>
          <dt>
            <span id="accountId">
              <Translate contentKey="activePortApp.marketPlace.accountId">Account Id</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.accountId}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="activePortApp.marketPlace.title">Title</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.title}</dd>
          <dt>
            <span id="locationId">
              <Translate contentKey="activePortApp.marketPlace.locationId">Location Id</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.locationId}</dd>
          <dt>
            <span id="speed">
              <Translate contentKey="activePortApp.marketPlace.speed">Speed</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.speed}</dd>
          <dt>
            <span id="rank">
              <Translate contentKey="activePortApp.marketPlace.rank">Rank</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.rank}</dd>
          <dt>
            <span id="bandwidth">
              <Translate contentKey="activePortApp.marketPlace.bandwidth">Bandwidth</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.bandwidth}</dd>
          <dt>
            <span id="locationName">
              <Translate contentKey="activePortApp.marketPlace.locationName">Location Name</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.locationName}</dd>
          <dt>
            <span id="locationMetro">
              <Translate contentKey="activePortApp.marketPlace.locationMetro">Location Metro</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.locationMetro}</dd>
          <dt>
            <span id="portId">
              <Translate contentKey="activePortApp.marketPlace.portId">Port Id</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.portId}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="activePortApp.marketPlace.type">Type</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.type}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.marketPlace.name">Name</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.marketPlace.description">Description</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.description}</dd>
          <dt>
            <span id="serviceKey">
              <Translate contentKey="activePortApp.marketPlace.serviceKey">Service Key</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.serviceKey}</dd>
          <dt>
            <span id="rateLimit">
              <Translate contentKey="activePortApp.marketPlace.rateLimit">Rate Limit</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.rateLimit}</dd>
          <dt>
            <span id="price">
              <Translate contentKey="activePortApp.marketPlace.price">Price</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.price}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="activePortApp.marketPlace.uuid">Uuid</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.uuid}</dd>
          <dt>
            <span id="productUid">
              <Translate contentKey="activePortApp.marketPlace.productUid">Product Uid</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.productUid}</dd>
          <dt>
            <span id="reTaggedVlanId">
              <Translate contentKey="activePortApp.marketPlace.reTaggedVlanId">Re Tagged Vlan Id</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.reTaggedVlanId}</dd>
          <dt>
            <span id="provisioningStatus">
              <Translate contentKey="activePortApp.marketPlace.provisioningStatus">Provisioning Status</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.provisioningStatus}</dd>
          <dt>
            <span id="vlanIdA">
              <Translate contentKey="activePortApp.marketPlace.vlanIdA">Vlan Id A</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.vlanIdA}</dd>
          <dt>
            <span id="vlanIdB">
              <Translate contentKey="activePortApp.marketPlace.vlanIdB">Vlan Id B</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.vlanIdB}</dd>
          <dt>
            <span id="vlanIdS">
              <Translate contentKey="activePortApp.marketPlace.vlanIdS">Vlan Id S</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.vlanIdS}</dd>
          <dt>
            <span id="ntuId">
              <Translate contentKey="activePortApp.marketPlace.ntuId">Ntu Id</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.ntuId}</dd>
          <dt>
            <span id="userIp">
              <Translate contentKey="activePortApp.marketPlace.userIp">User Ip</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.userIp}</dd>
          <dt>
            <span id="firewallPrice">
              <Translate contentKey="activePortApp.marketPlace.firewallPrice">Firewall Price</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.firewallPrice}</dd>
          <dt>
            <span id="firewallStatus">
              <Translate contentKey="activePortApp.marketPlace.firewallStatus">Firewall Status</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.firewallStatus}</dd>
          <dt>
            <span id="state">
              <Translate contentKey="activePortApp.marketPlace.state">State</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.state}</dd>
          <dt>
            <span id="bEndProductUid">
              <Translate contentKey="activePortApp.marketPlace.bEndProductUid">B End Product Uid</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.bEndProductUid}</dd>
          <dt>
            <span id="partnerType">
              <Translate contentKey="activePortApp.marketPlace.partnerType">Partner Type</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.partnerType}</dd>
          <dt>
            <span id="circuitType">
              <Translate contentKey="activePortApp.marketPlace.circuitType">Circuit Type</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.circuitType}</dd>
          <dt>
            <span id="userSubnet">
              <Translate contentKey="activePortApp.marketPlace.userSubnet">User Subnet</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.userSubnet}</dd>
          <dt>
            <span id="myGw">
              <Translate contentKey="activePortApp.marketPlace.myGw">My Gw</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.myGw}</dd>
          <dt>
            <span id="activePortGw">
              <Translate contentKey="activePortApp.marketPlace.activePortGw">Active Port Gw</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.activePortGw}</dd>
          <dt>
            <span id="awsAuthKey">
              <Translate contentKey="activePortApp.marketPlace.awsAuthKey">Aws Auth Key</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.awsAuthKey}</dd>
          <dt>
            <span id="awsIp">
              <Translate contentKey="activePortApp.marketPlace.awsIp">Aws Ip</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.awsIp}</dd>
          <dt>
            <span id="asn">
              <Translate contentKey="activePortApp.marketPlace.asn">Asn</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.asn}</dd>
          <dt>
            <span id="peerAsn">
              <Translate contentKey="activePortApp.marketPlace.peerAsn">Peer Asn</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.peerAsn}</dd>
          <dt>
            <span id="vxcType">
              <Translate contentKey="activePortApp.marketPlace.vxcType">Vxc Type</Translate>
            </span>
          </dt>
          <dd>{marketPlaceEntity.vxcType}</dd>
        </dl>
        <Button tag={Link} to="/market-place" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/market-place/${marketPlaceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ marketPlace }: IRootState) => ({
  marketPlaceEntity: marketPlace.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MarketPlaceDetail);
