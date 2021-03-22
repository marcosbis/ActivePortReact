import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import XeroAccount from './xero-account';
import XeroAccountDetail from './xero-account-detail';
import XeroAccountUpdate from './xero-account-update';
import XeroAccountDeleteDialog from './xero-account-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={XeroAccountUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={XeroAccountUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={XeroAccountDetail} />
      <ErrorBoundaryRoute path={match.url} component={XeroAccount} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={XeroAccountDeleteDialog} />
  </>
);

export default Routes;
