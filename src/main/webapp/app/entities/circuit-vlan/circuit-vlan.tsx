import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './circuit-vlan.reducer';
import { ICircuitVlan } from 'app/shared/model/circuit-vlan.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface ICircuitVlanProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const CircuitVlan = (props: ICircuitVlanProps) => {
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

  const { circuitVlanList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="circuit-vlan-heading">
        <Translate contentKey="activePortApp.circuitVlan.home.title">Circuit Vlans</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="activePortApp.circuitVlan.home.createLabel">Create new Circuit Vlan</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {circuitVlanList && circuitVlanList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('zone')}>
                  <Translate contentKey="activePortApp.circuitVlan.zone">Zone</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('serviceKey')}>
                  <Translate contentKey="activePortApp.circuitVlan.serviceKey">Service Key</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('vlanId')}>
                  <Translate contentKey="activePortApp.circuitVlan.vlanId">Vlan Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('rd')}>
                  <Translate contentKey="activePortApp.circuitVlan.rd">Rd</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('serviceId')}>
                  <Translate contentKey="activePortApp.circuitVlan.serviceId">Service Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('tenantName')}>
                  <Translate contentKey="activePortApp.circuitVlan.tenantName">Tenant Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('childServiceId')}>
                  <Translate contentKey="activePortApp.circuitVlan.childServiceId">Child Service Id</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('childNtuId')}>
                  <Translate contentKey="activePortApp.circuitVlan.childNtuId">Child Ntu Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('realmIp')}>
                  <Translate contentKey="activePortApp.circuitVlan.realmIp">Realm Ip</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('internetType')}>
                  <Translate contentKey="activePortApp.circuitVlan.internetType">Internet Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('type')}>
                  <Translate contentKey="activePortApp.circuitVlan.type">Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="activePortApp.circuitVlan.serviceConfiguration">Service Configuration</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {circuitVlanList.map((circuitVlan, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${circuitVlan.id}`} color="link" size="sm">
                      {circuitVlan.id}
                    </Button>
                  </td>
                  <td>{circuitVlan.zone}</td>
                  <td>{circuitVlan.serviceKey}</td>
                  <td>{circuitVlan.vlanId}</td>
                  <td>{circuitVlan.rd}</td>
                  <td>{circuitVlan.serviceId}</td>
                  <td>{circuitVlan.tenantName}</td>
                  <td>{circuitVlan.childServiceId}</td>
                  <td>{circuitVlan.childNtuId}</td>
                  <td>{circuitVlan.realmIp}</td>
                  <td>
                    <Translate contentKey={`activePortApp.InternetTypeEnum.${circuitVlan.internetType}`} />
                  </td>
                  <td>
                    <Translate contentKey={`activePortApp.ConnectTypeEnum.${circuitVlan.type}`} />
                  </td>
                  <td>
                    {circuitVlan.serviceConfigurationName ? (
                      <Link to={`service-configuration/${circuitVlan.serviceConfigurationId}`}>{circuitVlan.serviceConfigurationName}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${circuitVlan.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${circuitVlan.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${circuitVlan.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="activePortApp.circuitVlan.home.notFound">No Circuit Vlans found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={circuitVlanList && circuitVlanList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ circuitVlan }: IRootState) => ({
  circuitVlanList: circuitVlan.entities,
  loading: circuitVlan.loading,
  totalItems: circuitVlan.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CircuitVlan);
