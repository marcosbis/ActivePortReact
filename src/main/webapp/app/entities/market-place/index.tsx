import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import MarketPlace from './market-place';
import MarketPlaceDetail from './market-place-detail';
import MarketPlaceUpdate from './market-place-update';
import MarketPlaceDeleteDialog from './market-place-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MarketPlaceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MarketPlaceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MarketPlaceDetail} />
      <ErrorBoundaryRoute path={match.url} component={MarketPlace} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={MarketPlaceDeleteDialog} />
  </>
);

export default Routes;
