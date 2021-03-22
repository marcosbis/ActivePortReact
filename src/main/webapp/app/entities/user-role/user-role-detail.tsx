import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './user-role.reducer';
import { IUserRole } from 'app/shared/model/user-role.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUserRoleDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const UserRoleDetail = (props: IUserRoleDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { userRoleEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.userRole.detail.title">UserRole</Translate> [<b>{userRoleEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.userRole.name">Name</Translate>
            </span>
          </dt>
          <dd>{userRoleEntity.name}</dd>
          <dt>
            <span id="priority">
              <Translate contentKey="activePortApp.userRole.priority">Priority</Translate>
            </span>
          </dt>
          <dd>{userRoleEntity.priority}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.userRole.description">Description</Translate>
            </span>
          </dt>
          <dd>{userRoleEntity.description}</dd>
          <dt>
            <Translate contentKey="activePortApp.userRole.authorities">Authorities</Translate>
          </dt>
          <dd>
            {userRoleEntity.authorities
              ? userRoleEntity.authorities.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.name}</a>
                    {userRoleEntity.authorities && i === userRoleEntity.authorities.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/user-role" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/user-role/${userRoleEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ userRole }: IRootState) => ({
  userRoleEntity: userRole.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UserRoleDetail);
