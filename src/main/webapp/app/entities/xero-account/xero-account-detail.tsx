import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './xero-account.reducer';
import { IXeroAccount } from 'app/shared/model/xero-account.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IXeroAccountDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const XeroAccountDetail = (props: IXeroAccountDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { xeroAccountEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="activePortApp.xeroAccount.detail.title">XeroAccount</Translate> [<b>{xeroAccountEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="contactId">
              <Translate contentKey="activePortApp.xeroAccount.contactId">Contact Id</Translate>
            </span>
          </dt>
          <dd>{xeroAccountEntity.contactId}</dd>
        </dl>
        <Button tag={Link} to="/xero-account" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/xero-account/${xeroAccountEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ xeroAccount }: IRootState) => ({
  xeroAccountEntity: xeroAccount.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(XeroAccountDetail);
