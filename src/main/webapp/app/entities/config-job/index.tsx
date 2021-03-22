import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ConfigJob from './config-job';
import ConfigJobDetail from './config-job-detail';
import ConfigJobUpdate from './config-job-update';
import ConfigJobDeleteDialog from './config-job-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ConfigJobUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ConfigJobUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ConfigJobDetail} />
      <ErrorBoundaryRoute path={match.url} component={ConfigJob} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ConfigJobDeleteDialog} />
  </>
);

export default Routes;
