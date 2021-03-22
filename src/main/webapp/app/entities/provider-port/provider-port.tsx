import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './provider-port.reducer';
import { IProviderPort } from 'app/shared/model/provider-port.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface IProviderPortProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ProviderPort = (props: IProviderPortProps) => {
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

  const { providerPortList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="provider-port-heading">
        <Translate contentKey="activePortApp.providerPort.home.title">Provider Ports</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="activePortApp.providerPort.home.createLabel">Create new Provider Port</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {providerPortList && providerPortList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="activePortApp.providerPort.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('uid')}>
                  <Translate contentKey="activePortApp.providerPort.uid">Uid</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="activePortApp.providerPort.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('type')}>
                  <Translate contentKey="activePortApp.providerPort.type">Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('connection')}>
                  <Translate contentKey="activePortApp.providerPort.connection">Connection</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('portType')}>
                  <Translate contentKey="activePortApp.providerPort.portType">Port Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('portId')}>
                  <Translate contentKey="activePortApp.providerPort.portId">Port Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('market')}>
                  <Translate contentKey="activePortApp.providerPort.market">Market</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('locationId')}>
                  <Translate contentKey="activePortApp.providerPort.locationId">Location Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="activePortApp.providerPort.thirdPartyApi">Third Party Api</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {providerPortList.map((providerPort, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${providerPort.id}`} color="link" size="sm">
                      {providerPort.id}
                    </Button>
                  </td>
                  <td>{providerPort.name}</td>
                  <td>{providerPort.uid}</td>
                  <td>{providerPort.description}</td>
                  <td>
                    <Translate contentKey={`activePortApp.PartnerTypeEnum.${providerPort.type}`} />
                  </td>
                  <td>
                    <Translate contentKey={`activePortApp.ConnetionTypeEnum.${providerPort.connection}`} />
                  </td>
                  <td>
                    <Translate contentKey={`activePortApp.AwsTypeEnum.${providerPort.portType}`} />
                  </td>
                  <td>{providerPort.portId}</td>
                  <td>{providerPort.market}</td>
                  <td>{providerPort.locationId}</td>
                  <td>
                    {providerPort.thirdPartyApiName ? (
                      <Link to={`third-party-api/${providerPort.thirdPartyApiId}`}>{providerPort.thirdPartyApiName}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${providerPort.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${providerPort.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${providerPort.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="activePortApp.providerPort.home.notFound">No Provider Ports found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={providerPortList && providerPortList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ providerPort }: IRootState) => ({
  providerPortList: providerPort.entities,
  loading: providerPort.loading,
  totalItems: providerPort.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProviderPort);
