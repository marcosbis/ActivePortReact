import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './provider-configuration.reducer';
import { IProviderConfiguration } from 'app/shared/model/provider-configuration.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProviderConfigurationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProviderConfigurationDetail = (props: IProviderConfigurationDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { providerConfigurationEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.providerConfiguration.detail.title">ProviderConfiguration</Translate> [
          <b>{providerConfigurationEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.providerConfiguration.name">Name</Translate>
            </span>
          </dt>
          <dd>{providerConfigurationEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.providerConfiguration.description">Description</Translate>
            </span>
          </dt>
          <dd>{providerConfigurationEntity.description}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="activePortApp.providerConfiguration.type">Type</Translate>
            </span>
          </dt>
          <dd>{providerConfigurationEntity.type}</dd>
          <dt>
            <span id="apiType">
              <Translate contentKey="activePortApp.providerConfiguration.apiType">Api Type</Translate>
            </span>
          </dt>
          <dd>{providerConfigurationEntity.apiType}</dd>
          <dt>
            <span id="hasPortId">
              <Translate contentKey="activePortApp.providerConfiguration.hasPortId">Has Port Id</Translate>
            </span>
          </dt>
          <dd>{providerConfigurationEntity.hasPortId ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="activePortApp.providerConfiguration.services">Services</Translate>
          </dt>
          <dd>
            {providerConfigurationEntity.services
              ? providerConfigurationEntity.services.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.code}</a>
                    {providerConfigurationEntity.services && i === providerConfigurationEntity.services.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/provider-configuration" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/provider-configuration/${providerConfigurationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ providerConfiguration }: IRootState) => ({
  providerConfigurationEntity: providerConfiguration.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProviderConfigurationDetail);
