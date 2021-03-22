import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import VntuDownlinkPort from './vntu-downlink-port';
import VntuDownlinkPortDetail from './vntu-downlink-port-detail';
import VntuDownlinkPortUpdate from './vntu-downlink-port-update';
import VntuDownlinkPortDeleteDialog from './vntu-downlink-port-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={VntuDownlinkPortUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={VntuDownlinkPortUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={VntuDownlinkPortDetail} />
      <ErrorBoundaryRoute path={match.url} component={VntuDownlinkPort} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={VntuDownlinkPortDeleteDialog} />
  </>
);

export default Routes;
