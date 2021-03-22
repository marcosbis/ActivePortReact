import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import NtuConfig from './ntu-config';
import NtuConfigDetail from './ntu-config-detail';
import NtuConfigUpdate from './ntu-config-update';
import NtuConfigDeleteDialog from './ntu-config-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={NtuConfigUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={NtuConfigUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={NtuConfigDetail} />
      <ErrorBoundaryRoute path={match.url} component={NtuConfig} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={NtuConfigDeleteDialog} />
  </>
);

export default Routes;
