import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import UserService from './user-service';
import UserServiceDetail from './user-service-detail';
import UserServiceUpdate from './user-service-update';
import UserServiceDeleteDialog from './user-service-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={UserServiceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={UserServiceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={UserServiceDetail} />
      <ErrorBoundaryRoute path={match.url} component={UserService} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={UserServiceDeleteDialog} />
  </>
);

export default Routes;
