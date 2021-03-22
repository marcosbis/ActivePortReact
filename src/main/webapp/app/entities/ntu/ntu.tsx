import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './ntu.reducer';
import { INtu } from 'app/shared/model/ntu.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface INtuProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Ntu = (props: INtuProps) => {
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

  const { ntuList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="ntu-heading">
        <Translate contentKey="activePortApp.ntu.home.title">Ntus</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="activePortApp.ntu.home.createLabel">Create new Ntu</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {ntuList && ntuList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="activePortApp.ntu.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('hostId')}>
                  <Translate contentKey="activePortApp.ntu.hostId">Host Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="activePortApp.ntu.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('serialNumber')}>
                  <Translate contentKey="activePortApp.ntu.serialNumber">Serial Number</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('ipAddress')}>
                  <Translate contentKey="activePortApp.ntu.ipAddress">Ip Address</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('companyName')}>
                  <Translate contentKey="activePortApp.ntu.companyName">Company Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('hostName')}>
                  <Translate contentKey="activePortApp.ntu.hostName">Host Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('loIp')}>
                  <Translate contentKey="activePortApp.ntu.loIp">Lo Ip</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('category')}>
                  <Translate contentKey="activePortApp.ntu.category">Category</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('alarmEmailAddresses')}>
                  <Translate contentKey="activePortApp.ntu.alarmEmailAddresses">Alarm Email Addresses</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('metricCollection')}>
                  <Translate contentKey="activePortApp.ntu.metricCollection">Metric Collection</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('securityCollection')}>
                  <Translate contentKey="activePortApp.ntu.securityCollection">Security Collection</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('alarmCollection')}>
                  <Translate contentKey="activePortApp.ntu.alarmCollection">Alarm Collection</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('trendCollection')}>
                  <Translate contentKey="activePortApp.ntu.trendCollection">Trend Collection</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('syslogCollection')}>
                  <Translate contentKey="activePortApp.ntu.syslogCollection">Syslog Collection</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('configBackup')}>
                  <Translate contentKey="activePortApp.ntu.configBackup">Config Backup</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('updateOneconfig')}>
                  <Translate contentKey="activePortApp.ntu.updateOneconfig">Update Oneconfig</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('firmwareVersion')}>
                  <Translate contentKey="activePortApp.ntu.firmwareVersion">Firmware Version</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('runningConfig')}>
                  <Translate contentKey="activePortApp.ntu.runningConfig">Running Config</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('configId')}>
                  <Translate contentKey="activePortApp.ntu.configId">Config Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('endpoint')}>
                  <Translate contentKey="activePortApp.ntu.endpoint">Endpoint</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('restUsername')}>
                  <Translate contentKey="activePortApp.ntu.restUsername">Rest Username</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('restPassword')}>
                  <Translate contentKey="activePortApp.ntu.restPassword">Rest Password</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('restEnabled')}>
                  <Translate contentKey="activePortApp.ntu.restEnabled">Rest Enabled</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('autoRollback')}>
                  <Translate contentKey="activePortApp.ntu.autoRollback">Auto Rollback</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('mode')}>
                  <Translate contentKey="activePortApp.ntu.mode">Mode</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('timeZone')}>
                  <Translate contentKey="activePortApp.ntu.timeZone">Time Zone</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('minRate')}>
                  <Translate contentKey="activePortApp.ntu.minRate">Min Rate</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('maxRate')}>
                  <Translate contentKey="activePortApp.ntu.maxRate">Max Rate</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('defaultRate')}>
                  <Translate contentKey="activePortApp.ntu.defaultRate">Default Rate</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('enableBod')}>
                  <Translate contentKey="activePortApp.ntu.enableBod">Enable Bod</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('burstTime')}>
                  <Translate contentKey="activePortApp.ntu.burstTime">Burst Time</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('secondlinkPort')}>
                  <Translate contentKey="activePortApp.ntu.secondlinkPort">Secondlink Port</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="activePortApp.ntu.deviceConfiguration">Device Configuration</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="activePortApp.ntu.ntutype">Ntutype</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="activePortApp.ntu.ntutype">Ntutype</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {ntuList.map((ntu, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${ntu.id}`} color="link" size="sm">
                      {ntu.id}
                    </Button>
                  </td>
                  <td>{ntu.name}</td>
                  <td>{ntu.hostId}</td>
                  <td>{ntu.description}</td>
                  <td>{ntu.serialNumber}</td>
                  <td>{ntu.ipAddress}</td>
                  <td>{ntu.companyName}</td>
                  <td>{ntu.hostName}</td>
                  <td>{ntu.loIp}</td>
                  <td>{ntu.category}</td>
                  <td>{ntu.alarmEmailAddresses}</td>
                  <td>{ntu.metricCollection ? 'true' : 'false'}</td>
                  <td>{ntu.securityCollection ? 'true' : 'false'}</td>
                  <td>{ntu.alarmCollection ? 'true' : 'false'}</td>
                  <td>{ntu.trendCollection ? 'true' : 'false'}</td>
                  <td>{ntu.syslogCollection ? 'true' : 'false'}</td>
                  <td>{ntu.configBackup ? 'true' : 'false'}</td>
                  <td>{ntu.updateOneconfig ? 'true' : 'false'}</td>
                  <td>{ntu.firmwareVersion}</td>
                  <td>{ntu.runningConfig}</td>
                  <td>{ntu.configId}</td>
                  <td>{ntu.endpoint}</td>
                  <td>{ntu.restUsername}</td>
                  <td>{ntu.restPassword}</td>
                  <td>{ntu.restEnabled ? 'true' : 'false'}</td>
                  <td>{ntu.autoRollback ? 'true' : 'false'}</td>
                  <td>
                    <Translate contentKey={`activePortApp.NtuModeEnum.${ntu.mode}`} />
                  </td>
                  <td>{ntu.timeZone}</td>
                  <td>{ntu.minRate}</td>
                  <td>{ntu.maxRate}</td>
                  <td>{ntu.defaultRate}</td>
                  <td>{ntu.enableBod ? 'true' : 'false'}</td>
                  <td>{ntu.burstTime}</td>
                  <td>{ntu.secondlinkPort}</td>
                  <td>
                    {ntu.deviceConfigurationSerialNumber ? (
                      <Link to={`device-configuration/${ntu.deviceConfigurationId}`}>{ntu.deviceConfigurationSerialNumber}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{ntu.ntutypeModel ? <Link to={`ntu-type/${ntu.ntutypeId}`}>{ntu.ntutypeModel}</Link> : ''}</td>
                  <td>{ntu.ntutypeModel ? <Link to={`ntu-type/${ntu.ntutypeId}`}>{ntu.ntutypeModel}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${ntu.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${ntu.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${ntu.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="activePortApp.ntu.home.notFound">No Ntus found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={ntuList && ntuList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ ntu }: IRootState) => ({
  ntuList: ntu.entities,
  loading: ntu.loading,
  totalItems: ntu.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Ntu);
