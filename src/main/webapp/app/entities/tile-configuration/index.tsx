import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TileConfiguration from './tile-configuration';
import TileConfigurationDetail from './tile-configuration-detail';
import TileConfigurationUpdate from './tile-configuration-update';
import TileConfigurationDeleteDialog from './tile-configuration-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TileConfigurationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TileConfigurationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TileConfigurationDetail} />
      <ErrorBoundaryRoute path={match.url} component={TileConfiguration} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TileConfigurationDeleteDialog} />
  </>
);

export default Routes;
