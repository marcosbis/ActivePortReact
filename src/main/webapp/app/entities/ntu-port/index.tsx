import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import NtuPort from './ntu-port';
import NtuPortDetail from './ntu-port-detail';
import NtuPortUpdate from './ntu-port-update';
import NtuPortDeleteDialog from './ntu-port-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={NtuPortUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={NtuPortUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={NtuPortDetail} />
      <ErrorBoundaryRoute path={match.url} component={NtuPort} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={NtuPortDeleteDialog} />
  </>
);

export default Routes;
