import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { byteSize, Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './service-code.reducer';
import { IServiceCode } from 'app/shared/model/service-code.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface IServiceCodeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ServiceCode = (props: IServiceCodeProps) => {
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

  const { serviceCodeList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="service-code-heading">
        <Translate contentKey="activePortApp.serviceCode.home.title">Service Codes</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="activePortApp.serviceCode.home.createLabel">Create new Service Code</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {serviceCodeList && serviceCodeList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="activePortApp.serviceCode.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('command')}>
                  <Translate contentKey="activePortApp.serviceCode.command">Command</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="activePortApp.serviceCode.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('enabled')}>
                  <Translate contentKey="activePortApp.serviceCode.enabled">Enabled</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('serviceUrl')}>
                  <Translate contentKey="activePortApp.serviceCode.serviceUrl">Service Url</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('hostedType')}>
                  <Translate contentKey="activePortApp.serviceCode.hostedType">Hosted Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('creationType')}>
                  <Translate contentKey="activePortApp.serviceCode.creationType">Creation Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('tag')}>
                  <Translate contentKey="activePortApp.serviceCode.tag">Tag</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('dtoClass')}>
                  <Translate contentKey="activePortApp.serviceCode.dtoClass">Dto Class</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="activePortApp.serviceCode.providerType">Provider Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="activePortApp.serviceCode.serviceType">Service Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {serviceCodeList.map((serviceCode, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${serviceCode.id}`} color="link" size="sm">
                      {serviceCode.id}
                    </Button>
                  </td>
                  <td>{serviceCode.name}</td>
                  <td>{serviceCode.command}</td>
                  <td>{serviceCode.description}</td>
                  <td>{serviceCode.enabled ? 'true' : 'false'}</td>
                  <td>{serviceCode.serviceUrl}</td>
                  <td>
                    <Translate contentKey={`activePortApp.HostedTypeEnum.${serviceCode.hostedType}`} />
                  </td>
                  <td>
                    <Translate contentKey={`activePortApp.CreationTypeEnum.${serviceCode.creationType}`} />
                  </td>
                  <td>{serviceCode.tag}</td>
                  <td>{serviceCode.dtoClass}</td>
                  <td>
                    {serviceCode.providerTypeName ? (
                      <Link to={`provider-configuration/${serviceCode.providerTypeId}`}>{serviceCode.providerTypeName}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {serviceCode.serviceTypeCode ? (
                      <Link to={`service-type/${serviceCode.serviceTypeId}`}>{serviceCode.serviceTypeCode}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${serviceCode.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${serviceCode.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${serviceCode.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="activePortApp.serviceCode.home.notFound">No Service Codes found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={serviceCodeList && serviceCodeList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ serviceCode }: IRootState) => ({
  serviceCodeList: serviceCode.entities,
  loading: serviceCode.loading,
  totalItems: serviceCode.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ServiceCode);
