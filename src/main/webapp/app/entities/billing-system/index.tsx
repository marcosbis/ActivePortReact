import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BillingSystem from './billing-system';
import BillingSystemDetail from './billing-system-detail';
import BillingSystemUpdate from './billing-system-update';
import BillingSystemDeleteDialog from './billing-system-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BillingSystemUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BillingSystemUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BillingSystemDetail} />
      <ErrorBoundaryRoute path={match.url} component={BillingSystem} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={BillingSystemDeleteDialog} />
  </>
);

export default Routes;
