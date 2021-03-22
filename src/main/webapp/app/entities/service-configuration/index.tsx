import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ServiceConfiguration from './service-configuration';
import ServiceConfigurationDetail from './service-configuration-detail';
import ServiceConfigurationUpdate from './service-configuration-update';
import ServiceConfigurationDeleteDialog from './service-configuration-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ServiceConfigurationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ServiceConfigurationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ServiceConfigurationDetail} />
      <ErrorBoundaryRoute path={match.url} component={ServiceConfiguration} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ServiceConfigurationDeleteDialog} />
  </>
);

export default Routes;
