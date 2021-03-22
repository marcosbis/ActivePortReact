import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './template-configuration.reducer';
import { ITemplateConfiguration } from 'app/shared/model/template-configuration.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITemplateConfigurationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TemplateConfigurationDetail = (props: ITemplateConfigurationDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { templateConfigurationEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.templateConfiguration.detail.title">TemplateConfiguration</Translate> [
          <b>{templateConfigurationEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.templateConfiguration.name">Name</Translate>
            </span>
          </dt>
          <dd>{templateConfigurationEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.templateConfiguration.description">Description</Translate>
            </span>
          </dt>
          <dd>{templateConfigurationEntity.description}</dd>
          <dt>
            <span id="configuration">
              <Translate contentKey="activePortApp.templateConfiguration.configuration">Configuration</Translate>
            </span>
          </dt>
          <dd>{templateConfigurationEntity.configuration}</dd>
        </dl>
        <Button tag={Link} to="/template-configuration" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/template-configuration/${templateConfigurationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ templateConfiguration }: IRootState) => ({
  templateConfigurationEntity: templateConfiguration.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TemplateConfigurationDetail);
