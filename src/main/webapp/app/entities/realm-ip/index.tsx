import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import RealmIp from './realm-ip';
import RealmIpDetail from './realm-ip-detail';
import RealmIpUpdate from './realm-ip-update';
import RealmIpDeleteDialog from './realm-ip-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={RealmIpUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={RealmIpUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={RealmIpDetail} />
      <ErrorBoundaryRoute path={match.url} component={RealmIp} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={RealmIpDeleteDialog} />
  </>
);

export default Routes;
