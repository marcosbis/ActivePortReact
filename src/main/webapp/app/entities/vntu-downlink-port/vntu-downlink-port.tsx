import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './vntu-downlink-port.reducer';
import { IVntuDownlinkPort } from 'app/shared/model/vntu-downlink-port.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface IVntuDownlinkPortProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const VntuDownlinkPort = (props: IVntuDownlinkPortProps) => {
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

  const { vntuDownlinkPortList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="vntu-downlink-port-heading">
        <Translate contentKey="activePortApp.vntuDownlinkPort.home.title">Vntu Downlink Ports</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="activePortApp.vntuDownlinkPort.home.createLabel">Create new Vntu Downlink Port</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {vntuDownlinkPortList && vntuDownlinkPortList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="activePortApp.vntuDownlinkPort.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('uid')}>
                  <Translate contentKey="activePortApp.vntuDownlinkPort.uid">Uid</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="activePortApp.vntuDownlinkPort.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('assignedOrgName')}>
                  <Translate contentKey="activePortApp.vntuDownlinkPort.assignedOrgName">Assigned Org Name</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('assignedTenantName')}>
                  <Translate contentKey="activePortApp.vntuDownlinkPort.assignedTenantName">Assigned Tenant Name</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('assignedOrgId')}>
                  <Translate contentKey="activePortApp.vntuDownlinkPort.assignedOrgId">Assigned Org Id</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('assignedTenantId')}>
                  <Translate contentKey="activePortApp.vntuDownlinkPort.assignedTenantId">Assigned Tenant Id</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('assignedVntuId')}>
                  <Translate contentKey="activePortApp.vntuDownlinkPort.assignedVntuId">Assigned Vntu Id</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('assignedVntuName')}>
                  <Translate contentKey="activePortApp.vntuDownlinkPort.assignedVntuName">Assigned Vntu Name</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="activePortApp.vntuDownlinkPort.centralSwitch">Central Switch</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {vntuDownlinkPortList.map((vntuDownlinkPort, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${vntuDownlinkPort.id}`} color="link" size="sm">
                      {vntuDownlinkPort.id}
                    </Button>
                  </td>
                  <td>{vntuDownlinkPort.name}</td>
                  <td>{vntuDownlinkPort.uid}</td>
                  <td>{vntuDownlinkPort.description}</td>
                  <td>{vntuDownlinkPort.assignedOrgName}</td>
                  <td>{vntuDownlinkPort.assignedTenantName}</td>
                  <td>{vntuDownlinkPort.assignedOrgId}</td>
                  <td>{vntuDownlinkPort.assignedTenantId}</td>
                  <td>{vntuDownlinkPort.assignedVntuId}</td>
                  <td>{vntuDownlinkPort.assignedVntuName}</td>
                  <td>
                    {vntuDownlinkPort.centralSwitchName ? (
                      <Link to={`central-switch/${vntuDownlinkPort.centralSwitchId}`}>{vntuDownlinkPort.centralSwitchName}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${vntuDownlinkPort.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${vntuDownlinkPort.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${vntuDownlinkPort.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="activePortApp.vntuDownlinkPort.home.notFound">No Vntu Downlink Ports found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={vntuDownlinkPortList && vntuDownlinkPortList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ vntuDownlinkPort }: IRootState) => ({
  vntuDownlinkPortList: vntuDownlinkPort.entities,
  loading: vntuDownlinkPort.loading,
  totalItems: vntuDownlinkPort.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(VntuDownlinkPort);
