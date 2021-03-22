import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './template-configuration.reducer';
import { ITemplateConfiguration } from 'app/shared/model/template-configuration.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITemplateConfigurationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TemplateConfigurationUpdate = (props: ITemplateConfigurationUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { templateConfigurationEntity, loading, updating } = props;

  const { configuration } = templateConfigurationEntity;

  const handleClose = () => {
    props.history.push('/template-configuration' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  const onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => props.setBlob(name, data, contentType), isAnImage);
  };

  const clearBlob = name => () => {
    props.setBlob(name, undefined, undefined);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...templateConfigurationEntity,
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
          <h2 id="activePortApp.templateConfiguration.home.createOrEditLabel">
            <Translate contentKey="activePortApp.templateConfiguration.home.createOrEditLabel">
              Create or edit a TemplateConfiguration
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : templateConfigurationEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="template-configuration-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="template-configuration-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="template-configuration-name">
                  <Translate contentKey="activePortApp.templateConfiguration.name">Name</Translate>
                </Label>
                <AvField id="template-configuration-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="template-configuration-description">
                  <Translate contentKey="activePortApp.templateConfiguration.description">Description</Translate>
                </Label>
                <AvField id="template-configuration-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="configurationLabel" for="template-configuration-configuration">
                  <Translate contentKey="activePortApp.templateConfiguration.configuration">Configuration</Translate>
                </Label>
                <AvInput id="template-configuration-configuration" type="textarea" name="configuration" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/template-configuration" replace color="info">
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
  templateConfigurationEntity: storeState.templateConfiguration.entity,
  loading: storeState.templateConfiguration.loading,
  updating: storeState.templateConfiguration.updating,
  updateSuccess: storeState.templateConfiguration.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TemplateConfigurationUpdate);
