import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './user-service.reducer';
import { IUserService } from 'app/shared/model/user-service.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUserServiceDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const UserServiceDetail = (props: IUserServiceDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { userServiceEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.userService.detail.title">UserService</Translate> [<b>{userServiceEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="type">
              <Translate contentKey="activePortApp.userService.type">Type</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.type}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.userService.name">Name</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.userService.description">Description</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.description}</dd>
          <dt>
            <span id="serviceKey">
              <Translate contentKey="activePortApp.userService.serviceKey">Service Key</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.serviceKey}</dd>
          <dt>
            <span id="rateLimit">
              <Translate contentKey="activePortApp.userService.rateLimit">Rate Limit</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.rateLimit}</dd>
          <dt>
            <span id="price">
              <Translate contentKey="activePortApp.userService.price">Price</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.price}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="activePortApp.userService.uuid">Uuid</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.uuid}</dd>
          <dt>
            <span id="productUid">
              <Translate contentKey="activePortApp.userService.productUid">Product Uid</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.productUid}</dd>
          <dt>
            <span id="reTaggedVlanId">
              <Translate contentKey="activePortApp.userService.reTaggedVlanId">Re Tagged Vlan Id</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.reTaggedVlanId}</dd>
          <dt>
            <span id="provisioningStatus">
              <Translate contentKey="activePortApp.userService.provisioningStatus">Provisioning Status</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.provisioningStatus}</dd>
          <dt>
            <span id="vlanIdA">
              <Translate contentKey="activePortApp.userService.vlanIdA">Vlan Id A</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.vlanIdA}</dd>
          <dt>
            <span id="vlanIdB">
              <Translate contentKey="activePortApp.userService.vlanIdB">Vlan Id B</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.vlanIdB}</dd>
          <dt>
            <span id="vlanIdS">
              <Translate contentKey="activePortApp.userService.vlanIdS">Vlan Id S</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.vlanIdS}</dd>
          <dt>
            <span id="ntuId">
              <Translate contentKey="activePortApp.userService.ntuId">Ntu Id</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.ntuId}</dd>
          <dt>
            <span id="userIp">
              <Translate contentKey="activePortApp.userService.userIp">User Ip</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.userIp}</dd>
          <dt>
            <span id="firewallPrice">
              <Translate contentKey="activePortApp.userService.firewallPrice">Firewall Price</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.firewallPrice}</dd>
          <dt>
            <span id="firewallStatus">
              <Translate contentKey="activePortApp.userService.firewallStatus">Firewall Status</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.firewallStatus}</dd>
          <dt>
            <span id="state">
              <Translate contentKey="activePortApp.userService.state">State</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.state}</dd>
          <dt>
            <span id="bEndProductUid">
              <Translate contentKey="activePortApp.userService.bEndProductUid">B End Product Uid</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.bEndProductUid}</dd>
          <dt>
            <span id="partnerType">
              <Translate contentKey="activePortApp.userService.partnerType">Partner Type</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.partnerType}</dd>
          <dt>
            <span id="circuitType">
              <Translate contentKey="activePortApp.userService.circuitType">Circuit Type</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.circuitType}</dd>
          <dt>
            <span id="userSubnet">
              <Translate contentKey="activePortApp.userService.userSubnet">User Subnet</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.userSubnet}</dd>
          <dt>
            <span id="myGw">
              <Translate contentKey="activePortApp.userService.myGw">My Gw</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.myGw}</dd>
          <dt>
            <span id="activePortGw">
              <Translate contentKey="activePortApp.userService.activePortGw">Active Port Gw</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.activePortGw}</dd>
          <dt>
            <span id="awsAuthKey">
              <Translate contentKey="activePortApp.userService.awsAuthKey">Aws Auth Key</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.awsAuthKey}</dd>
          <dt>
            <span id="awsIp">
              <Translate contentKey="activePortApp.userService.awsIp">Aws Ip</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.awsIp}</dd>
          <dt>
            <span id="asn">
              <Translate contentKey="activePortApp.userService.asn">Asn</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.asn}</dd>
          <dt>
            <span id="peerAsn">
              <Translate contentKey="activePortApp.userService.peerAsn">Peer Asn</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.peerAsn}</dd>
          <dt>
            <span id="vxcType">
              <Translate contentKey="activePortApp.userService.vxcType">Vxc Type</Translate>
            </span>
          </dt>
          <dd>{userServiceEntity.vxcType}</dd>
          <dt>
            <Translate contentKey="activePortApp.userService.ntuPort">Ntu Port</Translate>
          </dt>
          <dd>{userServiceEntity.ntuPortName ? userServiceEntity.ntuPortName : ''}</dd>
        </dl>
        <Button tag={Link} to="/user-service" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/user-service/${userServiceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ userService }: IRootState) => ({
  userServiceEntity: userService.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UserServiceDetail);
