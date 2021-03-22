import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tile-configuration.reducer';
import { ITileConfiguration } from 'app/shared/model/tile-configuration.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITileConfigurationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TileConfigurationDetail = (props: ITileConfigurationDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { tileConfigurationEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.tileConfiguration.detail.title">TileConfiguration</Translate> [
          <b>{tileConfigurationEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.tileConfiguration.name">Name</Translate>
            </span>
          </dt>
          <dd>{tileConfigurationEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.tileConfiguration.description">Description</Translate>
            </span>
          </dt>
          <dd>{tileConfigurationEntity.description}</dd>
          <dt>
            <span id="command">
              <Translate contentKey="activePortApp.tileConfiguration.command">Command</Translate>
            </span>
          </dt>
          <dd>{tileConfigurationEntity.command}</dd>
          <dt>
            <Translate contentKey="activePortApp.tileConfiguration.serviceType">Service Type</Translate>
          </dt>
          <dd>{tileConfigurationEntity.serviceTypeCode ? tileConfigurationEntity.serviceTypeCode : ''}</dd>
          <dt>
            <Translate contentKey="activePortApp.tileConfiguration.services">Services</Translate>
          </dt>
          <dd>
            {tileConfigurationEntity.services
              ? tileConfigurationEntity.services.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.name}</a>
                    {tileConfigurationEntity.services && i === tileConfigurationEntity.services.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/tile-configuration" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/tile-configuration/${tileConfigurationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ tileConfiguration }: IRootState) => ({
  tileConfigurationEntity: tileConfiguration.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TileConfigurationDetail);
