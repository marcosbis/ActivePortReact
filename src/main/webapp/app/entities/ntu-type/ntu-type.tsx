import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { byteSize, Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './ntu-type.reducer';
import { INtuType } from 'app/shared/model/ntu-type.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface INtuTypeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const NtuType = (props: INtuTypeProps) => {
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

  const { ntuTypeList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="ntu-type-heading">
        <Translate contentKey="activePortApp.ntuType.home.title">Ntu Types</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="activePortApp.ntuType.home.createLabel">Create new Ntu Type</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {ntuTypeList && ntuTypeList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('model')}>
                  <Translate contentKey="activePortApp.ntuType.model">Model</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('ethernetPorts')}>
                  <Translate contentKey="activePortApp.ntuType.ethernetPorts">Ethernet Ports</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('sfpPorts')}>
                  <Translate contentKey="activePortApp.ntuType.sfpPorts">Sfp Ports</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('pictureContentType')}>
                  <Translate contentKey="activePortApp.ntuType.pictureContentType">Picture Content Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="activePortApp.ntuType.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('portServiceType')}>
                  <Translate contentKey="activePortApp.ntuType.portServiceType">Port Service Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('osType')}>
                  <Translate contentKey="activePortApp.ntuType.osType">Os Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('etherPrefix')}>
                  <Translate contentKey="activePortApp.ntuType.etherPrefix">Ether Prefix</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('sfpPrefix')}>
                  <Translate contentKey="activePortApp.ntuType.sfpPrefix">Sfp Prefix</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('startIndex')}>
                  <Translate contentKey="activePortApp.ntuType.startIndex">Start Index</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('portTemplate')}>
                  <Translate contentKey="activePortApp.ntuType.portTemplate">Port Template</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {ntuTypeList.map((ntuType, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${ntuType.id}`} color="link" size="sm">
                      {ntuType.id}
                    </Button>
                  </td>
                  <td>{ntuType.model}</td>
                  <td>{ntuType.ethernetPorts}</td>
                  <td>{ntuType.sfpPorts}</td>
                  <td>{ntuType.pictureContentType}</td>
                  <td>{ntuType.description}</td>
                  <td>
                    <Translate contentKey={`activePortApp.PortServiceTypeEnum.${ntuType.portServiceType}`} />
                  </td>
                  <td>
                    <Translate contentKey={`activePortApp.OsTypeEnum.${ntuType.osType}`} />
                  </td>
                  <td>{ntuType.etherPrefix}</td>
                  <td>{ntuType.sfpPrefix}</td>
                  <td>{ntuType.startIndex}</td>
                  <td>{ntuType.portTemplate}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${ntuType.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${ntuType.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${ntuType.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="activePortApp.ntuType.home.notFound">No Ntu Types found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={ntuTypeList && ntuTypeList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ ntuType }: IRootState) => ({
  ntuTypeList: ntuType.entities,
  loading: ntuType.loading,
  totalItems: ntuType.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NtuType);
