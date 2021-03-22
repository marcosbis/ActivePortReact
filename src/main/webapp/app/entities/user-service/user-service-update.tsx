import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { INtuPort } from 'app/shared/model/ntu-port.model';
import { getEntities as getNtuPorts } from 'app/entities/ntu-port/ntu-port.reducer';
import { getEntity, updateEntity, createEntity, reset } from './user-service.reducer';
import { IUserService } from 'app/shared/model/user-service.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IUserServiceUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const UserServiceUpdate = (props: IUserServiceUpdateProps) => {
  const [ntuPortId, setNtuPortId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { userServiceEntity, ntuPorts, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/user-service' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getNtuPorts();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...userServiceEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="activePortApp.userService.home.createOrEditLabel">
            <Translate contentKey="activePortApp.userService.home.createOrEditLabel">Create or edit a UserService</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : userServiceEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="user-service-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="user-service-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="typeLabel" for="user-service-type">
                  <Translate contentKey="activePortApp.userService.type">Type</Translate>
                </Label>
                <AvInput
                  id="user-service-type"
                  type="select"
                  className="form-control"
                  name="type"
                  value={(!isNew && userServiceEntity.type) || 'AWS'}
                >
                  <option value="AWS">{translate('activePortApp.ConnectTypeEnum.AWS')}</option>
                  <option value="IX">{translate('activePortApp.ConnectTypeEnum.IX')}</option>
                  <option value="VXC">{translate('activePortApp.ConnectTypeEnum.VXC')}</option>
                  <option value="AZURE">{translate('activePortApp.ConnectTypeEnum.AZURE')}</option>
                  <option value="AZURE_PRIVATE">{translate('activePortApp.ConnectTypeEnum.AZURE_PRIVATE')}</option>
                  <option value="AZURE_PUBLIC">{translate('activePortApp.ConnectTypeEnum.AZURE_PUBLIC')}</option>
                  <option value="AZURE_MICROSOFT">{translate('activePortApp.ConnectTypeEnum.AZURE_MICROSOFT')}</option>
                  <option value="OFFICE">{translate('activePortApp.ConnectTypeEnum.OFFICE')}</option>
                  <option value="INTERNET">{translate('activePortApp.ConnectTypeEnum.INTERNET')}</option>
                  <option value="ZETTA_INTERNET">{translate('activePortApp.ConnectTypeEnum.ZETTA_INTERNET')}</option>
                  <option value="DNS_FILTER">{translate('activePortApp.ConnectTypeEnum.DNS_FILTER')}</option>
                  <option value="ZETTA_SERVICE">{translate('activePortApp.ConnectTypeEnum.ZETTA_SERVICE')}</option>
                  <option value="INTERNET_LAYER3">{translate('activePortApp.ConnectTypeEnum.INTERNET_LAYER3')}</option>
                  <option value="MCON">{translate('activePortApp.ConnectTypeEnum.MCON')}</option>
                  <option value="ALIBABA">{translate('activePortApp.ConnectTypeEnum.ALIBABA')}</option>
                  <option value="IBM">{translate('activePortApp.ConnectTypeEnum.IBM')}</option>
                  <option value="GOOGLE">{translate('activePortApp.ConnectTypeEnum.GOOGLE')}</option>
                  <option value="ORACLE">{translate('activePortApp.ConnectTypeEnum.ORACLE')}</option>
                  <option value="XC">{translate('activePortApp.ConnectTypeEnum.XC')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="nameLabel" for="user-service-name">
                  <Translate contentKey="activePortApp.userService.name">Name</Translate>
                </Label>
                <AvField id="user-service-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="user-service-description">
                  <Translate contentKey="activePortApp.userService.description">Description</Translate>
                </Label>
                <AvField id="user-service-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="serviceKeyLabel" for="user-service-serviceKey">
                  <Translate contentKey="activePortApp.userService.serviceKey">Service Key</Translate>
                </Label>
                <AvField id="user-service-serviceKey" type="text" name="serviceKey" />
              </AvGroup>
              <AvGroup>
                <Label id="rateLimitLabel" for="user-service-rateLimit">
                  <Translate contentKey="activePortApp.userService.rateLimit">Rate Limit</Translate>
                </Label>
                <AvField id="user-service-rateLimit" type="string" className="form-control" name="rateLimit" />
              </AvGroup>
              <AvGroup>
                <Label id="priceLabel" for="user-service-price">
                  <Translate contentKey="activePortApp.userService.price">Price</Translate>
                </Label>
                <AvField id="user-service-price" type="text" name="price" />
              </AvGroup>
              <AvGroup>
                <Label id="uuidLabel" for="user-service-uuid">
                  <Translate contentKey="activePortApp.userService.uuid">Uuid</Translate>
                </Label>
                <AvField id="user-service-uuid" type="text" name="uuid" />
              </AvGroup>
              <AvGroup>
                <Label id="productUidLabel" for="user-service-productUid">
                  <Translate contentKey="activePortApp.userService.productUid">Product Uid</Translate>
                </Label>
                <AvField id="user-service-productUid" type="text" name="productUid" />
              </AvGroup>
              <AvGroup>
                <Label id="reTaggedVlanIdLabel" for="user-service-reTaggedVlanId">
                  <Translate contentKey="activePortApp.userService.reTaggedVlanId">Re Tagged Vlan Id</Translate>
                </Label>
                <AvField id="user-service-reTaggedVlanId" type="string" className="form-control" name="reTaggedVlanId" />
              </AvGroup>
              <AvGroup>
                <Label id="provisioningStatusLabel" for="user-service-provisioningStatus">
                  <Translate contentKey="activePortApp.userService.provisioningStatus">Provisioning Status</Translate>
                </Label>
                <AvInput
                  id="user-service-provisioningStatus"
                  type="select"
                  className="form-control"
                  name="provisioningStatus"
                  value={(!isNew && userServiceEntity.provisioningStatus) || 'NEW'}
                >
                  <option value="NEW">{translate('activePortApp.ProvisioningStatusEnum.NEW')}</option>
                  <option value="DESIGN">{translate('activePortApp.ProvisioningStatusEnum.DESIGN')}</option>
                  <option value="IN_PROGRESS">{translate('activePortApp.ProvisioningStatusEnum.IN_PROGRESS')}</option>
                  <option value="ON_HOLD">{translate('activePortApp.ProvisioningStatusEnum.ON_HOLD')}</option>
                  <option value="DEPLOYABLE">{translate('activePortApp.ProvisioningStatusEnum.DEPLOYABLE')}</option>
                  <option value="CONFIGURED">{translate('activePortApp.ProvisioningStatusEnum.CONFIGURED')}</option>
                  <option value="LIVE">{translate('activePortApp.ProvisioningStatusEnum.LIVE')}</option>
                  <option value="CANCELLED">{translate('activePortApp.ProvisioningStatusEnum.CANCELLED')}</option>
                  <option value="CANCELLED_PARENT">{translate('activePortApp.ProvisioningStatusEnum.CANCELLED_PARENT')}</option>
                  <option value="SLEEP">{translate('activePortApp.ProvisioningStatusEnum.SLEEP')}</option>
                  <option value="FAILED">{translate('activePortApp.ProvisioningStatusEnum.FAILED')}</option>
                  <option value="DECOMMISSIONED">{translate('activePortApp.ProvisioningStatusEnum.DECOMMISSIONED')}</option>
                  <option value="DISABLED">{translate('activePortApp.ProvisioningStatusEnum.DISABLED')}</option>
                  <option value="PRE_APPROVED">{translate('activePortApp.ProvisioningStatusEnum.PRE_APPROVED')}</option>
                  <option value="APPROVED">{translate('activePortApp.ProvisioningStatusEnum.APPROVED')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="vlanIdALabel" for="user-service-vlanIdA">
                  <Translate contentKey="activePortApp.userService.vlanIdA">Vlan Id A</Translate>
                </Label>
                <AvField id="user-service-vlanIdA" type="string" className="form-control" name="vlanIdA" />
              </AvGroup>
              <AvGroup>
                <Label id="vlanIdBLabel" for="user-service-vlanIdB">
                  <Translate contentKey="activePortApp.userService.vlanIdB">Vlan Id B</Translate>
                </Label>
                <AvField id="user-service-vlanIdB" type="string" className="form-control" name="vlanIdB" />
              </AvGroup>
              <AvGroup>
                <Label id="vlanIdSLabel" for="user-service-vlanIdS">
                  <Translate contentKey="activePortApp.userService.vlanIdS">Vlan Id S</Translate>
                </Label>
                <AvField id="user-service-vlanIdS" type="string" className="form-control" name="vlanIdS" />
              </AvGroup>
              <AvGroup>
                <Label id="ntuIdLabel" for="user-service-ntuId">
                  <Translate contentKey="activePortApp.userService.ntuId">Ntu Id</Translate>
                </Label>
                <AvField id="user-service-ntuId" type="string" className="form-control" name="ntuId" />
              </AvGroup>
              <AvGroup>
                <Label id="userIpLabel" for="user-service-userIp">
                  <Translate contentKey="activePortApp.userService.userIp">User Ip</Translate>
                </Label>
                <AvField id="user-service-userIp" type="text" name="userIp" />
              </AvGroup>
              <AvGroup>
                <Label id="firewallPriceLabel" for="user-service-firewallPrice">
                  <Translate contentKey="activePortApp.userService.firewallPrice">Firewall Price</Translate>
                </Label>
                <AvField id="user-service-firewallPrice" type="text" name="firewallPrice" />
              </AvGroup>
              <AvGroup>
                <Label id="firewallStatusLabel" for="user-service-firewallStatus">
                  <Translate contentKey="activePortApp.userService.firewallStatus">Firewall Status</Translate>
                </Label>
                <AvInput
                  id="user-service-firewallStatus"
                  type="select"
                  className="form-control"
                  name="firewallStatus"
                  value={(!isNew && userServiceEntity.firewallStatus) || 'DESIGN'}
                >
                  <option value="DESIGN">{translate('activePortApp.FirewallStatusEnum.DESIGN')}</option>
                  <option value="IN_PROGRESS">{translate('activePortApp.FirewallStatusEnum.IN_PROGRESS')}</option>
                  <option value="ENABLED">{translate('activePortApp.FirewallStatusEnum.ENABLED')}</option>
                  <option value="SLEEP">{translate('activePortApp.FirewallStatusEnum.SLEEP')}</option>
                  <option value="FAILED">{translate('activePortApp.FirewallStatusEnum.FAILED')}</option>
                  <option value="DISABLED">{translate('activePortApp.FirewallStatusEnum.DISABLED')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="stateLabel" for="user-service-state">
                  <Translate contentKey="activePortApp.userService.state">State</Translate>
                </Label>
                <AvInput
                  id="user-service-state"
                  type="select"
                  className="form-control"
                  name="state"
                  value={(!isNew && userServiceEntity.state) || 'DESIGN'}
                >
                  <option value="DESIGN">{translate('activePortApp.ServiceStateEnum.DESIGN')}</option>
                  <option value="ACTIVE">{translate('activePortApp.ServiceStateEnum.ACTIVE')}</option>
                  <option value="ON_HOLD">{translate('activePortApp.ServiceStateEnum.ON_HOLD')}</option>
                  <option value="DISABLED">{translate('activePortApp.ServiceStateEnum.DISABLED')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="bEndProductUidLabel" for="user-service-bEndProductUid">
                  <Translate contentKey="activePortApp.userService.bEndProductUid">B End Product Uid</Translate>
                </Label>
                <AvField id="user-service-bEndProductUid" type="text" name="bEndProductUid" />
              </AvGroup>
              <AvGroup>
                <Label id="partnerTypeLabel" for="user-service-partnerType">
                  <Translate contentKey="activePortApp.userService.partnerType">Partner Type</Translate>
                </Label>
                <AvInput
                  id="user-service-partnerType"
                  type="select"
                  className="form-control"
                  name="partnerType"
                  value={(!isNew && userServiceEntity.partnerType) || 'MEGAPORT'}
                >
                  <option value="MEGAPORT">{translate('activePortApp.PartnerTypeEnum.MEGAPORT')}</option>
                  <option value="ISP">{translate('activePortApp.PartnerTypeEnum.ISP')}</option>
                  <option value="ZETTA">{translate('activePortApp.PartnerTypeEnum.ZETTA')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="circuitTypeLabel" for="user-service-circuitType">
                  <Translate contentKey="activePortApp.userService.circuitType">Circuit Type</Translate>
                </Label>
                <AvInput
                  id="user-service-circuitType"
                  type="select"
                  className="form-control"
                  name="circuitType"
                  value={(!isNew && userServiceEntity.circuitType) || 'VLAN'}
                >
                  <option value="VLAN">{translate('activePortApp.CircuitTypeEnum.VLAN')}</option>
                  <option value="CIRCUIT">{translate('activePortApp.CircuitTypeEnum.CIRCUIT')}</option>
                  <option value="ROUTING_ROUTE">{translate('activePortApp.CircuitTypeEnum.ROUTING_ROUTE')}</option>
                  <option value="ROUTING_NAT">{translate('activePortApp.CircuitTypeEnum.ROUTING_NAT')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="userSubnetLabel" for="user-service-userSubnet">
                  <Translate contentKey="activePortApp.userService.userSubnet">User Subnet</Translate>
                </Label>
                <AvField id="user-service-userSubnet" type="text" name="userSubnet" />
              </AvGroup>
              <AvGroup>
                <Label id="myGwLabel" for="user-service-myGw">
                  <Translate contentKey="activePortApp.userService.myGw">My Gw</Translate>
                </Label>
                <AvField id="user-service-myGw" type="text" name="myGw" />
              </AvGroup>
              <AvGroup>
                <Label id="activePortGwLabel" for="user-service-activePortGw">
                  <Translate contentKey="activePortApp.userService.activePortGw">Active Port Gw</Translate>
                </Label>
                <AvField id="user-service-activePortGw" type="text" name="activePortGw" />
              </AvGroup>
              <AvGroup>
                <Label id="awsAuthKeyLabel" for="user-service-awsAuthKey">
                  <Translate contentKey="activePortApp.userService.awsAuthKey">Aws Auth Key</Translate>
                </Label>
                <AvField id="user-service-awsAuthKey" type="text" name="awsAuthKey" />
              </AvGroup>
              <AvGroup>
                <Label id="awsIpLabel" for="user-service-awsIp">
                  <Translate contentKey="activePortApp.userService.awsIp">Aws Ip</Translate>
                </Label>
                <AvField id="user-service-awsIp" type="text" name="awsIp" />
              </AvGroup>
              <AvGroup>
                <Label id="asnLabel" for="user-service-asn">
                  <Translate contentKey="activePortApp.userService.asn">Asn</Translate>
                </Label>
                <AvField id="user-service-asn" type="string" className="form-control" name="asn" />
              </AvGroup>
              <AvGroup>
                <Label id="peerAsnLabel" for="user-service-peerAsn">
                  <Translate contentKey="activePortApp.userService.peerAsn">Peer Asn</Translate>
                </Label>
                <AvField id="user-service-peerAsn" type="string" className="form-control" name="peerAsn" />
              </AvGroup>
              <AvGroup>
                <Label id="vxcTypeLabel" for="user-service-vxcType">
                  <Translate contentKey="activePortApp.userService.vxcType">Vxc Type</Translate>
                </Label>
                <AvInput
                  id="user-service-vxcType"
                  type="select"
                  className="form-control"
                  name="vxcType"
                  value={(!isNew && userServiceEntity.vxcType) || 'PRIMARY'}
                >
                  <option value="PRIMARY">{translate('activePortApp.VXCTypeEnum.PRIMARY')}</option>
                  <option value="SECONDARY">{translate('activePortApp.VXCTypeEnum.SECONDARY')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="user-service-ntuPort">
                  <Translate contentKey="activePortApp.userService.ntuPort">Ntu Port</Translate>
                </Label>
                <AvInput id="user-service-ntuPort" type="select" className="form-control" name="ntuPortId">
                  <option value="" key="0" />
                  {ntuPorts
                    ? ntuPorts.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/user-service" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  ntuPorts: storeState.ntuPort.entities,
  userServiceEntity: storeState.userService.entity,
  loading: storeState.userService.loading,
  updating: storeState.userService.updating,
  updateSuccess: storeState.userService.updateSuccess,
});

const mapDispatchToProps = {
  getNtuPorts,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UserServiceUpdate);
