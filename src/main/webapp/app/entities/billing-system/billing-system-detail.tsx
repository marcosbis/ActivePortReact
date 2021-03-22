import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './billing-system.reducer';
import { IBillingSystem } from 'app/shared/model/billing-system.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBillingSystemDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BillingSystemDetail = (props: IBillingSystemDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { billingSystemEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.billingSystem.detail.title">BillingSystem</Translate> [<b>{billingSystemEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.billingSystem.name">Name</Translate>
            </span>
          </dt>
          <dd>{billingSystemEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.billingSystem.description">Description</Translate>
            </span>
          </dt>
          <dd>{billingSystemEntity.description}</dd>
          <dt>
            <span id="api">
              <Translate contentKey="activePortApp.billingSystem.api">Api</Translate>
            </span>
          </dt>
          <dd>{billingSystemEntity.api}</dd>
          <dt>
            <span id="stage">
              <Translate contentKey="activePortApp.billingSystem.stage">Stage</Translate>
            </span>
          </dt>
          <dd>{billingSystemEntity.stage}</dd>
          <dt>
            <span id="username">
              <Translate contentKey="activePortApp.billingSystem.username">Username</Translate>
            </span>
          </dt>
          <dd>{billingSystemEntity.username}</dd>
          <dt>
            <span id="secret">
              <Translate contentKey="activePortApp.billingSystem.secret">Secret</Translate>
            </span>
          </dt>
          <dd>{billingSystemEntity.secret}</dd>
          <dt>
            <span id="privateKeyCert">
              <Translate contentKey="activePortApp.billingSystem.privateKeyCert">Private Key Cert</Translate>
            </span>
          </dt>
          <dd>{billingSystemEntity.privateKeyCert}</dd>
          <dt>
            <span id="privateKeyPassword">
              <Translate contentKey="activePortApp.billingSystem.privateKeyPassword">Private Key Password</Translate>
            </span>
          </dt>
          <dd>{billingSystemEntity.privateKeyPassword}</dd>
          <dt>
            <span id="billingUid">
              <Translate contentKey="activePortApp.billingSystem.billingUid">Billing Uid</Translate>
            </span>
          </dt>
          <dd>{billingSystemEntity.billingUid}</dd>
          <dt>
            <span id="startBilling">
              <Translate contentKey="activePortApp.billingSystem.startBilling">Start Billing</Translate>
            </span>
          </dt>
          <dd>
            {billingSystemEntity.startBilling ? (
              <TextFormat value={billingSystemEntity.startBilling} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="currencyCode">
              <Translate contentKey="activePortApp.billingSystem.currencyCode">Currency Code</Translate>
            </span>
          </dt>
          <dd>{billingSystemEntity.currencyCode}</dd>
        </dl>
        <Button tag={Link} to="/billing-system" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/billing-system/${billingSystemEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ billingSystem }: IRootState) => ({
  billingSystemEntity: billingSystem.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BillingSystemDetail);
