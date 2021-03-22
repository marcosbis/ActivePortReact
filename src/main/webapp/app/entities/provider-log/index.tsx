import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ProviderLog from './provider-log';
import ProviderLogDetail from './provider-log-detail';
import ProviderLogUpdate from './provider-log-update';
import ProviderLogDeleteDialog from './provider-log-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ProviderLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ProviderLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ProviderLogDetail} />
      <ErrorBoundaryRoute path={match.url} component={ProviderLog} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ProviderLogDeleteDialog} />
  </>
);

export default Routes;
