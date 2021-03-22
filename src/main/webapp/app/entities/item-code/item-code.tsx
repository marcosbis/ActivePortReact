import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './item-code.reducer';
import { IItemCode } from 'app/shared/model/item-code.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface IItemCodeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ItemCode = (props: IItemCodeProps) => {
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

  const { itemCodeList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="item-code-heading">
        <Translate contentKey="activePortApp.itemCode.home.title">Item Codes</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="activePortApp.itemCode.home.createLabel">Create new Item Code</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {itemCodeList && itemCodeList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="activePortApp.itemCode.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('code')}>
                  <Translate contentKey="activePortApp.itemCode.code">Code</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('codeRate')}>
                  <Translate contentKey="activePortApp.itemCode.codeRate">Code Rate</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('codeActivation')}>
                  <Translate contentKey="activePortApp.itemCode.codeActivation">Code Activation</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="activePortApp.itemCode.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('localPriceRate')}>
                  <Translate contentKey="activePortApp.itemCode.localPriceRate">Local Price Rate</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('localPriceMontlhy')}>
                  <Translate contentKey="activePortApp.itemCode.localPriceMontlhy">Local Price Montlhy</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('localPriceActivation')}>
                  <Translate contentKey="activePortApp.itemCode.localPriceActivation">Local Price Activation</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('codeUpLift')}>
                  <Translate contentKey="activePortApp.itemCode.codeUpLift">Code Up Lift</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('upLift')}>
                  <Translate contentKey="activePortApp.itemCode.upLift">Up Lift</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('useUpLift')}>
                  <Translate contentKey="activePortApp.itemCode.useUpLift">Use Up Lift</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('useLocalPrice')}>
                  <Translate contentKey="activePortApp.itemCode.useLocalPrice">Use Local Price</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {itemCodeList.map((itemCode, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${itemCode.id}`} color="link" size="sm">
                      {itemCode.id}
                    </Button>
                  </td>
                  <td>{itemCode.name}</td>
                  <td>{itemCode.code}</td>
                  <td>{itemCode.codeRate}</td>
                  <td>{itemCode.codeActivation}</td>
                  <td>{itemCode.description}</td>
                  <td>{itemCode.localPriceRate}</td>
                  <td>{itemCode.localPriceMontlhy}</td>
                  <td>{itemCode.localPriceActivation}</td>
                  <td>{itemCode.codeUpLift}</td>
                  <td>{itemCode.upLift}</td>
                  <td>{itemCode.useUpLift ? 'true' : 'false'}</td>
                  <td>{itemCode.useLocalPrice ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${itemCode.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${itemCode.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${itemCode.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="activePortApp.itemCode.home.notFound">No Item Codes found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={itemCodeList && itemCodeList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ itemCode }: IRootState) => ({
  itemCodeList: itemCode.entities,
  loading: itemCode.loading,
  totalItems: itemCode.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ItemCode);
