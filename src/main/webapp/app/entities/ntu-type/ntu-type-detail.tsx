import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ntu-type.reducer';
import { INtuType } from 'app/shared/model/ntu-type.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INtuTypeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NtuTypeDetail = (props: INtuTypeDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { ntuTypeEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.ntuType.detail.title">NtuType</Translate> [<b>{ntuTypeEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="model">
              <Translate contentKey="activePortApp.ntuType.model">Model</Translate>
            </span>
          </dt>
          <dd>{ntuTypeEntity.model}</dd>
          <dt>
            <span id="ethernetPorts">
              <Translate contentKey="activePortApp.ntuType.ethernetPorts">Ethernet Ports</Translate>
            </span>
          </dt>
          <dd>{ntuTypeEntity.ethernetPorts}</dd>
          <dt>
            <span id="sfpPorts">
              <Translate contentKey="activePortApp.ntuType.sfpPorts">Sfp Ports</Translate>
            </span>
          </dt>
          <dd>{ntuTypeEntity.sfpPorts}</dd>
          <dt>
            <span id="pictureContentType">
              <Translate contentKey="activePortApp.ntuType.pictureContentType">Picture Content Type</Translate>
            </span>
          </dt>
          <dd>{ntuTypeEntity.pictureContentType}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="activePortApp.ntuType.description">Description</Translate>
            </span>
          </dt>
          <dd>{ntuTypeEntity.description}</dd>
          <dt>
            <span id="portServiceType">
              <Translate contentKey="activePortApp.ntuType.portServiceType">Port Service Type</Translate>
            </span>
          </dt>
          <dd>{ntuTypeEntity.portServiceType}</dd>
          <dt>
            <span id="osType">
              <Translate contentKey="activePortApp.ntuType.osType">Os Type</Translate>
            </span>
          </dt>
          <dd>{ntuTypeEntity.osType}</dd>
          <dt>
            <span id="etherPrefix">
              <Translate contentKey="activePortApp.ntuType.etherPrefix">Ether Prefix</Translate>
            </span>
          </dt>
          <dd>{ntuTypeEntity.etherPrefix}</dd>
          <dt>
            <span id="sfpPrefix">
              <Translate contentKey="activePortApp.ntuType.sfpPrefix">Sfp Prefix</Translate>
            </span>
          </dt>
          <dd>{ntuTypeEntity.sfpPrefix}</dd>
          <dt>
            <span id="startIndex">
              <Translate contentKey="activePortApp.ntuType.startIndex">Start Index</Translate>
            </span>
          </dt>
          <dd>{ntuTypeEntity.startIndex}</dd>
          <dt>
            <span id="portTemplate">
              <Translate contentKey="activePortApp.ntuType.portTemplate">Port Template</Translate>
            </span>
          </dt>
          <dd>{ntuTypeEntity.portTemplate}</dd>
        </dl>
        <Button tag={Link} to="/ntu-type" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ntu-type/${ntuTypeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ ntuType }: IRootState) => ({
  ntuTypeEntity: ntuType.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NtuTypeDetail);
