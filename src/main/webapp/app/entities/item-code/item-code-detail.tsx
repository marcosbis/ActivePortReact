import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './item-code.reducer';
import { IItemCode } from 'app/shared/model/item-code.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IItemCodeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ItemCodeDetail = (props: IItemCodeDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { itemCodeEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.itemCode.detail.title">ItemCode</Translate> [<b>{itemCodeEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.itemCode.name">Name</Translate>
            </span>
          </dt>
          <dd>{itemCodeEntity.name}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="activePortApp.itemCode.code">Code</Translate>
            </span>
          </dt>
          <dd>{itemCodeEntity.code}</dd>
          <dt>
            <span id="codeRate">
              <Translate contentKey="activePortApp.itemCode.codeRate">Code Rate</Translate>
            </span>
          </dt>
          <dd>{itemCodeEntity.codeRate}</dd>
          <dt>
            <span id="codeActivation">
              <Translate contentKey="activePortApp.itemCode.codeActivation">Code Activation</Translate>
            </span>
          </dt>
          <dd>{itemCodeEntity.codeActivation}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.itemCode.description">Description</Translate>
            </span>
          </dt>
          <dd>{itemCodeEntity.description}</dd>
          <dt>
            <span id="localPriceRate">
              <Translate contentKey="activePortApp.itemCode.localPriceRate">Local Price Rate</Translate>
            </span>
          </dt>
          <dd>{itemCodeEntity.localPriceRate}</dd>
          <dt>
            <span id="localPriceMontlhy">
              <Translate contentKey="activePortApp.itemCode.localPriceMontlhy">Local Price Montlhy</Translate>
            </span>
          </dt>
          <dd>{itemCodeEntity.localPriceMontlhy}</dd>
          <dt>
            <span id="localPriceActivation">
              <Translate contentKey="activePortApp.itemCode.localPriceActivation">Local Price Activation</Translate>
            </span>
          </dt>
          <dd>{itemCodeEntity.localPriceActivation}</dd>
          <dt>
            <span id="codeUpLift">
              <Translate contentKey="activePortApp.itemCode.codeUpLift">Code Up Lift</Translate>
            </span>
          </dt>
          <dd>{itemCodeEntity.codeUpLift}</dd>
          <dt>
            <span id="upLift">
              <Translate contentKey="activePortApp.itemCode.upLift">Up Lift</Translate>
            </span>
          </dt>
          <dd>{itemCodeEntity.upLift}</dd>
          <dt>
            <span id="useUpLift">
              <Translate contentKey="activePortApp.itemCode.useUpLift">Use Up Lift</Translate>
            </span>
          </dt>
          <dd>{itemCodeEntity.useUpLift ? 'true' : 'false'}</dd>
          <dt>
            <span id="useLocalPrice">
              <Translate contentKey="activePortApp.itemCode.useLocalPrice">Use Local Price</Translate>
            </span>
          </dt>
          <dd>{itemCodeEntity.useLocalPrice ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/item-code" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/item-code/${itemCodeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ itemCode }: IRootState) => ({
  itemCodeEntity: itemCode.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ItemCodeDetail);
