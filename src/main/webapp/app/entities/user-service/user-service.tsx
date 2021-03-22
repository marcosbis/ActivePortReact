import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './user-service.reducer';
import { IUserService } from 'app/shared/model/user-service.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface IUserServiceProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const UserService = (props: IUserServiceProps) => {
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

  const { userServiceList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="user-service-heading">
        <Translate contentKey="activePortApp.userService.home.title">User Services</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="activePortApp.userService.home.createLabel">Create new User Service</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {userServiceList && userServiceList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('type')}>
                  <Translate contentKey="activePortApp.userService.type">Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="activePortApp.userService.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="activePortApp.userService.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('serviceKey')}>
                  <Translate contentKey="activePortApp.userService.serviceKey">Service Key</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('rateLimit')}>
                  <Translate contentKey="activePortApp.userService.rateLimit">Rate Limit</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('price')}>
                  <Translate contentKey="activePortApp.userService.price">Price</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('uuid')}>
                  <Translate contentKey="activePortApp.userService.uuid">Uuid</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('productUid')}>
                  <Translate contentKey="activePortApp.userService.productUid">Product Uid</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('reTaggedVlanId')}>
                  <Translate contentKey="activePortApp.userService.reTaggedVlanId">Re Tagged Vlan Id</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('provisioningStatus')}>
                  <Translate contentKey="activePortApp.userService.provisioningStatus">Provisioning Status</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('vlanIdA')}>
                  <Translate contentKey="activePortApp.userService.vlanIdA">Vlan Id A</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('vlanIdB')}>
                  <Translate contentKey="activePortApp.userService.vlanIdB">Vlan Id B</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('vlanIdS')}>
                  <Translate contentKey="activePortApp.userService.vlanIdS">Vlan Id S</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('ntuId')}>
                  <Translate contentKey="activePortApp.userService.ntuId">Ntu Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('userIp')}>
                  <Translate contentKey="activePortApp.userService.userIp">User Ip</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('firewallPrice')}>
                  <Translate contentKey="activePortApp.userService.firewallPrice">Firewall Price</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('firewallStatus')}>
                  <Translate contentKey="activePortApp.userService.firewallStatus">Firewall Status</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('state')}>
                  <Translate contentKey="activePortApp.userService.state">State</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('bEndProductUid')}>
                  <Translate contentKey="activePortApp.userService.bEndProductUid">B End Product Uid</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('partnerType')}>
                  <Translate contentKey="activePortApp.userService.partnerType">Partner Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('circuitType')}>
                  <Translate contentKey="activePortApp.userService.circuitType">Circuit Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('userSubnet')}>
                  <Translate contentKey="activePortApp.userService.userSubnet">User Subnet</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('myGw')}>
                  <Translate contentKey="activePortApp.userService.myGw">My Gw</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('activePortGw')}>
                  <Translate contentKey="activePortApp.userService.activePortGw">Active Port Gw</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('awsAuthKey')}>
                  <Translate contentKey="activePortApp.userService.awsAuthKey">Aws Auth Key</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('awsIp')}>
                  <Translate contentKey="activePortApp.userService.awsIp">Aws Ip</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('asn')}>
                  <Translate contentKey="activePortApp.userService.asn">Asn</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('peerAsn')}>
                  <Translate contentKey="activePortApp.userService.peerAsn">Peer Asn</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('vxcType')}>
                  <Translate contentKey="activePortApp.userService.vxcType">Vxc Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="activePortApp.userService.ntuPort">Ntu Port</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {userServiceList.map((userService, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${userService.id}`} color="link" size="sm">
                      {userService.id}
                    </Button>
                  </td>
                  <td>
                    <Translate contentKey={`activePortApp.ConnectTypeEnum.${userService.type}`} />
                  </td>
                  <td>{userService.name}</td>
                  <td>{userService.description}</td>
                  <td>{userService.serviceKey}</td>
                  <td>{userService.rateLimit}</td>
                  <td>{userService.price}</td>
                  <td>{userService.uuid}</td>
                  <td>{userService.productUid}</td>
                  <td>{userService.reTaggedVlanId}</td>
                  <td>
                    <Translate contentKey={`activePortApp.ProvisioningStatusEnum.${userService.provisioningStatus}`} />
                  </td>
                  <td>{userService.vlanIdA}</td>
                  <td>{userService.vlanIdB}</td>
                  <td>{userService.vlanIdS}</td>
                  <td>{userService.ntuId}</td>
                  <td>{userService.userIp}</td>
                  <td>{userService.firewallPrice}</td>
                  <td>
                    <Translate contentKey={`activePortApp.FirewallStatusEnum.${userService.firewallStatus}`} />
                  </td>
                  <td>
                    <Translate contentKey={`activePortApp.ServiceStateEnum.${userService.state}`} />
                  </td>
                  <td>{userService.bEndProductUid}</td>
                  <td>
                    <Translate contentKey={`activePortApp.PartnerTypeEnum.${userService.partnerType}`} />
                  </td>
                  <td>
                    <Translate contentKey={`activePortApp.CircuitTypeEnum.${userService.circuitType}`} />
                  </td>
                  <td>{userService.userSubnet}</td>
                  <td>{userService.myGw}</td>
                  <td>{userService.activePortGw}</td>
                  <td>{userService.awsAuthKey}</td>
                  <td>{userService.awsIp}</td>
                  <td>{userService.asn}</td>
                  <td>{userService.peerAsn}</td>
                  <td>
                    <Translate contentKey={`activePortApp.VXCTypeEnum.${userService.vxcType}`} />
                  </td>
                  <td>{userService.ntuPortName ? <Link to={`ntu-port/${userService.ntuPortId}`}>{userService.ntuPortName}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${userService.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${userService.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${userService.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="activePortApp.userService.home.notFound">No User Services found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={userServiceList && userServiceList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ userService }: IRootState) => ({
  userServiceList: userService.entities,
  loading: userService.loading,
  totalItems: userService.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UserService);
