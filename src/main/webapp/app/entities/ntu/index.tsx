import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Ntu from './ntu';
import NtuDetail from './ntu-detail';
import NtuUpdate from './ntu-update';
import NtuDeleteDialog from './ntu-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={NtuUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={NtuUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={NtuDetail} />
      <ErrorBoundaryRoute path={match.url} component={Ntu} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={NtuDeleteDialog} />
  </>
);

export default Routes;
