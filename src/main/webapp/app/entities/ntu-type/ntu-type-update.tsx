import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './ntu-type.reducer';
import { INtuType } from 'app/shared/model/ntu-type.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface INtuTypeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NtuTypeUpdate = (props: INtuTypeUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { ntuTypeEntity, loading, updating } = props;

  const { portTemplate } = ntuTypeEntity;

  const handleClose = () => {
    props.history.push('/ntu-type' + props.location.search);
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
        ...ntuTypeEntity,
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
          <h2 id="activePortApp.ntuType.home.createOrEditLabel">
            <Translate contentKey="activePortApp.ntuType.home.createOrEditLabel">Create or edit a NtuType</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : ntuTypeEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="ntu-type-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="ntu-type-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="modelLabel" for="ntu-type-model">
                  <Translate contentKey="activePortApp.ntuType.model">Model</Translate>
                </Label>
                <AvField id="ntu-type-model" type="text" name="model" />
              </AvGroup>
              <AvGroup>
                <Label id="ethernetPortsLabel" for="ntu-type-ethernetPorts">
                  <Translate contentKey="activePortApp.ntuType.ethernetPorts">Ethernet Ports</Translate>
                </Label>
                <AvField id="ntu-type-ethernetPorts" type="string" className="form-control" name="ethernetPorts" />
              </AvGroup>
              <AvGroup>
                <Label id="sfpPortsLabel" for="ntu-type-sfpPorts">
                  <Translate contentKey="activePortApp.ntuType.sfpPorts">Sfp Ports</Translate>
                </Label>
                <AvField id="ntu-type-sfpPorts" type="string" className="form-control" name="sfpPorts" />
              </AvGroup>
              <AvGroup>
                <Label id="pictureContentTypeLabel" for="ntu-type-pictureContentType">
                  <Translate contentKey="activePortApp.ntuType.pictureContentType">Picture Content Type</Translate>
                </Label>
                <AvField id="ntu-type-pictureContentType" type="text" name="pictureContentType" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="ntu-type-description">
                  <Translate contentKey="activePortApp.ntuType.description">Description</Translate>
                </Label>
                <AvField id="ntu-type-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="portServiceTypeLabel" for="ntu-type-portServiceType">
                  <Translate contentKey="activePortApp.ntuType.portServiceType">Port Service Type</Translate>
                </Label>
                <AvInput
                  id="ntu-type-portServiceType"
                  type="select"
                  className="form-control"
                  name="portServiceType"
                  value={(!isNew && ntuTypeEntity.portServiceType) || 'EDGE'}
                >
                  <option value="EDGE">{translate('activePortApp.PortServiceTypeEnum.EDGE')}</option>
                  <option value="SWITCH">{translate('activePortApp.PortServiceTypeEnum.SWITCH')}</option>
                  <option value="FIREWALL">{translate('activePortApp.PortServiceTypeEnum.FIREWALL')}</option>
                  <option value="NTU">{translate('activePortApp.PortServiceTypeEnum.NTU')}</option>
                  <option value="PE">{translate('activePortApp.PortServiceTypeEnum.PE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="osTypeLabel" for="ntu-type-osType">
                  <Translate contentKey="activePortApp.ntuType.osType">Os Type</Translate>
                </Label>
                <AvInput
                  id="ntu-type-osType"
                  type="select"
                  className="form-control"
                  name="osType"
                  value={(!isNew && ntuTypeEntity.osType) || 'JUNOS'}
                >
                  <option value="JUNOS">{translate('activePortApp.OsTypeEnum.JUNOS')}</option>
                  <option value="MIKROTIK">{translate('activePortApp.OsTypeEnum.MIKROTIK')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="etherPrefixLabel" for="ntu-type-etherPrefix">
                  <Translate contentKey="activePortApp.ntuType.etherPrefix">Ether Prefix</Translate>
                </Label>
                <AvField id="ntu-type-etherPrefix" type="text" name="etherPrefix" />
              </AvGroup>
              <AvGroup>
                <Label id="sfpPrefixLabel" for="ntu-type-sfpPrefix">
                  <Translate contentKey="activePortApp.ntuType.sfpPrefix">Sfp Prefix</Translate>
                </Label>
                <AvField id="ntu-type-sfpPrefix" type="text" name="sfpPrefix" />
              </AvGroup>
              <AvGroup>
                <Label id="startIndexLabel" for="ntu-type-startIndex">
                  <Translate contentKey="activePortApp.ntuType.startIndex">Start Index</Translate>
                </Label>
                <AvField id="ntu-type-startIndex" type="string" className="form-control" name="startIndex" />
              </AvGroup>
              <AvGroup>
                <Label id="portTemplateLabel" for="ntu-type-portTemplate">
                  <Translate contentKey="activePortApp.ntuType.portTemplate">Port Template</Translate>
                </Label>
                <AvInput id="ntu-type-portTemplate" type="textarea" name="portTemplate" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/ntu-type" replace color="info">
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
  ntuTypeEntity: storeState.ntuType.entity,
  loading: storeState.ntuType.loading,
  updating: storeState.ntuType.updating,
  updateSuccess: storeState.ntuType.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(NtuTypeUpdate);
