import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TileTenantConfiguration from './tile-tenant-configuration';
import TileTenantConfigurationDetail from './tile-tenant-configuration-detail';
import TileTenantConfigurationUpdate from './tile-tenant-configuration-update';
import TileTenantConfigurationDeleteDialog from './tile-tenant-configuration-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TileTenantConfigurationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TileTenantConfigurationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TileTenantConfigurationDetail} />
      <ErrorBoundaryRoute path={match.url} component={TileTenantConfiguration} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TileTenantConfigurationDeleteDialog} />
  </>
);

export default Routes;
