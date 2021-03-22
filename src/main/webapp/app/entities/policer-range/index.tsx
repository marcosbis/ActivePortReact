import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PolicerRange from './policer-range';
import PolicerRangeDetail from './policer-range-detail';
import PolicerRangeUpdate from './policer-range-update';
import PolicerRangeDeleteDialog from './policer-range-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PolicerRangeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PolicerRangeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PolicerRangeDetail} />
      <ErrorBoundaryRoute path={match.url} component={PolicerRange} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={PolicerRangeDeleteDialog} />
  </>
);

export default Routes;
