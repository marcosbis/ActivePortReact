import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './service-configuration.reducer';
import { IServiceConfiguration } from 'app/shared/model/service-configuration.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IServiceConfigurationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ServiceConfigurationDetail = (props: IServiceConfigurationDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { serviceConfigurationEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.serviceConfiguration.detail.title">ServiceConfiguration</Translate> [
          <b>{serviceConfigurationEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.serviceConfiguration.name">Name</Translate>
            </span>
          </dt>
          <dd>{serviceConfigurationEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.serviceConfiguration.description">Description</Translate>
            </span>
          </dt>
          <dd>{serviceConfigurationEntity.description}</dd>
          <dt>
            <span id="tenantType">
              <Translate contentKey="activePortApp.serviceConfiguration.tenantType">Tenant Type</Translate>
            </span>
          </dt>
          <dd>{serviceConfigurationEntity.tenantType}</dd>
          <dt>
            <span id="command">
              <Translate contentKey="activePortApp.serviceConfiguration.command">Command</Translate>
            </span>
          </dt>
          <dd>{serviceConfigurationEntity.command}</dd>
          <dt>
            <span id="test">
              <Translate contentKey="activePortApp.serviceConfiguration.test">Test</Translate>
            </span>
          </dt>
          <dd>{serviceConfigurationEntity.test}</dd>
          <dt>
            <span id="useDefaultCommands">
              <Translate contentKey="activePortApp.serviceConfiguration.useDefaultCommands">Use Default Commands</Translate>
            </span>
          </dt>
          <dd>{serviceConfigurationEntity.useDefaultCommands ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="activePortApp.serviceConfiguration.serviceCode">Service Code</Translate>
          </dt>
          <dd>{serviceConfigurationEntity.serviceCodeName ? serviceConfigurationEntity.serviceCodeName : ''}</dd>
          <dt>
            <Translate contentKey="activePortApp.serviceConfiguration.provider">Provider</Translate>
          </dt>
          <dd>{serviceConfigurationEntity.providerName ? serviceConfigurationEntity.providerName : ''}</dd>
          <dt>
            <Translate contentKey="activePortApp.serviceConfiguration.priceCode">Price Code</Translate>
          </dt>
          <dd>{serviceConfigurationEntity.priceCodeName ? serviceConfigurationEntity.priceCodeName : ''}</dd>
          <dt>
            <Translate contentKey="activePortApp.serviceConfiguration.commands">Commands</Translate>
          </dt>
          <dd>
            {serviceConfigurationEntity.commands
              ? serviceConfigurationEntity.commands.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.name}</a>
                    {serviceConfigurationEntity.commands && i === serviceConfigurationEntity.commands.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/service-configuration" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/service-configuration/${serviceConfigurationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ serviceConfiguration }: IRootState) => ({
  serviceConfigurationEntity: serviceConfiguration.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ServiceConfigurationDetail);
