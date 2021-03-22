import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EntityManager from './entity-manager';
import EntityManagerDetail from './entity-manager-detail';
import EntityManagerUpdate from './entity-manager-update';
import EntityManagerDeleteDialog from './entity-manager-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EntityManagerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EntityManagerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EntityManagerDetail} />
      <ErrorBoundaryRoute path={match.url} component={EntityManager} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={EntityManagerDeleteDialog} />
  </>
);

export default Routes;
