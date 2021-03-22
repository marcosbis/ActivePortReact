import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ProviderConfiguration from './provider-configuration';
import ProviderConfigurationDetail from './provider-configuration-detail';
import ProviderConfigurationUpdate from './provider-configuration-update';
import ProviderConfigurationDeleteDialog from './provider-configuration-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ProviderConfigurationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ProviderConfigurationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ProviderConfigurationDetail} />
      <ErrorBoundaryRoute path={match.url} component={ProviderConfiguration} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ProviderConfigurationDeleteDialog} />
  </>
);

export default Routes;
