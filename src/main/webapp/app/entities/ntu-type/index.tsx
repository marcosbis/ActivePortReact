import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import NtuType from './ntu-type';
import NtuTypeDetail from './ntu-type-detail';
import NtuTypeUpdate from './ntu-type-update';
import NtuTypeDeleteDialog from './ntu-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={NtuTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={NtuTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={NtuTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={NtuType} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={NtuTypeDeleteDialog} />
  </>
);

export default Routes;
