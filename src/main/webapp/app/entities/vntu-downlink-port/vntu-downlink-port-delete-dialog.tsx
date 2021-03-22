import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IVntuDownlinkPort } from 'app/shared/model/vntu-downlink-port.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './vntu-downlink-port.reducer';

export interface IVntuDownlinkPortDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const VntuDownlinkPortDeleteDialog = (props: IVntuDownlinkPortDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/vntu-downlink-port' + props.location.search);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.vntuDownlinkPortEntity.id);
  };

  const { vntuDownlinkPortEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="activePortApp.vntuDownlinkPort.delete.question">
        <Translate contentKey="activePortApp.vntuDownlinkPort.delete.question" interpolate={{ id: vntuDownlinkPortEntity.id }}>
          Are you sure you want to delete this VntuDownlinkPort?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-vntuDownlinkPort" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ vntuDownlinkPort }: IRootState) => ({
  vntuDownlinkPortEntity: vntuDownlinkPort.entity,
  updateSuccess: vntuDownlinkPort.updateSuccess,
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(VntuDownlinkPortDeleteDialog);
