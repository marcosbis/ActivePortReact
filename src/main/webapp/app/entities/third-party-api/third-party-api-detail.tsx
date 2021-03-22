import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './third-party-api.reducer';
import { IThirdPartyApi } from 'app/shared/model/third-party-api.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IThirdPartyApiDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ThirdPartyApiDetail = (props: IThirdPartyApiDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { thirdPartyApiEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.thirdPartyApi.detail.title">ThirdPartyApi</Translate> [<b>{thirdPartyApiEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.thirdPartyApi.name">Name</Translate>
            </span>
          </dt>
          <dd>{thirdPartyApiEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.thirdPartyApi.description">Description</Translate>
            </span>
          </dt>
          <dd>{thirdPartyApiEntity.description}</dd>
          <dt>
            <span id="api">
              <Translate contentKey="activePortApp.thirdPartyApi.api">Api</Translate>
            </span>
          </dt>
          <dd>{thirdPartyApiEntity.api}</dd>
          <dt>
            <span id="stage">
              <Translate contentKey="activePortApp.thirdPartyApi.stage">Stage</Translate>
            </span>
          </dt>
          <dd>{thirdPartyApiEntity.stage}</dd>
          <dt>
            <span id="username">
              <Translate contentKey="activePortApp.thirdPartyApi.username">Username</Translate>
            </span>
          </dt>
          <dd>{thirdPartyApiEntity.username}</dd>
          <dt>
            <span id="secret">
              <Translate contentKey="activePortApp.thirdPartyApi.secret">Secret</Translate>
            </span>
          </dt>
          <dd>{thirdPartyApiEntity.secret}</dd>
          <dt>
            <span id="privateKeyCert">
              <Translate contentKey="activePortApp.thirdPartyApi.privateKeyCert">Private Key Cert</Translate>
            </span>
          </dt>
          <dd>{thirdPartyApiEntity.privateKeyCert}</dd>
          <dt>
            <span id="privateKeyPassword">
              <Translate contentKey="activePortApp.thirdPartyApi.privateKeyPassword">Private Key Password</Translate>
            </span>
          </dt>
          <dd>{thirdPartyApiEntity.privateKeyPassword}</dd>
          <dt>
            <span id="billingUid">
              <Translate contentKey="activePortApp.thirdPartyApi.billingUid">Billing Uid</Translate>
            </span>
          </dt>
          <dd>{thirdPartyApiEntity.billingUid}</dd>
          <dt>
            <span id="productUid">
              <Translate contentKey="activePortApp.thirdPartyApi.productUid">Product Uid</Translate>
            </span>
          </dt>
          <dd>{thirdPartyApiEntity.productUid}</dd>
          <dt>
            <span id="endpoint">
              <Translate contentKey="activePortApp.thirdPartyApi.endpoint">Endpoint</Translate>
            </span>
          </dt>
          <dd>{thirdPartyApiEntity.endpoint}</dd>
          <dt>
            <span id="allowSharedPortsUid">
              <Translate contentKey="activePortApp.thirdPartyApi.allowSharedPortsUid">Allow Shared Ports Uid</Translate>
            </span>
          </dt>
          <dd>{thirdPartyApiEntity.allowSharedPortsUid}</dd>
          <dt>
            <span id="connectionType">
              <Translate contentKey="activePortApp.thirdPartyApi.connectionType">Connection Type</Translate>
            </span>
          </dt>
          <dd>{thirdPartyApiEntity.connectionType}</dd>
        </dl>
        <Button tag={Link} to="/third-party-api" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/third-party-api/${thirdPartyApiEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ thirdPartyApi }: IRootState) => ({
  thirdPartyApiEntity: thirdPartyApi.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ThirdPartyApiDetail);
