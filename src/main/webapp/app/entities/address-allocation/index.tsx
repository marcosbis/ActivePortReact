import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AddressAllocation from './address-allocation';
import AddressAllocationDetail from './address-allocation-detail';
import AddressAllocationUpdate from './address-allocation-update';
import AddressAllocationDeleteDialog from './address-allocation-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AddressAllocationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AddressAllocationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AddressAllocationDetail} />
      <ErrorBoundaryRoute path={match.url} component={AddressAllocation} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AddressAllocationDeleteDialog} />
  </>
);

export default Routes;
