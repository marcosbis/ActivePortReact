import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ProviderPort from './provider-port';
import ProviderPortDetail from './provider-port-detail';
import ProviderPortUpdate from './provider-port-update';
import ProviderPortDeleteDialog from './provider-port-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ProviderPortUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ProviderPortUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ProviderPortDetail} />
      <ErrorBoundaryRoute path={match.url} component={ProviderPort} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ProviderPortDeleteDialog} />
  </>
);

export default Routes;
