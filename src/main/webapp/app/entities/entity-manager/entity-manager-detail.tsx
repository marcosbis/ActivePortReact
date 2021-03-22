import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './entity-manager.reducer';
import { IEntityManager } from 'app/shared/model/entity-manager.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEntityManagerDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EntityManagerDetail = (props: IEntityManagerDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { entityManagerEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.entityManager.detail.title">EntityManager</Translate> [<b>{entityManagerEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.entityManager.name">Name</Translate>
            </span>
          </dt>
          <dd>{entityManagerEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.entityManager.description">Description</Translate>
            </span>
          </dt>
          <dd>{entityManagerEntity.description}</dd>
          <dt>
            <span id="uid">
              <Translate contentKey="activePortApp.entityManager.uid">Uid</Translate>
            </span>
          </dt>
          <dd>{entityManagerEntity.uid}</dd>
        </dl>
        <Button tag={Link} to="/entity-manager" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/entity-manager/${entityManagerEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ entityManager }: IRootState) => ({
  entityManagerEntity: entityManager.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EntityManagerDetail);
