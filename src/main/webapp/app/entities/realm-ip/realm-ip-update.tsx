import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ILocation } from 'app/shared/model/location.model';
import { getEntities as getLocations } from 'app/entities/location/location.reducer';
import { getEntity, updateEntity, createEntity, reset } from './realm-ip.reducer';
import { IRealmIp } from 'app/shared/model/realm-ip.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IRealmIpUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RealmIpUpdate = (props: IRealmIpUpdateProps) => {
  const [locationId, setLocationId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { realmIpEntity, locations, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/realm-ip' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getLocations();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...realmIpEntity,
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
          <h2 id="activePortApp.realmIp.home.createOrEditLabel">
            <Translate contentKey="activePortApp.realmIp.home.createOrEditLabel">Create or edit a RealmIp</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : realmIpEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="realm-ip-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="realm-ip-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="subnetLabel" for="realm-ip-subnet">
                  <Translate contentKey="activePortApp.realmIp.subnet">Subnet</Translate>
                </Label>
                <AvField id="realm-ip-subnet" type="text" name="subnet" />
              </AvGroup>
              <AvGroup>
                <Label id="nameLabel" for="realm-ip-name">
                  <Translate contentKey="activePortApp.realmIp.name">Name</Translate>
                </Label>
                <AvField id="realm-ip-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="desciptionLabel" for="realm-ip-desciption">
                  <Translate contentKey="activePortApp.realmIp.desciption">Desciption</Translate>
                </Label>
                <AvField id="realm-ip-desciption" type="text" name="desciption" />
              </AvGroup>
              <AvGroup>
                <Label id="maskLabel" for="realm-ip-mask">
                  <Translate contentKey="activePortApp.realmIp.mask">Mask</Translate>
                </Label>
                <AvField id="realm-ip-mask" type="text" name="mask" />
              </AvGroup>
              <AvGroup>
                <Label id="subnetSizeLabel" for="realm-ip-subnetSize">
                  <Translate contentKey="activePortApp.realmIp.subnetSize">Subnet Size</Translate>
                </Label>
                <AvField id="realm-ip-subnetSize" type="text" name="subnetSize" />
              </AvGroup>
              <AvGroup>
                <Label id="firstIpLabel" for="realm-ip-firstIp">
                  <Translate contentKey="activePortApp.realmIp.firstIp">First Ip</Translate>
                </Label>
                <AvField id="realm-ip-firstIp" type="text" name="firstIp" />
              </AvGroup>
              <AvGroup>
                <Label id="lastIpLabel" for="realm-ip-lastIp">
                  <Translate contentKey="activePortApp.realmIp.lastIp">Last Ip</Translate>
                </Label>
                <AvField id="realm-ip-lastIp" type="text" name="lastIp" />
              </AvGroup>
              <AvGroup>
                <Label id="broadcastLabel" for="realm-ip-broadcast">
                  <Translate contentKey="activePortApp.realmIp.broadcast">Broadcast</Translate>
                </Label>
                <AvField id="realm-ip-broadcast" type="text" name="broadcast" />
              </AvGroup>
              <AvGroup>
                <Label id="cirLabel" for="realm-ip-cir">
                  <Translate contentKey="activePortApp.realmIp.cir">Cir</Translate>
                </Label>
                <AvField id="realm-ip-cir" type="text" name="cir" />
              </AvGroup>
              <AvGroup>
                <Label id="ipsecGatewayLabel" for="realm-ip-ipsecGateway">
                  <Translate contentKey="activePortApp.realmIp.ipsecGateway">Ipsec Gateway</Translate>
                </Label>
                <AvField id="realm-ip-ipsecGateway" type="text" name="ipsecGateway" />
              </AvGroup>
              <AvGroup>
                <Label for="realm-ip-location">
                  <Translate contentKey="activePortApp.realmIp.location">Location</Translate>
                </Label>
                <AvInput id="realm-ip-location" type="select" className="form-control" name="locationId">
                  <option value="" key="0" />
                  {locations
                    ? locations.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.code}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/realm-ip" replace color="info">
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
  locations: storeState.location.entities,
  realmIpEntity: storeState.realmIp.entity,
  loading: storeState.realmIp.loading,
  updating: storeState.realmIp.updating,
  updateSuccess: storeState.realmIp.updateSuccess,
});

const mapDispatchToProps = {
  getLocations,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RealmIpUpdate);
