import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './provider-port.reducer';
import { IProviderPort } from 'app/shared/model/provider-port.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProviderPortDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProviderPortDetail = (props: IProviderPortDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { providerPortEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.providerPort.detail.title">ProviderPort</Translate> [<b>{providerPortEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.providerPort.name">Name</Translate>
            </span>
          </dt>
          <dd>{providerPortEntity.name}</dd>
          <dt>
            <span id="uid">
              <Translate contentKey="activePortApp.providerPort.uid">Uid</Translate>
            </span>
          </dt>
          <dd>{providerPortEntity.uid}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.providerPort.description">Description</Translate>
            </span>
          </dt>
          <dd>{providerPortEntity.description}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="activePortApp.providerPort.type">Type</Translate>
            </span>
          </dt>
          <dd>{providerPortEntity.type}</dd>
          <dt>
            <span id="connection">
              <Translate contentKey="activePortApp.providerPort.connection">Connection</Translate>
            </span>
          </dt>
          <dd>{providerPortEntity.connection}</dd>
          <dt>
            <span id="portType">
              <Translate contentKey="activePortApp.providerPort.portType">Port Type</Translate>
            </span>
          </dt>
          <dd>{providerPortEntity.portType}</dd>
          <dt>
            <span id="portId">
              <Translate contentKey="activePortApp.providerPort.portId">Port Id</Translate>
            </span>
          </dt>
          <dd>{providerPortEntity.portId}</dd>
          <dt>
            <span id="market">
              <Translate contentKey="activePortApp.providerPort.market">Market</Translate>
            </span>
          </dt>
          <dd>{providerPortEntity.market}</dd>
          <dt>
            <span id="locationId">
              <Translate contentKey="activePortApp.providerPort.locationId">Location Id</Translate>
            </span>
          </dt>
          <dd>{providerPortEntity.locationId}</dd>
          <dt>
            <Translate contentKey="activePortApp.providerPort.thirdPartyApi">Third Party Api</Translate>
          </dt>
          <dd>{providerPortEntity.thirdPartyApiName ? providerPortEntity.thirdPartyApiName : ''}</dd>
        </dl>
        <Button tag={Link} to="/provider-port" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/provider-port/${providerPortEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ providerPort }: IRootState) => ({
  providerPortEntity: providerPort.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProviderPortDetail);
