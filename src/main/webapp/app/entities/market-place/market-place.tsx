import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './market-place.reducer';
import { IMarketPlace } from 'app/shared/model/market-place.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface IMarketPlaceProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const MarketPlace = (props: IMarketPlaceProps) => {
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE), props.location.search)
  );

  const getAllEntities = () => {
    props.getEntities(paginationState.activePage - 1, paginationState.itemsPerPage, `${paginationState.sort},${paginationState.order}`);
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (props.location.search !== endURL) {
      props.history.push(`${props.location.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(props.location.search);
    const page = params.get('page');
    const sort = params.get('sort');
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [props.location.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === 'asc' ? 'desc' : 'asc',
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const { marketPlaceList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="market-place-heading">
        <Translate contentKey="activePortApp.marketPlace.home.title">Market Places</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="activePortApp.marketPlace.home.createLabel">Create new Market Place</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {marketPlaceList && marketPlaceList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('companyUid')}>
                  <Translate contentKey="activePortApp.marketPlace.companyUid">Company Uid</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('companyName')}>
                  <Translate contentKey="activePortApp.marketPlace.companyName">Company Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('partnerUid')}>
                  <Translate contentKey="activePortApp.marketPlace.partnerUid">Partner Uid</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('accountId')}>
                  <Translate contentKey="activePortApp.marketPlace.accountId">Account Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('title')}>
                  <Translate contentKey="activePortApp.marketPlace.title">Title</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('locationId')}>
                  <Translate contentKey="activePortApp.marketPlace.locationId">Location Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('speed')}>
                  <Translate contentKey="activePortApp.marketPlace.speed">Speed</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('rank')}>
                  <Translate contentKey="activePortApp.marketPlace.rank">Rank</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('bandwidth')}>
                  <Translate contentKey="activePortApp.marketPlace.bandwidth">Bandwidth</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('locationName')}>
                  <Translate contentKey="activePortApp.marketPlace.locationName">Location Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('locationMetro')}>
                  <Translate contentKey="activePortApp.marketPlace.locationMetro">Location Metro</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('portId')}>
                  <Translate contentKey="activePortApp.marketPlace.portId">Port Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('type')}>
                  <Translate contentKey="activePortApp.marketPlace.type">Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="activePortApp.marketPlace.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="activePortApp.marketPlace.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('serviceKey')}>
                  <Translate contentKey="activePortApp.marketPlace.serviceKey">Service Key</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('rateLimit')}>
                  <Translate contentKey="activePortApp.marketPlace.rateLimit">Rate Limit</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('price')}>
                  <Translate contentKey="activePortApp.marketPlace.price">Price</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('uuid')}>
                  <Translate contentKey="activePortApp.marketPlace.uuid">Uuid</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('productUid')}>
                  <Translate contentKey="activePortApp.marketPlace.productUid">Product Uid</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('reTaggedVlanId')}>
                  <Translate contentKey="activePortApp.marketPlace.reTaggedVlanId">Re Tagged Vlan Id</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('provisioningStatus')}>
                  <Translate contentKey="activePortApp.marketPlace.provisioningStatus">Provisioning Status</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('vlanIdA')}>
                  <Translate contentKey="activePortApp.marketPlace.vlanIdA">Vlan Id A</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('vlanIdB')}>
                  <Translate contentKey="activePortApp.marketPlace.vlanIdB">Vlan Id B</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('vlanIdS')}>
                  <Translate contentKey="activePortApp.marketPlace.vlanIdS">Vlan Id S</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('ntuId')}>
                  <Translate contentKey="activePortApp.marketPlace.ntuId">Ntu Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('userIp')}>
                  <Translate contentKey="activePortApp.marketPlace.userIp">User Ip</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('firewallPrice')}>
                  <Translate contentKey="activePortApp.marketPlace.firewallPrice">Firewall Price</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('firewallStatus')}>
                  <Translate contentKey="activePortApp.marketPlace.firewallStatus">Firewall Status</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('state')}>
                  <Translate contentKey="activePortApp.marketPlace.state">State</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('bEndProductUid')}>
                  <Translate contentKey="activePortApp.marketPlace.bEndProductUid">B End Product Uid</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('partnerType')}>
                  <Translate contentKey="activePortApp.marketPlace.partnerType">Partner Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('circuitType')}>
                  <Translate contentKey="activePortApp.marketPlace.circuitType">Circuit Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('userSubnet')}>
                  <Translate contentKey="activePortApp.marketPlace.userSubnet">User Subnet</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('myGw')}>
                  <Translate contentKey="activePortApp.marketPlace.myGw">My Gw</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('activePortGw')}>
                  <Translate contentKey="activePortApp.marketPlace.activePortGw">Active Port Gw</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('awsAuthKey')}>
                  <Translate contentKey="activePortApp.marketPlace.awsAuthKey">Aws Auth Key</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('awsIp')}>
                  <Translate contentKey="activePortApp.marketPlace.awsIp">Aws Ip</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('asn')}>
                  <Translate contentKey="activePortApp.marketPlace.asn">Asn</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('peerAsn')}>
                  <Translate contentKey="activePortApp.marketPlace.peerAsn">Peer Asn</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('vxcType')}>
                  <Translate contentKey="activePortApp.marketPlace.vxcType">Vxc Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {marketPlaceList.map((marketPlace, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${marketPlace.id}`} color="link" size="sm">
                      {marketPlace.id}
                    </Button>
                  </td>
                  <td>{marketPlace.companyUid}</td>
                  <td>{marketPlace.companyName}</td>
                  <td>{marketPlace.partnerUid}</td>
                  <td>{marketPlace.accountId}</td>
                  <td>{marketPlace.title}</td>
                  <td>{marketPlace.locationId}</td>
                  <td>{marketPlace.speed}</td>
                  <td>{marketPlace.rank}</td>
                  <td>{marketPlace.bandwidth}</td>
                  <td>{marketPlace.locationName}</td>
                  <td>{marketPlace.locationMetro}</td>
                  <td>{marketPlace.portId}</td>
                  <td>
                    <Translate contentKey={`activePortApp.ConnectTypeEnum.${marketPlace.type}`} />
                  </td>
                  <td>{marketPlace.name}</td>
                  <td>{marketPlace.description}</td>
                  <td>{marketPlace.serviceKey}</td>
                  <td>{marketPlace.rateLimit}</td>
                  <td>{marketPlace.price}</td>
                  <td>{marketPlace.uuid}</td>
                  <td>{marketPlace.productUid}</td>
                  <td>{marketPlace.reTaggedVlanId}</td>
                  <td>
                    <Translate contentKey={`activePortApp.ProvisioningStatusEnum.${marketPlace.provisioningStatus}`} />
                  </td>
                  <td>{marketPlace.vlanIdA}</td>
                  <td>{marketPlace.vlanIdB}</td>
                  <td>{marketPlace.vlanIdS}</td>
                  <td>{marketPlace.ntuId}</td>
                  <td>{marketPlace.userIp}</td>
                  <td>{marketPlace.firewallPrice}</td>
                  <td>
                    <Translate contentKey={`activePortApp.FirewallStatusEnum.${marketPlace.firewallStatus}`} />
                  </td>
                  <td>
                    <Translate contentKey={`activePortApp.ServiceStateEnum.${marketPlace.state}`} />
                  </td>
                  <td>{marketPlace.bEndProductUid}</td>
                  <td>
                    <Translate contentKey={`activePortApp.PartnerTypeEnum.${marketPlace.partnerType}`} />
                  </td>
                  <td>
                    <Translate contentKey={`activePortApp.CircuitTypeEnum.${marketPlace.circuitType}`} />
                  </td>
                  <td>{marketPlace.userSubnet}</td>
                  <td>{marketPlace.myGw}</td>
                  <td>{marketPlace.activePortGw}</td>
                  <td>{marketPlace.awsAuthKey}</td>
                  <td>{marketPlace.awsIp}</td>
                  <td>{marketPlace.asn}</td>
                  <td>{marketPlace.peerAsn}</td>
                  <td>
                    <Translate contentKey={`activePortApp.VXCTypeEnum.${marketPlace.vxcType}`} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${marketPlace.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${marketPlace.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${marketPlace.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="activePortApp.marketPlace.home.notFound">No Market Places found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={marketPlaceList && marketPlaceList.length > 0 ? '' : 'd-none'}>
          <Row className="justify-content-center">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </Row>
          <Row className="justify-content-center">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={props.totalItems}
            />
          </Row>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

const mapStateToProps = ({ marketPlace }: IRootState) => ({
  marketPlaceList: marketPlace.entities,
  loading: marketPlace.loading,
  totalItems: marketPlace.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MarketPlace);
