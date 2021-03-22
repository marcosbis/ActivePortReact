import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import RateChangeLog from './rate-change-log';
import RateChangeLogDetail from './rate-change-log-detail';
import RateChangeLogUpdate from './rate-change-log-update';
import RateChangeLogDeleteDialog from './rate-change-log-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={RateChangeLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={RateChangeLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={RateChangeLogDetail} />
      <ErrorBoundaryRoute path={match.url} component={RateChangeLog} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={RateChangeLogDeleteDialog} />
  </>
);

export default Routes;
