import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ntu-config.reducer';
import { INtuConfig } from 'app/shared/model/ntu-config.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INtuConfigDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NtuConfigDetail = (props: INtuConfigDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { ntuConfigEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.ntuConfig.detail.title">NtuConfig</Translate> [<b>{ntuConfigEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="serialNumber">
              <Translate contentKey="activePortApp.ntuConfig.serialNumber">Serial Number</Translate>
            </span>
          </dt>
          <dd>{ntuConfigEntity.serialNumber}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.ntuConfig.name">Name</Translate>
            </span>
          </dt>
          <dd>{ntuConfigEntity.name}</dd>
          <dt>
            <span id="firmwareVersion">
              <Translate contentKey="activePortApp.ntuConfig.firmwareVersion">Firmware Version</Translate>
            </span>
          </dt>
          <dd>{ntuConfigEntity.firmwareVersion}</dd>
          <dt>
            <span id="ntuId">
              <Translate contentKey="activePortApp.ntuConfig.ntuId">Ntu Id</Translate>
            </span>
          </dt>
          <dd>{ntuConfigEntity.ntuId}</dd>
        </dl>
        <Button tag={Link} to="/ntu-config" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ntu-config/${ntuConfigEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ ntuConfig }: IRootState) => ({
  ntuConfigEntity: ntuConfig.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NtuConfigDetail);
