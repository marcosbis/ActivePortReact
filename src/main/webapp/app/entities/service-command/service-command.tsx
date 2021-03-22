import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { byteSize, Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './service-command.reducer';
import { IServiceCommand } from 'app/shared/model/service-command.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface IServiceCommandProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ServiceCommand = (props: IServiceCommandProps) => {
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

  const { serviceCommandList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="service-command-heading">
        <Translate contentKey="activePortApp.serviceCommand.home.title">Service Commands</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="activePortApp.serviceCommand.home.createLabel">Create new Service Command</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {serviceCommandList && serviceCommandList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="activePortApp.serviceCommand.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('command')}>
                  <Translate contentKey="activePortApp.serviceCommand.command">Command</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('onEvent')}>
                  <Translate contentKey="activePortApp.serviceCommand.onEvent">On Event</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('onService')}>
                  <Translate contentKey="activePortApp.serviceCommand.onService">On Service</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('deviceType')}>
                  <Translate contentKey="activePortApp.serviceCommand.deviceType">Device Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('enabled')}>
                  <Translate contentKey="activePortApp.serviceCommand.enabled">Enabled</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('circuitType')}>
                  <Translate contentKey="activePortApp.serviceCommand.circuitType">Circuit Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('tag')}>
                  <Translate contentKey="activePortApp.serviceCommand.tag">Tag</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('osType')}>
                  <Translate contentKey="activePortApp.serviceCommand.osType">Os Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('entryType')}>
                  <Translate contentKey="activePortApp.serviceCommand.entryType">Entry Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {serviceCommandList.map((serviceCommand, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${serviceCommand.id}`} color="link" size="sm">
                      {serviceCommand.id}
                    </Button>
                  </td>
                  <td>{serviceCommand.name}</td>
                  <td>{serviceCommand.command}</td>
                  <td>
                    <Translate contentKey={`activePortApp.OnEventTypeEnum.${serviceCommand.onEvent}`} />
                  </td>
                  <td>
                    <Translate contentKey={`activePortApp.ServiceTypeEnum.${serviceCommand.onService}`} />
                  </td>
                  <td>
                    <Translate contentKey={`activePortApp.NtuSerieEnum.${serviceCommand.deviceType}`} />
                  </td>
                  <td>{serviceCommand.enabled ? 'true' : 'false'}</td>
                  <td>
                    <Translate contentKey={`activePortApp.FilterCommandTypeEnum.${serviceCommand.circuitType}`} />
                  </td>
                  <td>{serviceCommand.tag}</td>
                  <td>
                    <Translate contentKey={`activePortApp.OsTypeEnum.${serviceCommand.osType}`} />
                  </td>
                  <td>
                    <Translate contentKey={`activePortApp.EntityTypeEnum.${serviceCommand.entryType}`} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${serviceCommand.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${serviceCommand.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${serviceCommand.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="activePortApp.serviceCommand.home.notFound">No Service Commands found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={serviceCommandList && serviceCommandList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ serviceCommand }: IRootState) => ({
  serviceCommandList: serviceCommand.entities,
  loading: serviceCommand.loading,
  totalItems: serviceCommand.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ServiceCommand);
