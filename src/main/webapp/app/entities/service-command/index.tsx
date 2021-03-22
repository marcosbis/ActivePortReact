import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ServiceCommand from './service-command';
import ServiceCommandDetail from './service-command-detail';
import ServiceCommandUpdate from './service-command-update';
import ServiceCommandDeleteDialog from './service-command-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ServiceCommandUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ServiceCommandUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ServiceCommandDetail} />
      <ErrorBoundaryRoute path={match.url} component={ServiceCommand} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ServiceCommandDeleteDialog} />
  </>
);

export default Routes;
