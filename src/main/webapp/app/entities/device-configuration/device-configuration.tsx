import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './device-configuration.reducer';
import { IDeviceConfiguration } from 'app/shared/model/device-configuration.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface IDeviceConfigurationProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const DeviceConfiguration = (props: IDeviceConfigurationProps) => {
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

  const { deviceConfigurationList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="device-configuration-heading">
        <Translate contentKey="activePortApp.deviceConfiguration.home.title">Device Configurations</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="activePortApp.deviceConfiguration.home.createLabel">Create new Device Configuration</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {deviceConfigurationList && deviceConfigurationList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('uid')}>
                  <Translate contentKey="activePortApp.deviceConfiguration.uid">Uid</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="activePortApp.deviceConfiguration.description">Description</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('serialNumber')}>
                  <Translate contentKey="activePortApp.deviceConfiguration.serialNumber">Serial Number</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('hostName')}>
                  <Translate contentKey="activePortApp.deviceConfiguration.hostName">Host Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('loIp')}>
                  <Translate contentKey="activePortApp.deviceConfiguration.loIp">Lo Ip</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('firmwareVersion')}>
                  <Translate contentKey="activePortApp.deviceConfiguration.firmwareVersion">Firmware Version</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('endpoint')}>
                  <Translate contentKey="activePortApp.deviceConfiguration.endpoint">Endpoint</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('restUsername')}>
                  <Translate contentKey="activePortApp.deviceConfiguration.restUsername">Rest Username</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('restPassword')}>
                  <Translate contentKey="activePortApp.deviceConfiguration.restPassword">Rest Password</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('restEnabled')}>
                  <Translate contentKey="activePortApp.deviceConfiguration.restEnabled">Rest Enabled</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('mode')}>
                  <Translate contentKey="activePortApp.deviceConfiguration.mode">Mode</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('defaultRate')}>
                  <Translate contentKey="activePortApp.deviceConfiguration.defaultRate">Default Rate</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('subnet')}>
                  <Translate contentKey="activePortApp.deviceConfiguration.subnet">Subnet</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('deviceType')}>
                  <Translate contentKey="activePortApp.deviceConfiguration.deviceType">Device Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('addressSetupType')}>
                  <Translate contentKey="activePortApp.deviceConfiguration.addressSetupType">Address Setup Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="activePortApp.deviceConfiguration.ntutype">Ntutype</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="activePortApp.deviceConfiguration.configuration">Configuration</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="activePortApp.deviceConfiguration.realm">Realm</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {deviceConfigurationList.map((deviceConfiguration, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${deviceConfiguration.id}`} color="link" size="sm">
                      {deviceConfiguration.id}
                    </Button>
                  </td>
                  <td>{deviceConfiguration.uid}</td>
                  <td>{deviceConfiguration.description}</td>
                  <td>{deviceConfiguration.serialNumber}</td>
                  <td>{deviceConfiguration.hostName}</td>
                  <td>{deviceConfiguration.loIp}</td>
                  <td>{deviceConfiguration.firmwareVersion}</td>
                  <td>{deviceConfiguration.endpoint}</td>
                  <td>{deviceConfiguration.restUsername}</td>
                  <td>{deviceConfiguration.restPassword}</td>
                  <td>{deviceConfiguration.restEnabled ? 'true' : 'false'}</td>
                  <td>
                    <Translate contentKey={`activePortApp.NtuModeEnum.${deviceConfiguration.mode}`} />
                  </td>
                  <td>{deviceConfiguration.defaultRate}</td>
                  <td>{deviceConfiguration.subnet}</td>
                  <td>
                    <Translate contentKey={`activePortApp.PortServiceTypeEnum.${deviceConfiguration.deviceType}`} />
                  </td>
                  <td>
                    <Translate contentKey={`activePortApp.AddressSetupTypeEnum.${deviceConfiguration.addressSetupType}`} />
                  </td>
                  <td>
                    {deviceConfiguration.ntutypeModel ? (
                      <Link to={`ntu-type/${deviceConfiguration.ntutypeId}`}>{deviceConfiguration.ntutypeModel}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {deviceConfiguration.configurationName ? (
                      <Link to={`template-configuration/${deviceConfiguration.configurationId}`}>
                        {deviceConfiguration.configurationName}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {deviceConfiguration.realmSubnet ? (
                      <Link to={`realm-ip/${deviceConfiguration.realmId}`}>{deviceConfiguration.realmSubnet}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${deviceConfiguration.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${deviceConfiguration.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${deviceConfiguration.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="activePortApp.deviceConfiguration.home.notFound">No Device Configurations found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={deviceConfigurationList && deviceConfigurationList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ deviceConfiguration }: IRootState) => ({
  deviceConfigurationList: deviceConfiguration.entities,
  loading: deviceConfiguration.loading,
  totalItems: deviceConfiguration.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DeviceConfiguration);
