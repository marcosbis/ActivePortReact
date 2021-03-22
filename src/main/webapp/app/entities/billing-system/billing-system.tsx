import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './billing-system.reducer';
import { IBillingSystem } from 'app/shared/model/billing-system.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface IBillingSystemProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const BillingSystem = (props: IBillingSystemProps) => {
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

  const { billingSystemList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="billing-system-heading">
        <Translate contentKey="activePortApp.billingSystem.home.title">Billing Systems</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="activePortApp.billingSystem.home.createLabel">Create new Billing System</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {billingSystemList && billingSystemList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="activePortApp.billingSystem.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="activePortApp.billingSystem.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('api')}>
                  <Translate contentKey="activePortApp.billingSystem.api">Api</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('stage')}>
                  <Translate contentKey="activePortApp.billingSystem.stage">Stage</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('username')}>
                  <Translate contentKey="activePortApp.billingSystem.username">Username</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('secret')}>
                  <Translate contentKey="activePortApp.billingSystem.secret">Secret</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('privateKeyCert')}>
                  <Translate contentKey="activePortApp.billingSystem.privateKeyCert">Private Key Cert</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('privateKeyPassword')}>
                  <Translate contentKey="activePortApp.billingSystem.privateKeyPassword">Private Key Password</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('billingUid')}>
                  <Translate contentKey="activePortApp.billingSystem.billingUid">Billing Uid</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('startBilling')}>
                  <Translate contentKey="activePortApp.billingSystem.startBilling">Start Billing</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('currencyCode')}>
                  <Translate contentKey="activePortApp.billingSystem.currencyCode">Currency Code</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {billingSystemList.map((billingSystem, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${billingSystem.id}`} color="link" size="sm">
                      {billingSystem.id}
                    </Button>
                  </td>
                  <td>{billingSystem.name}</td>
                  <td>{billingSystem.description}</td>
                  <td>
                    <Translate contentKey={`activePortApp.BillingTypeEnum.${billingSystem.api}`} />
                  </td>
                  <td>{billingSystem.stage}</td>
                  <td>{billingSystem.username}</td>
                  <td>{billingSystem.secret}</td>
                  <td>{billingSystem.privateKeyCert}</td>
                  <td>{billingSystem.privateKeyPassword}</td>
                  <td>{billingSystem.billingUid}</td>
                  <td>
                    {billingSystem.startBilling ? (
                      <TextFormat type="date" value={billingSystem.startBilling} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{billingSystem.currencyCode}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${billingSystem.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${billingSystem.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${billingSystem.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="activePortApp.billingSystem.home.notFound">No Billing Systems found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={billingSystemList && billingSystemList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ billingSystem }: IRootState) => ({
  billingSystemList: billingSystem.entities,
  loading: billingSystem.loading,
  totalItems: billingSystem.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BillingSystem);
