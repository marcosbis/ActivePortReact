import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './central-switch.reducer';
import { ICentralSwitch } from 'app/shared/model/central-switch.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface ICentralSwitchProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const CentralSwitch = (props: ICentralSwitchProps) => {
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

  const { centralSwitchList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="central-switch-heading">
        <Translate contentKey="activePortApp.centralSwitch.home.title">Central Switches</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="activePortApp.centralSwitch.home.createLabel">Create new Central Switch</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {centralSwitchList && centralSwitchList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="activePortApp.centralSwitch.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('hostId')}>
                  <Translate contentKey="activePortApp.centralSwitch.hostId">Host Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="activePortApp.centralSwitch.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('serialNumber')}>
                  <Translate contentKey="activePortApp.centralSwitch.serialNumber">Serial Number</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('ipAddress')}>
                  <Translate contentKey="activePortApp.centralSwitch.ipAddress">Ip Address</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('companyName')}>
                  <Translate contentKey="activePortApp.centralSwitch.companyName">Company Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('hostName')}>
                  <Translate contentKey="activePortApp.centralSwitch.hostName">Host Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('configBackup')}>
                  <Translate contentKey="activePortApp.centralSwitch.configBackup">Config Backup</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('poolVlanStart')}>
                  <Translate contentKey="activePortApp.centralSwitch.poolVlanStart">Pool Vlan Start</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('poolVlanEnd')}>
                  <Translate contentKey="activePortApp.centralSwitch.poolVlanEnd">Pool Vlan End</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('endpoint')}>
                  <Translate contentKey="activePortApp.centralSwitch.endpoint">Endpoint</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('restUsername')}>
                  <Translate contentKey="activePortApp.centralSwitch.restUsername">Rest Username</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('restPassword')}>
                  <Translate contentKey="activePortApp.centralSwitch.restPassword">Rest Password</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('restEnabled')}>
                  <Translate contentKey="activePortApp.centralSwitch.restEnabled">Rest Enabled</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('autoRollback')}>
                  <Translate contentKey="activePortApp.centralSwitch.autoRollback">Auto Rollback</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('feign')}>
                  <Translate contentKey="activePortApp.centralSwitch.feign">Feign</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="activePortApp.centralSwitch.location">Location</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="activePortApp.centralSwitch.ntutype">Ntutype</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="activePortApp.centralSwitch.ntutype">Ntutype</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {centralSwitchList.map((centralSwitch, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${centralSwitch.id}`} color="link" size="sm">
                      {centralSwitch.id}
                    </Button>
                  </td>
                  <td>{centralSwitch.name}</td>
                  <td>{centralSwitch.hostId}</td>
                  <td>{centralSwitch.description}</td>
                  <td>{centralSwitch.serialNumber}</td>
                  <td>{centralSwitch.ipAddress}</td>
                  <td>{centralSwitch.companyName}</td>
                  <td>{centralSwitch.hostName}</td>
                  <td>{centralSwitch.configBackup ? 'true' : 'false'}</td>
                  <td>{centralSwitch.poolVlanStart}</td>
                  <td>{centralSwitch.poolVlanEnd}</td>
                  <td>{centralSwitch.endpoint}</td>
                  <td>{centralSwitch.restUsername}</td>
                  <td>{centralSwitch.restPassword}</td>
                  <td>{centralSwitch.restEnabled ? 'true' : 'false'}</td>
                  <td>{centralSwitch.autoRollback ? 'true' : 'false'}</td>
                  <td>{centralSwitch.feign ? 'true' : 'false'}</td>
                  <td>
                    {centralSwitch.locationCode ? (
                      <Link to={`location/${centralSwitch.locationId}`}>{centralSwitch.locationCode}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {centralSwitch.ntutypeModel ? <Link to={`ntu-type/${centralSwitch.ntutypeId}`}>{centralSwitch.ntutypeModel}</Link> : ''}
                  </td>
                  <td>
                    {centralSwitch.ntutypeModel ? <Link to={`ntu-type/${centralSwitch.ntutypeId}`}>{centralSwitch.ntutypeModel}</Link> : ''}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${centralSwitch.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${centralSwitch.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${centralSwitch.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="activePortApp.centralSwitch.home.notFound">No Central Switches found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={centralSwitchList && centralSwitchList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ centralSwitch }: IRootState) => ({
  centralSwitchList: centralSwitch.entities,
  loading: centralSwitch.loading,
  totalItems: centralSwitch.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CentralSwitch);
