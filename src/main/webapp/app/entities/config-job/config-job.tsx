import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import {
  byteSize,
  Translate,
  ICrudGetAllAction,
  TextFormat,
  getSortState,
  IPaginationBaseState,
  JhiPagination,
  JhiItemCount,
} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './config-job.reducer';
import { IConfigJob } from 'app/shared/model/config-job.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface IConfigJobProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ConfigJob = (props: IConfigJobProps) => {
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

  const { configJobList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="config-job-heading">
        <Translate contentKey="activePortApp.configJob.home.title">Config Jobs</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="activePortApp.configJob.home.createLabel">Create new Config Job</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {configJobList && configJobList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('hostId')}>
                  <Translate contentKey="activePortApp.configJob.hostId">Host Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('status')}>
                  <Translate contentKey="activePortApp.configJob.status">Status</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('uuid')}>
                  <Translate contentKey="activePortApp.configJob.uuid">Uuid</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('message')}>
                  <Translate contentKey="activePortApp.configJob.message">Message</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('command')}>
                  <Translate contentKey="activePortApp.configJob.command">Command</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('executed')}>
                  <Translate contentKey="activePortApp.configJob.executed">Executed</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('executedStatus')}>
                  <Translate contentKey="activePortApp.configJob.executedStatus">Executed Status</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('executedMessage')}>
                  <Translate contentKey="activePortApp.configJob.executedMessage">Executed Message</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('ntuId')}>
                  <Translate contentKey="activePortApp.configJob.ntuId">Ntu Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('eventType')}>
                  <Translate contentKey="activePortApp.configJob.eventType">Event Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('user')}>
                  <Translate contentKey="activePortApp.configJob.user">User</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('errorMessage')}>
                  <Translate contentKey="activePortApp.configJob.errorMessage">Error Message</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('callbackUrl')}>
                  <Translate contentKey="activePortApp.configJob.callbackUrl">Callback Url</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {configJobList.map((configJob, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${configJob.id}`} color="link" size="sm">
                      {configJob.id}
                    </Button>
                  </td>
                  <td>{configJob.hostId}</td>
                  <td>{configJob.status}</td>
                  <td>{configJob.uuid}</td>
                  <td>{configJob.message}</td>
                  <td>{configJob.command}</td>
                  <td>{configJob.executed ? <TextFormat type="date" value={configJob.executed} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{configJob.executedStatus}</td>
                  <td>{configJob.executedMessage}</td>
                  <td>{configJob.ntuId}</td>
                  <td>
                    <Translate contentKey={`activePortApp.EventTypeEnum.${configJob.eventType}`} />
                  </td>
                  <td>{configJob.user}</td>
                  <td>{configJob.errorMessage}</td>
                  <td>{configJob.callbackUrl}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${configJob.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${configJob.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${configJob.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="activePortApp.configJob.home.notFound">No Config Jobs found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={configJobList && configJobList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ configJob }: IRootState) => ({
  configJobList: configJob.entities,
  loading: configJob.loading,
  totalItems: configJob.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ConfigJob);
