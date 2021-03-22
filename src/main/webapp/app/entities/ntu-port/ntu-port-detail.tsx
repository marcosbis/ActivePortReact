import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ntu-port.reducer';
import { INtuPort } from 'app/shared/model/ntu-port.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INtuPortDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NtuPortDetail = (props: INtuPortDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { ntuPortEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.ntuPort.detail.title">NtuPort</Translate> [<b>{ntuPortEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="activePortApp.ntuPort.name">Name</Translate>
            </span>
          </dt>
          <dd>{ntuPortEntity.name}</dd>
          <dt>
            <span id="label">
              <Translate contentKey="activePortApp.ntuPort.label">Label</Translate>
            </span>
          </dt>
          <dd>{ntuPortEntity.label}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.ntuPort.description">Description</Translate>
            </span>
          </dt>
          <dd>{ntuPortEntity.description}</dd>
          <dt>
            <span id="mac">
              <Translate contentKey="activePortApp.ntuPort.mac">Mac</Translate>
            </span>
          </dt>
          <dd>{ntuPortEntity.mac}</dd>
          <dt>
            <span id="port">
              <Translate contentKey="activePortApp.ntuPort.port">Port</Translate>
            </span>
          </dt>
          <dd>{ntuPortEntity.port}</dd>
          <dt>
            <span id="portType">
              <Translate contentKey="activePortApp.ntuPort.portType">Port Type</Translate>
            </span>
          </dt>
          <dd>{ntuPortEntity.portType}</dd>
          <dt>
            <span id="trunk">
              <Translate contentKey="activePortApp.ntuPort.trunk">Trunk</Translate>
            </span>
          </dt>
          <dd>{ntuPortEntity.trunk ? 'true' : 'false'}</dd>
          <dt>
            <span id="jumbo">
              <Translate contentKey="activePortApp.ntuPort.jumbo">Jumbo</Translate>
            </span>
          </dt>
          <dd>{ntuPortEntity.jumbo ? 'true' : 'false'}</dd>
          <dt>
            <span id="portSpeed">
              <Translate contentKey="activePortApp.ntuPort.portSpeed">Port Speed</Translate>
            </span>
          </dt>
          <dd>{ntuPortEntity.portSpeed}</dd>
          <dt>
            <span id="internetPort">
              <Translate contentKey="activePortApp.ntuPort.internetPort">Internet Port</Translate>
            </span>
          </dt>
          <dd>{ntuPortEntity.internetPort ? 'true' : 'false'}</dd>
          <dt>
            <span id="uplinkPort">
              <Translate contentKey="activePortApp.ntuPort.uplinkPort">Uplink Port</Translate>
            </span>
          </dt>
          <dd>{ntuPortEntity.uplinkPort}</dd>
          <dt>
            <Translate contentKey="activePortApp.ntuPort.ntu">Ntu</Translate>
          </dt>
          <dd>{ntuPortEntity.ntuName ? ntuPortEntity.ntuName : ''}</dd>
        </dl>
        <Button tag={Link} to="/ntu-port" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ntu-port/${ntuPortEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ ntuPort }: IRootState) => ({
  ntuPortEntity: ntuPort.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NtuPortDetail);
