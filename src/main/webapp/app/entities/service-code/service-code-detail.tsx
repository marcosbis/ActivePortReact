import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './service-code.reducer';
import { IServiceCode } from 'app/shared/model/service-code.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IServiceCodeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ServiceCodeDetail = (props: IServiceCodeDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { serviceCodeEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.serviceCode.detail.title">ServiceCode</Translate> [<b>{serviceCodeEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.serviceCode.name">Name</Translate>
            </span>
          </dt>
          <dd>{serviceCodeEntity.name}</dd>
          <dt>
            <span id="command">
              <Translate contentKey="activePortApp.serviceCode.command">Command</Translate>
            </span>
          </dt>
          <dd>{serviceCodeEntity.command}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.serviceCode.description">Description</Translate>
            </span>
          </dt>
          <dd>{serviceCodeEntity.description}</dd>
          <dt>
            <span id="enabled">
              <Translate contentKey="activePortApp.serviceCode.enabled">Enabled</Translate>
            </span>
          </dt>
          <dd>{serviceCodeEntity.enabled ? 'true' : 'false'}</dd>
          <dt>
            <span id="serviceUrl">
              <Translate contentKey="activePortApp.serviceCode.serviceUrl">Service Url</Translate>
            </span>
          </dt>
          <dd>{serviceCodeEntity.serviceUrl}</dd>
          <dt>
            <span id="hostedType">
              <Translate contentKey="activePortApp.serviceCode.hostedType">Hosted Type</Translate>
            </span>
          </dt>
          <dd>{serviceCodeEntity.hostedType}</dd>
          <dt>
            <span id="creationType">
              <Translate contentKey="activePortApp.serviceCode.creationType">Creation Type</Translate>
            </span>
          </dt>
          <dd>{serviceCodeEntity.creationType}</dd>
          <dt>
            <span id="tag">
              <Translate contentKey="activePortApp.serviceCode.tag">Tag</Translate>
            </span>
          </dt>
          <dd>{serviceCodeEntity.tag}</dd>
          <dt>
            <span id="dtoClass">
              <Translate contentKey="activePortApp.serviceCode.dtoClass">Dto Class</Translate>
            </span>
          </dt>
          <dd>{serviceCodeEntity.dtoClass}</dd>
          <dt>
            <Translate contentKey="activePortApp.serviceCode.providerType">Provider Type</Translate>
          </dt>
          <dd>{serviceCodeEntity.providerTypeName ? serviceCodeEntity.providerTypeName : ''}</dd>
          <dt>
            <Translate contentKey="activePortApp.serviceCode.serviceType">Service Type</Translate>
          </dt>
          <dd>{serviceCodeEntity.serviceTypeCode ? serviceCodeEntity.serviceTypeCode : ''}</dd>
        </dl>
        <Button tag={Link} to="/service-code" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/service-code/${serviceCodeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ serviceCode }: IRootState) => ({
  serviceCodeEntity: serviceCode.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ServiceCodeDetail);
