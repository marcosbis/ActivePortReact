import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './user-profile.reducer';
import { IUserProfile } from 'app/shared/model/user-profile.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUserProfileDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const UserProfileDetail = (props: IUserProfileDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { userProfileEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.userProfile.detail.title">UserProfile</Translate> [<b>{userProfileEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.userProfile.name">Name</Translate>
            </span>
          </dt>
          <dd>{userProfileEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.userProfile.description">Description</Translate>
            </span>
          </dt>
          <dd>{userProfileEntity.description}</dd>
          <dt>
            <Translate contentKey="activePortApp.userProfile.userRole">User Role</Translate>
          </dt>
          <dd>{userProfileEntity.userRoleName ? userProfileEntity.userRoleName : ''}</dd>
        </dl>
        <Button tag={Link} to="/user-profile" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/user-profile/${userProfileEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ userProfile }: IRootState) => ({
  userProfileEntity: userProfile.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UserProfileDetail);
