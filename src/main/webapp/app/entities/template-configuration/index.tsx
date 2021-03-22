import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TemplateConfiguration from './template-configuration';
import TemplateConfigurationDetail from './template-configuration-detail';
import TemplateConfigurationUpdate from './template-configuration-update';
import TemplateConfigurationDeleteDialog from './template-configuration-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TemplateConfigurationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TemplateConfigurationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TemplateConfigurationDetail} />
      <ErrorBoundaryRoute path={match.url} component={TemplateConfiguration} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TemplateConfigurationDeleteDialog} />
  </>
);

export default Routes;
