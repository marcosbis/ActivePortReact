import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './third-party-api.reducer';
import { IThirdPartyApi } from 'app/shared/model/third-party-api.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface IThirdPartyApiProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ThirdPartyApi = (props: IThirdPartyApiProps) => {
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

  const { thirdPartyApiList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="third-party-api-heading">
        <Translate contentKey="activePortApp.thirdPartyApi.home.title">Third Party Apis</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="activePortApp.thirdPartyApi.home.createLabel">Create new Third Party Api</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {thirdPartyApiList && thirdPartyApiList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="activePortApp.thirdPartyApi.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="activePortApp.thirdPartyApi.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('api')}>
                  <Translate contentKey="activePortApp.thirdPartyApi.api">Api</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('stage')}>
                  <Translate contentKey="activePortApp.thirdPartyApi.stage">Stage</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('username')}>
                  <Translate contentKey="activePortApp.thirdPartyApi.username">Username</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('secret')}>
                  <Translate contentKey="activePortApp.thirdPartyApi.secret">Secret</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('privateKeyCert')}>
                  <Translate contentKey="activePortApp.thirdPartyApi.privateKeyCert">Private Key Cert</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('privateKeyPassword')}>
                  <Translate contentKey="activePortApp.thirdPartyApi.privateKeyPassword">Private Key Password</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('billingUid')}>
                  <Translate contentKey="activePortApp.thirdPartyApi.billingUid">Billing Uid</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('productUid')}>
                  <Translate contentKey="activePortApp.thirdPartyApi.productUid">Product Uid</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('endpoint')}>
                  <Translate contentKey="activePortApp.thirdPartyApi.endpoint">Endpoint</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('allowSharedPortsUid')}>
                  <Translate contentKey="activePortApp.thirdPartyApi.allowSharedPortsUid">Allow Shared Ports Uid</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('connectionType')}>
                  <Translate contentKey="activePortApp.thirdPartyApi.connectionType">Connection Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {thirdPartyApiList.map((thirdPartyApi, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${thirdPartyApi.id}`} color="link" size="sm">
                      {thirdPartyApi.id}
                    </Button>
                  </td>
                  <td>{thirdPartyApi.name}</td>
                  <td>{thirdPartyApi.description}</td>
                  <td>
                    <Translate contentKey={`activePortApp.ApiType.${thirdPartyApi.api}`} />
                  </td>
                  <td>{thirdPartyApi.stage}</td>
                  <td>{thirdPartyApi.username}</td>
                  <td>{thirdPartyApi.secret}</td>
                  <td>{thirdPartyApi.privateKeyCert}</td>
                  <td>{thirdPartyApi.privateKeyPassword}</td>
                  <td>{thirdPartyApi.billingUid}</td>
                  <td>{thirdPartyApi.productUid}</td>
                  <td>{thirdPartyApi.endpoint}</td>
                  <td>{thirdPartyApi.allowSharedPortsUid}</td>
                  <td>
                    <Translate contentKey={`activePortApp.PortApiTypeEnum.${thirdPartyApi.connectionType}`} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${thirdPartyApi.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${thirdPartyApi.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${thirdPartyApi.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="activePortApp.thirdPartyApi.home.notFound">No Third Party Apis found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={thirdPartyApiList && thirdPartyApiList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ thirdPartyApi }: IRootState) => ({
  thirdPartyApiList: thirdPartyApi.entities,
  loading: thirdPartyApi.loading,
  totalItems: thirdPartyApi.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ThirdPartyApi);
