import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAuthority } from 'app/shared/model/authority.model';
import { getEntities as getAuthorities } from 'app/entities/authority/authority.reducer';
import { getEntity, updateEntity, createEntity, reset } from './user-role.reducer';
import { IUserRole } from 'app/shared/model/user-role.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IUserRoleUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const UserRoleUpdate = (props: IUserRoleUpdateProps) => {
  const [idsauthorities, setIdsauthorities] = useState([]);
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { userRoleEntity, authorities, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/user-role' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getAuthorities();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...userRoleEntity,
        ...values,
        authorities: mapIdList(values.authorities),
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="activePortApp.userRole.home.createOrEditLabel">
            <Translate contentKey="activePortApp.userRole.home.createOrEditLabel">Create or edit a UserRole</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : userRoleEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="user-role-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="user-role-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="user-role-name">
                  <Translate contentKey="activePortApp.userRole.name">Name</Translate>
                </Label>
                <AvField id="user-role-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="priorityLabel" for="user-role-priority">
                  <Translate contentKey="activePortApp.userRole.priority">Priority</Translate>
                </Label>
                <AvField id="user-role-priority" type="string" className="form-control" name="priority" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="user-role-description">
                  <Translate contentKey="activePortApp.userRole.description">Description</Translate>
                </Label>
                <AvField id="user-role-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label for="user-role-authorities">
                  <Translate contentKey="activePortApp.userRole.authorities">Authorities</Translate>
                </Label>
                <AvInput
                  id="user-role-authorities"
                  type="select"
                  multiple
                  className="form-control"
                  name="authorities"
                  value={userRoleEntity.authorities && userRoleEntity.authorities.map(e => e.id)}
                >
                  <option value="" key="0" />
                  {authorities
                    ? authorities.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/user-role" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  authorities: storeState.authority.entities,
  userRoleEntity: storeState.userRole.entity,
  loading: storeState.userRole.loading,
  updating: storeState.userRole.updating,
  updateSuccess: storeState.userRole.updateSuccess,
});

const mapDispatchToProps = {
  getAuthorities,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UserRoleUpdate);
