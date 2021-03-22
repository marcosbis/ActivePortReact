import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ItemCode from './item-code';
import ItemCodeDetail from './item-code-detail';
import ItemCodeUpdate from './item-code-update';
import ItemCodeDeleteDialog from './item-code-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ItemCodeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ItemCodeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ItemCodeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ItemCode} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ItemCodeDeleteDialog} />
  </>
);

export default Routes;
