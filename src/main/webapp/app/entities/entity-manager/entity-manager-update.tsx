import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './entity-manager.reducer';
import { IEntityManager } from 'app/shared/model/entity-manager.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEntityManagerUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EntityManagerUpdate = (props: IEntityManagerUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { entityManagerEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/entity-manager' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...entityManagerEntity,
        ...values,
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
          <h2 id="activePortApp.entityManager.home.createOrEditLabel">
            <Translate contentKey="activePortApp.entityManager.home.createOrEditLabel">Create or edit a EntityManager</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : entityManagerEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="entity-manager-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="entity-manager-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="entity-manager-name">
                  <Translate contentKey="activePortApp.entityManager.name">Name</Translate>
                </Label>
                <AvField id="entity-manager-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="entity-manager-description">
                  <Translate contentKey="activePortApp.entityManager.description">Description</Translate>
                </Label>
                <AvField id="entity-manager-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="uidLabel" for="entity-manager-uid">
                  <Translate contentKey="activePortApp.entityManager.uid">Uid</Translate>
                </Label>
                <AvField id="entity-manager-uid" type="text" name="uid" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/entity-manager" replace color="info">
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
  entityManagerEntity: storeState.entityManager.entity,
  loading: storeState.entityManager.loading,
  updating: storeState.entityManager.updating,
  updateSuccess: storeState.entityManager.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EntityManagerUpdate);
