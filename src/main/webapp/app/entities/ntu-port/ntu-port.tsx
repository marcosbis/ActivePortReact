import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './ntu-port.reducer';
import { INtuPort } from 'app/shared/model/ntu-port.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface INtuPortProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const NtuPort = (props: INtuPortProps) => {
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

  const { ntuPortList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="ntu-port-heading">
        <Translate contentKey="activePortApp.ntuPort.home.title">Ntu Ports</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="activePortApp.ntuPort.home.createLabel">Create new Ntu Port</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {ntuPortList && ntuPortList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="activePortApp.ntuPort.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('label')}>
                  <Translate contentKey="activePortApp.ntuPort.label">Label</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="activePortApp.ntuPort.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('mac')}>
                  <Translate contentKey="activePortApp.ntuPort.mac">Mac</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('port')}>
                  <Translate contentKey="activePortApp.ntuPort.port">Port</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('portType')}>
                  <Translate contentKey="activePortApp.ntuPort.portType">Port Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('trunk')}>
                  <Translate contentKey="activePortApp.ntuPort.trunk">Trunk</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('jumbo')}>
                  <Translate contentKey="activePortApp.ntuPort.jumbo">Jumbo</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('portSpeed')}>
                  <Translate contentKey="activePortApp.ntuPort.portSpeed">Port Speed</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('internetPort')}>
                  <Translate contentKey="activePortApp.ntuPort.internetPort">Internet Port</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('uplinkPort')}>
                  <Translate contentKey="activePortApp.ntuPort.uplinkPort">Uplink Port</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="activePortApp.ntuPort.ntu">Ntu</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {ntuPortList.map((ntuPort, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${ntuPort.id}`} color="link" size="sm">
                      {ntuPort.id}
                    </Button>
                  </td>
                  <td>{ntuPort.name}</td>
                  <td>{ntuPort.label}</td>
                  <td>{ntuPort.description}</td>
                  <td>{ntuPort.mac}</td>
                  <td>{ntuPort.port}</td>
                  <td>
                    <Translate contentKey={`activePortApp.PortTypeEnum.${ntuPort.portType}`} />
                  </td>
                  <td>{ntuPort.trunk ? 'true' : 'false'}</td>
                  <td>{ntuPort.jumbo ? 'true' : 'false'}</td>
                  <td>{ntuPort.portSpeed}</td>
                  <td>{ntuPort.internetPort ? 'true' : 'false'}</td>
                  <td>{ntuPort.uplinkPort}</td>
                  <td>{ntuPort.ntuName ? <Link to={`ntu/${ntuPort.ntuId}`}>{ntuPort.ntuName}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${ntuPort.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${ntuPort.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${ntuPort.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="activePortApp.ntuPort.home.notFound">No Ntu Ports found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={ntuPortList && ntuPortList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ ntuPort }: IRootState) => ({
  ntuPortList: ntuPort.entities,
  loading: ntuPort.loading,
  totalItems: ntuPort.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NtuPort);
