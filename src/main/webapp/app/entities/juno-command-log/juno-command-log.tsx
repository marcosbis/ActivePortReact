import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './juno-command-log.reducer';
import { IJunoCommandLog } from 'app/shared/model/juno-command-log.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface IJunoCommandLogProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const JunoCommandLog = (props: IJunoCommandLogProps) => {
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

  const { junoCommandLogList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="juno-command-log-heading">
        <Translate contentKey="activePortApp.junoCommandLog.home.title">Juno Command Logs</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="activePortApp.junoCommandLog.home.createLabel">Create new Juno Command Log</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {junoCommandLogList && junoCommandLogList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('ntuId')}>
                  <Translate contentKey="activePortApp.junoCommandLog.ntuId">Ntu Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('switchId')}>
                  <Translate contentKey="activePortApp.junoCommandLog.switchId">Switch Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('serviceId')}>
                  <Translate contentKey="activePortApp.junoCommandLog.serviceId">Service Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('vxcId')}>
                  <Translate contentKey="activePortApp.junoCommandLog.vxcId">Vxc Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('deviceUrl')}>
                  <Translate contentKey="activePortApp.junoCommandLog.deviceUrl">Device Url</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('uuid')}>
                  <Translate contentKey="activePortApp.junoCommandLog.uuid">Uuid</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('command')}>
                  <Translate contentKey="activePortApp.junoCommandLog.command">Command</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('cmdResponse')}>
                  <Translate contentKey="activePortApp.junoCommandLog.cmdResponse">Cmd Response</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('executedDate')}>
                  <Translate contentKey="activePortApp.junoCommandLog.executedDate">Executed Date</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('eventType')}>
                  <Translate contentKey="activePortApp.junoCommandLog.eventType">Event Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('user')}>
                  <Translate contentKey="activePortApp.junoCommandLog.user">User</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('deviceName')}>
                  <Translate contentKey="activePortApp.junoCommandLog.deviceName">Device Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('targetType')}>
                  <Translate contentKey="activePortApp.junoCommandLog.targetType">Target Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('hasErrors')}>
                  <Translate contentKey="activePortApp.junoCommandLog.hasErrors">Has Errors</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {junoCommandLogList.map((junoCommandLog, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${junoCommandLog.id}`} color="link" size="sm">
                      {junoCommandLog.id}
                    </Button>
                  </td>
                  <td>{junoCommandLog.ntuId}</td>
                  <td>{junoCommandLog.switchId}</td>
                  <td>{junoCommandLog.serviceId}</td>
                  <td>{junoCommandLog.vxcId}</td>
                  <td>{junoCommandLog.deviceUrl}</td>
                  <td>{junoCommandLog.uuid}</td>
                  <td>{junoCommandLog.command}</td>
                  <td>{junoCommandLog.cmdResponse}</td>
                  <td>
                    {junoCommandLog.executedDate ? (
                      <TextFormat type="date" value={junoCommandLog.executedDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    <Translate contentKey={`activePortApp.EventTypeEnum.${junoCommandLog.eventType}`} />
                  </td>
                  <td>{junoCommandLog.user}</td>
                  <td>{junoCommandLog.deviceName}</td>
                  <td>
                    <Translate contentKey={`activePortApp.DeviceTargetTypeEnum.${junoCommandLog.targetType}`} />
                  </td>
                  <td>{junoCommandLog.hasErrors ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${junoCommandLog.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${junoCommandLog.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${junoCommandLog.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="activePortApp.junoCommandLog.home.notFound">No Juno Command Logs found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={junoCommandLogList && junoCommandLogList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ junoCommandLog }: IRootState) => ({
  junoCommandLogList: junoCommandLog.entities,
  loading: junoCommandLog.loading,
  totalItems: junoCommandLog.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(JunoCommandLog);
