import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import NotificationEvent from './notification-event';
import NotificationEventDetail from './notification-event-detail';
import NotificationEventUpdate from './notification-event-update';
import NotificationEventDeleteDialog from './notification-event-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={NotificationEventUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={NotificationEventUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={NotificationEventDetail} />
      <ErrorBoundaryRoute path={match.url} component={NotificationEvent} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={NotificationEventDeleteDialog} />
  </>
);

export default Routes;
