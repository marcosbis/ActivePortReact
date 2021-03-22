import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PolicerSchedule from './policer-schedule';
import PolicerScheduleDetail from './policer-schedule-detail';
import PolicerScheduleUpdate from './policer-schedule-update';
import PolicerScheduleDeleteDialog from './policer-schedule-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PolicerScheduleUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PolicerScheduleUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PolicerScheduleDetail} />
      <ErrorBoundaryRoute path={match.url} component={PolicerSchedule} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={PolicerScheduleDeleteDialog} />
  </>
);

export default Routes;
