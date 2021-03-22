import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CentralSwitch from './central-switch';
import CentralSwitchDetail from './central-switch-detail';
import CentralSwitchUpdate from './central-switch-update';
import CentralSwitchDeleteDialog from './central-switch-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CentralSwitchUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CentralSwitchUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CentralSwitchDetail} />
      <ErrorBoundaryRoute path={match.url} component={CentralSwitch} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CentralSwitchDeleteDialog} />
  </>
);

export default Routes;
