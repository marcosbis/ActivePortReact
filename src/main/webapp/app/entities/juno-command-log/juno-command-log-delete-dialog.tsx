import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IJunoCommandLog } from 'app/shared/model/juno-command-log.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './juno-command-log.reducer';

export interface IJunoCommandLogDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const JunoCommandLogDeleteDialog = (props: IJunoCommandLogDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/juno-command-log' + props.location.search);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.junoCommandLogEntity.id);
  };

  const { junoCommandLogEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="activePortApp.junoCommandLog.delete.question">
        <Translate contentKey="activePortApp.junoCommandLog.delete.question" interpolate={{ id: junoCommandLogEntity.id }}>
          Are you sure you want to delete this JunoCommandLog?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-junoCommandLog" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ junoCommandLog }: IRootState) => ({
  junoCommandLogEntity: junoCommandLog.entity,
  updateSuccess: junoCommandLog.updateSuccess,
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(JunoCommandLogDeleteDialog);
