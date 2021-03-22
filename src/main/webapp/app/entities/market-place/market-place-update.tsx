import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './market-place.reducer';
import { IMarketPlace } from 'app/shared/model/market-place.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IMarketPlaceUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MarketPlaceUpdate = (props: IMarketPlaceUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { marketPlaceEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/market-place' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...marketPlaceEntity,
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
          <h2 id="activePortApp.marketPlace.home.createOrEditLabel">
            <Translate contentKey="activePortApp.marketPlace.home.createOrEditLabel">Create or edit a MarketPlace</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : marketPlaceEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="market-place-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="market-place-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="companyUidLabel" for="market-place-companyUid">
                  <Translate contentKey="activePortApp.marketPlace.companyUid">Company Uid</Translate>
                </Label>
                <AvField id="market-place-companyUid" type="text" name="companyUid" />
              </AvGroup>
              <AvGroup>
                <Label id="companyNameLabel" for="market-place-companyName">
                  <Translate contentKey="activePortApp.marketPlace.companyName">Company Name</Translate>
                </Label>
                <AvField id="market-place-companyName" type="text" name="companyName" />
              </AvGroup>
              <AvGroup>
                <Label id="partnerUidLabel" for="market-place-partnerUid">
                  <Translate contentKey="activePortApp.marketPlace.partnerUid">Partner Uid</Translate>
                </Label>
                <AvField id="market-place-partnerUid" type="text" name="partnerUid" />
              </AvGroup>
              <AvGroup>
                <Label id="accountIdLabel" for="market-place-accountId">
                  <Translate contentKey="activePortApp.marketPlace.accountId">Account Id</Translate>
                </Label>
                <AvField id="market-place-accountId" type="text" name="accountId" />
              </AvGroup>
              <AvGroup>
                <Label id="titleLabel" for="market-place-title">
                  <Translate contentKey="activePortApp.marketPlace.title">Title</Translate>
                </Label>
                <AvField id="market-place-title" type="text" name="title" />
              </AvGroup>
              <AvGroup>
                <Label id="locationIdLabel" for="market-place-locationId">
                  <Translate contentKey="activePortApp.marketPlace.locationId">Location Id</Translate>
                </Label>
                <AvField id="market-place-locationId" type="string" className="form-control" name="locationId" />
              </AvGroup>
              <AvGroup>
                <Label id="speedLabel" for="market-place-speed">
                  <Translate contentKey="activePortApp.marketPlace.speed">Speed</Translate>
                </Label>
                <AvField id="market-place-speed" type="string" className="form-control" name="speed" />
              </AvGroup>
              <AvGroup>
                <Label id="rankLabel" for="market-place-rank">
                  <Translate contentKey="activePortApp.marketPlace.rank">Rank</Translate>
                </Label>
                <AvField id="market-place-rank" type="string" className="form-control" name="rank" />
              </AvGroup>
              <AvGroup>
                <Label id="bandwidthLabel" for="market-place-bandwidth">
                  <Translate contentKey="activePortApp.marketPlace.bandwidth">Bandwidth</Translate>
                </Label>
                <AvField id="market-place-bandwidth" type="string" className="form-control" name="bandwidth" />
              </AvGroup>
              <AvGroup>
                <Label id="locationNameLabel" for="market-place-locationName">
                  <Translate contentKey="activePortApp.marketPlace.locationName">Location Name</Translate>
                </Label>
                <AvField id="market-place-locationName" type="text" name="locationName" />
              </AvGroup>
              <AvGroup>
                <Label id="locationMetroLabel" for="market-place-locationMetro">
                  <Translate contentKey="activePortApp.marketPlace.locationMetro">Location Metro</Translate>
                </Label>
                <AvField id="market-place-locationMetro" type="text" name="locationMetro" />
              </AvGroup>
              <AvGroup>
                <Label id="portIdLabel" for="market-place-portId">
                  <Translate contentKey="activePortApp.marketPlace.portId">Port Id</Translate>
                </Label>
                <AvField id="market-place-portId" type="string" className="form-control" name="portId" />
              </AvGroup>
              <AvGroup>
                <Label id="typeLabel" for="market-place-type">
                  <Translate contentKey="activePortApp.marketPlace.type">Type</Translate>
                </Label>
                <AvInput
                  id="market-place-type"
                  type="select"
                  className="form-control"
                  name="type"
                  value={(!isNew && marketPlaceEntity.type) || 'AWS'}
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
                <Label id="nameLabel" for="market-place-name">
                  <Translate contentKey="activePortApp.marketPlace.name">Name</Translate>
                </Label>
                <AvField id="market-place-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="market-place-description">
                  <Translate contentKey="activePortApp.marketPlace.description">Description</Translate>
                </Label>
                <AvField id="market-place-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="serviceKeyLabel" for="market-place-serviceKey">
                  <Translate contentKey="activePortApp.marketPlace.serviceKey">Service Key</Translate>
                </Label>
                <AvField id="market-place-serviceKey" type="text" name="serviceKey" />
              </AvGroup>
              <AvGroup>
                <Label id="rateLimitLabel" for="market-place-rateLimit">
                  <Translate contentKey="activePortApp.marketPlace.rateLimit">Rate Limit</Translate>
                </Label>
                <AvField id="market-place-rateLimit" type="string" className="form-control" name="rateLimit" />
              </AvGroup>
              <AvGroup>
                <Label id="priceLabel" for="market-place-price">
                  <Translate contentKey="activePortApp.marketPlace.price">Price</Translate>
                </Label>
                <AvField id="market-place-price" type="text" name="price" />
              </AvGroup>
              <AvGroup>
                <Label id="uuidLabel" for="market-place-uuid">
                  <Translate contentKey="activePortApp.marketPlace.uuid">Uuid</Translate>
                </Label>
                <AvField id="market-place-uuid" type="text" name="uuid" />
              </AvGroup>
              <AvGroup>
                <Label id="productUidLabel" for="market-place-productUid">
                  <Translate contentKey="activePortApp.marketPlace.productUid">Product Uid</Translate>
                </Label>
                <AvField id="market-place-productUid" type="text" name="productUid" />
              </AvGroup>
              <AvGroup>
                <Label id="reTaggedVlanIdLabel" for="market-place-reTaggedVlanId">
                  <Translate contentKey="activePortApp.marketPlace.reTaggedVlanId">Re Tagged Vlan Id</Translate>
                </Label>
                <AvField id="market-place-reTaggedVlanId" type="string" className="form-control" name="reTaggedVlanId" />
              </AvGroup>
              <AvGroup>
                <Label id="provisioningStatusLabel" for="market-place-provisioningStatus">
                  <Translate contentKey="activePortApp.marketPlace.provisioningStatus">Provisioning Status</Translate>
                </Label>
                <AvInput
                  id="market-place-provisioningStatus"
                  type="select"
                  className="form-control"
                  name="provisioningStatus"
                  value={(!isNew && marketPlaceEntity.provisioningStatus) || 'NEW'}
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
                <Label id="vlanIdALabel" for="market-place-vlanIdA">
                  <Translate contentKey="activePortApp.marketPlace.vlanIdA">Vlan Id A</Translate>
                </Label>
                <AvField id="market-place-vlanIdA" type="string" className="form-control" name="vlanIdA" />
              </AvGroup>
              <AvGroup>
                <Label id="vlanIdBLabel" for="market-place-vlanIdB">
                  <Translate contentKey="activePortApp.marketPlace.vlanIdB">Vlan Id B</Translate>
                </Label>
                <AvField id="market-place-vlanIdB" type="string" className="form-control" name="vlanIdB" />
              </AvGroup>
              <AvGroup>
                <Label id="vlanIdSLabel" for="market-place-vlanIdS">
                  <Translate contentKey="activePortApp.marketPlace.vlanIdS">Vlan Id S</Translate>
                </Label>
                <AvField id="market-place-vlanIdS" type="string" className="form-control" name="vlanIdS" />
              </AvGroup>
              <AvGroup>
                <Label id="ntuIdLabel" for="market-place-ntuId">
                  <Translate contentKey="activePortApp.marketPlace.ntuId">Ntu Id</Translate>
                </Label>
                <AvField id="market-place-ntuId" type="string" className="form-control" name="ntuId" />
              </AvGroup>
              <AvGroup>
                <Label id="userIpLabel" for="market-place-userIp">
                  <Translate contentKey="activePortApp.marketPlace.userIp">User Ip</Translate>
                </Label>
                <AvField id="market-place-userIp" type="text" name="userIp" />
              </AvGroup>
              <AvGroup>
                <Label id="firewallPriceLabel" for="market-place-firewallPrice">
                  <Translate contentKey="activePortApp.marketPlace.firewallPrice">Firewall Price</Translate>
                </Label>
                <AvField id="market-place-firewallPrice" type="text" name="firewallPrice" />
              </AvGroup>
              <AvGroup>
                <Label id="firewallStatusLabel" for="market-place-firewallStatus">
                  <Translate contentKey="activePortApp.marketPlace.firewallStatus">Firewall Status</Translate>
                </Label>
                <AvInput
                  id="market-place-firewallStatus"
                  type="select"
                  className="form-control"
                  name="firewallStatus"
                  value={(!isNew && marketPlaceEntity.firewallStatus) || 'DESIGN'}
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
                <Label id="stateLabel" for="market-place-state">
                  <Translate contentKey="activePortApp.marketPlace.state">State</Translate>
                </Label>
                <AvInput
                  id="market-place-state"
                  type="select"
                  className="form-control"
                  name="state"
                  value={(!isNew && marketPlaceEntity.state) || 'DESIGN'}
                >
                  <option value="DESIGN">{translate('activePortApp.ServiceStateEnum.DESIGN')}</option>
                  <option value="ACTIVE">{translate('activePortApp.ServiceStateEnum.ACTIVE')}</option>
                  <option value="ON_HOLD">{translate('activePortApp.ServiceStateEnum.ON_HOLD')}</option>
                  <option value="DISABLED">{translate('activePortApp.ServiceStateEnum.DISABLED')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="bEndProductUidLabel" for="market-place-bEndProductUid">
                  <Translate contentKey="activePortApp.marketPlace.bEndProductUid">B End Product Uid</Translate>
                </Label>
                <AvField id="market-place-bEndProductUid" type="text" name="bEndProductUid" />
              </AvGroup>
              <AvGroup>
                <Label id="partnerTypeLabel" for="market-place-partnerType">
                  <Translate contentKey="activePortApp.marketPlace.partnerType">Partner Type</Translate>
                </Label>
                <AvInput
                  id="market-place-partnerType"
                  type="select"
                  className="form-control"
                  name="partnerType"
                  value={(!isNew && marketPlaceEntity.partnerType) || 'MEGAPORT'}
                >
                  <option value="MEGAPORT">{translate('activePortApp.PartnerTypeEnum.MEGAPORT')}</option>
                  <option value="ISP">{translate('activePortApp.PartnerTypeEnum.ISP')}</option>
                  <option value="ZETTA">{translate('activePortApp.PartnerTypeEnum.ZETTA')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="circuitTypeLabel" for="market-place-circuitType">
                  <Translate contentKey="activePortApp.marketPlace.circuitType">Circuit Type</Translate>
                </Label>
                <AvInput
                  id="market-place-circuitType"
                  type="select"
                  className="form-control"
                  name="circuitType"
                  value={(!isNew && marketPlaceEntity.circuitType) || 'VLAN'}
                >
                  <option value="VLAN">{translate('activePortApp.CircuitTypeEnum.VLAN')}</option>
                  <option value="CIRCUIT">{translate('activePortApp.CircuitTypeEnum.CIRCUIT')}</option>
                  <option value="ROUTING_ROUTE">{translate('activePortApp.CircuitTypeEnum.ROUTING_ROUTE')}</option>
                  <option value="ROUTING_NAT">{translate('activePortApp.CircuitTypeEnum.ROUTING_NAT')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="userSubnetLabel" for="market-place-userSubnet">
                  <Translate contentKey="activePortApp.marketPlace.userSubnet">User Subnet</Translate>
                </Label>
                <AvField id="market-place-userSubnet" type="text" name="userSubnet" />
              </AvGroup>
              <AvGroup>
                <Label id="myGwLabel" for="market-place-myGw">
                  <Translate contentKey="activePortApp.marketPlace.myGw">My Gw</Translate>
                </Label>
                <AvField id="market-place-myGw" type="text" name="myGw" />
              </AvGroup>
              <AvGroup>
                <Label id="activePortGwLabel" for="market-place-activePortGw">
                  <Translate contentKey="activePortApp.marketPlace.activePortGw">Active Port Gw</Translate>
                </Label>
                <AvField id="market-place-activePortGw" type="text" name="activePortGw" />
              </AvGroup>
              <AvGroup>
                <Label id="awsAuthKeyLabel" for="market-place-awsAuthKey">
                  <Translate contentKey="activePortApp.marketPlace.awsAuthKey">Aws Auth Key</Translate>
                </Label>
                <AvField id="market-place-awsAuthKey" type="text" name="awsAuthKey" />
              </AvGroup>
              <AvGroup>
                <Label id="awsIpLabel" for="market-place-awsIp">
                  <Translate contentKey="activePortApp.marketPlace.awsIp">Aws Ip</Translate>
                </Label>
                <AvField id="market-place-awsIp" type="text" name="awsIp" />
              </AvGroup>
              <AvGroup>
                <Label id="asnLabel" for="market-place-asn">
                  <Translate contentKey="activePortApp.marketPlace.asn">Asn</Translate>
                </Label>
                <AvField id="market-place-asn" type="string" className="form-control" name="asn" />
              </AvGroup>
              <AvGroup>
                <Label id="peerAsnLabel" for="market-place-peerAsn">
                  <Translate contentKey="activePortApp.marketPlace.peerAsn">Peer Asn</Translate>
                </Label>
                <AvField id="market-place-peerAsn" type="string" className="form-control" name="peerAsn" />
              </AvGroup>
              <AvGroup>
                <Label id="vxcTypeLabel" for="market-place-vxcType">
                  <Translate contentKey="activePortApp.marketPlace.vxcType">Vxc Type</Translate>
                </Label>
                <AvInput
                  id="market-place-vxcType"
                  type="select"
                  className="form-control"
                  name="vxcType"
                  value={(!isNew && marketPlaceEntity.vxcType) || 'PRIMARY'}
                >
                  <option value="PRIMARY">{translate('activePortApp.VXCTypeEnum.PRIMARY')}</option>
                  <option value="SECONDARY">{translate('activePortApp.VXCTypeEnum.SECONDARY')}</option>
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/market-place" replace color="info">
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
  marketPlaceEntity: storeState.marketPlace.entity,
  loading: storeState.marketPlace.loading,
  updating: storeState.marketPlace.updating,
  updateSuccess: storeState.marketPlace.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MarketPlaceUpdate);
