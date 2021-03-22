import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ServiceCode from './service-code';
import ServiceCodeDetail from './service-code-detail';
import ServiceCodeUpdate from './service-code-update';
import ServiceCodeDeleteDialog from './service-code-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ServiceCodeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ServiceCodeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ServiceCodeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ServiceCode} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ServiceCodeDeleteDialog} />
  </>
);

export default Routes;
