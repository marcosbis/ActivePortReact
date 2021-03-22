import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ThirdPartyApi from './third-party-api';
import ThirdPartyApiDetail from './third-party-api-detail';
import ThirdPartyApiUpdate from './third-party-api-update';
import ThirdPartyApiDeleteDialog from './third-party-api-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ThirdPartyApiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ThirdPartyApiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ThirdPartyApiDetail} />
      <ErrorBoundaryRoute path={match.url} component={ThirdPartyApi} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ThirdPartyApiDeleteDialog} />
  </>
);

export default Routes;
