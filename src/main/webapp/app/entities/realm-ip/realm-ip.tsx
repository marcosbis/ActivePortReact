import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './realm-ip.reducer';
import { IRealmIp } from 'app/shared/model/realm-ip.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface IRealmIpProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const RealmIp = (props: IRealmIpProps) => {
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

  const { realmIpList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="realm-ip-heading">
        <Translate contentKey="activePortApp.realmIp.home.title">Realm Ips</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="activePortApp.realmIp.home.createLabel">Create new Realm Ip</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {realmIpList && realmIpList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('subnet')}>
                  <Translate contentKey="activePortApp.realmIp.subnet">Subnet</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="activePortApp.realmIp.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('desciption')}>
                  <Translate contentKey="activePortApp.realmIp.desciption">Desciption</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('mask')}>
                  <Translate contentKey="activePortApp.realmIp.mask">Mask</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('subnetSize')}>
                  <Translate contentKey="activePortApp.realmIp.subnetSize">Subnet Size</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('firstIp')}>
                  <Translate contentKey="activePortApp.realmIp.firstIp">First Ip</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('lastIp')}>
                  <Translate contentKey="activePortApp.realmIp.lastIp">Last Ip</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('broadcast')}>
                  <Translate contentKey="activePortApp.realmIp.broadcast">Broadcast</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('cir')}>
                  <Translate contentKey="activePortApp.realmIp.cir">Cir</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('ipsecGateway')}>
                  <Translate contentKey="activePortApp.realmIp.ipsecGateway">Ipsec Gateway</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="activePortApp.realmIp.location">Location</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {realmIpList.map((realmIp, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${realmIp.id}`} color="link" size="sm">
                      {realmIp.id}
                    </Button>
                  </td>
                  <td>{realmIp.subnet}</td>
                  <td>{realmIp.name}</td>
                  <td>{realmIp.desciption}</td>
                  <td>{realmIp.mask}</td>
                  <td>{realmIp.subnetSize}</td>
                  <td>{realmIp.firstIp}</td>
                  <td>{realmIp.lastIp}</td>
                  <td>{realmIp.broadcast}</td>
                  <td>{realmIp.cir}</td>
                  <td>{realmIp.ipsecGateway}</td>
                  <td>{realmIp.locationCode ? <Link to={`location/${realmIp.locationId}`}>{realmIp.locationCode}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${realmIp.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${realmIp.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${realmIp.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="activePortApp.realmIp.home.notFound">No Realm Ips found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={realmIpList && realmIpList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ realmIp }: IRootState) => ({
  realmIpList: realmIp.entities,
  loading: realmIp.loading,
  totalItems: realmIp.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RealmIp);
