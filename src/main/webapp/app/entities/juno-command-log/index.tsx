import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import JunoCommandLog from './juno-command-log';
import JunoCommandLogDetail from './juno-command-log-detail';
import JunoCommandLogUpdate from './juno-command-log-update';
import JunoCommandLogDeleteDialog from './juno-command-log-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={JunoCommandLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={JunoCommandLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={JunoCommandLogDetail} />
      <ErrorBoundaryRoute path={match.url} component={JunoCommandLog} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={JunoCommandLogDeleteDialog} />
  </>
);

export default Routes;
