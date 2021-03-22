import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CircuitVlan from './circuit-vlan';
import CircuitVlanDetail from './circuit-vlan-detail';
import CircuitVlanUpdate from './circuit-vlan-update';
import CircuitVlanDeleteDialog from './circuit-vlan-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CircuitVlanUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CircuitVlanUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CircuitVlanDetail} />
      <ErrorBoundaryRoute path={match.url} component={CircuitVlan} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CircuitVlanDeleteDialog} />
  </>
);

export default Routes;
