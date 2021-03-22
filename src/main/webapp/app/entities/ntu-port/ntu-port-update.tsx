import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { INtu } from 'app/shared/model/ntu.model';
import { getEntities as getNtus } from 'app/entities/ntu/ntu.reducer';
import { getEntity, updateEntity, createEntity, reset } from './ntu-port.reducer';
import { INtuPort } from 'app/shared/model/ntu-port.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface INtuPortUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NtuPortUpdate = (props: INtuPortUpdateProps) => {
  const [ntuId, setNtuId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { ntuPortEntity, ntus, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/ntu-port' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getNtus();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...ntuPortEntity,
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
          <h2 id="activePortApp.ntuPort.home.createOrEditLabel">
            <Translate contentKey="activePortApp.ntuPort.home.createOrEditLabel">Create or edit a NtuPort</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : ntuPortEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="ntu-port-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="ntu-port-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="ntu-port-name">
                  <Translate contentKey="activePortApp.ntuPort.name">Name</Translate>
                </Label>
                <AvField id="ntu-port-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="labelLabel" for="ntu-port-label">
                  <Translate contentKey="activePortApp.ntuPort.label">Label</Translate>
                </Label>
                <AvField id="ntu-port-label" type="text" name="label" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="ntu-port-description">
                  <Translate contentKey="activePortApp.ntuPort.description">Description</Translate>
                </Label>
                <AvField id="ntu-port-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="macLabel" for="ntu-port-mac">
                  <Translate contentKey="activePortApp.ntuPort.mac">Mac</Translate>
                </Label>
                <AvField id="ntu-port-mac" type="text" name="mac" />
              </AvGroup>
              <AvGroup>
                <Label id="portLabel" for="ntu-port-port">
                  <Translate contentKey="activePortApp.ntuPort.port">Port</Translate>
                </Label>
                <AvField id="ntu-port-port" type="string" className="form-control" name="port" />
              </AvGroup>
              <AvGroup>
                <Label id="portTypeLabel" for="ntu-port-portType">
                  <Translate contentKey="activePortApp.ntuPort.portType">Port Type</Translate>
                </Label>
                <AvInput
                  id="ntu-port-portType"
                  type="select"
                  className="form-control"
                  name="portType"
                  value={(!isNew && ntuPortEntity.portType) || 'ETHERNET'}
                >
                  <option value="ETHERNET">{translate('activePortApp.PortTypeEnum.ETHERNET')}</option>
                  <option value="SFP">{translate('activePortApp.PortTypeEnum.SFP')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup check>
                <Label id="trunkLabel">
                  <AvInput id="ntu-port-trunk" type="checkbox" className="form-check-input" name="trunk" />
                  <Translate contentKey="activePortApp.ntuPort.trunk">Trunk</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="jumboLabel">
                  <AvInput id="ntu-port-jumbo" type="checkbox" className="form-check-input" name="jumbo" />
                  <Translate contentKey="activePortApp.ntuPort.jumbo">Jumbo</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="portSpeedLabel" for="ntu-port-portSpeed">
                  <Translate contentKey="activePortApp.ntuPort.portSpeed">Port Speed</Translate>
                </Label>
                <AvField id="ntu-port-portSpeed" type="text" name="portSpeed" />
              </AvGroup>
              <AvGroup check>
                <Label id="internetPortLabel">
                  <AvInput id="ntu-port-internetPort" type="checkbox" className="form-check-input" name="internetPort" />
                  <Translate contentKey="activePortApp.ntuPort.internetPort">Internet Port</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="uplinkPortLabel" for="ntu-port-uplinkPort">
                  <Translate contentKey="activePortApp.ntuPort.uplinkPort">Uplink Port</Translate>
                </Label>
                <AvField id="ntu-port-uplinkPort" type="text" name="uplinkPort" />
              </AvGroup>
              <AvGroup>
                <Label for="ntu-port-ntu">
                  <Translate contentKey="activePortApp.ntuPort.ntu">Ntu</Translate>
                </Label>
                <AvInput id="ntu-port-ntu" type="select" className="form-control" name="ntuId">
                  <option value="" key="0" />
                  {ntus
                    ? ntus.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/ntu-port" replace color="info">
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
  ntus: storeState.ntu.entities,
  ntuPortEntity: storeState.ntuPort.entity,
  loading: storeState.ntuPort.loading,
  updating: storeState.ntuPort.updating,
  updateSuccess: storeState.ntuPort.updateSuccess,
});

const mapDispatchToProps = {
  getNtus,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NtuPortUpdate);
