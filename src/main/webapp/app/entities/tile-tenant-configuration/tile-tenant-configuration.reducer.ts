import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITileTenantConfiguration, defaultValue } from 'app/shared/model/tile-tenant-configuration.model';

export const ACTION_TYPES = {
  FETCH_TILETENANTCONFIGURATION_LIST: 'tileTenantConfiguration/FETCH_TILETENANTCONFIGURATION_LIST',
  FETCH_TILETENANTCONFIGURATION: 'tileTenantConfiguration/FETCH_TILETENANTCONFIGURATION',
  CREATE_TILETENANTCONFIGURATION: 'tileTenantConfiguration/CREATE_TILETENANTCONFIGURATION',
  UPDATE_TILETENANTCONFIGURATION: 'tileTenantConfiguration/UPDATE_TILETENANTCONFIGURATION',
  DELETE_TILETENANTCONFIGURATION: 'tileTenantConfiguration/DELETE_TILETENANTCONFIGURATION',
  RESET: 'tileTenantConfiguration/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITileTenantConfiguration>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type TileTenantConfigurationState = Readonly<typeof initialState>;

// Reducer

export default (state: TileTenantConfigurationState = initialState, action): TileTenantConfigurationState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TILETENANTCONFIGURATION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TILETENANTCONFIGURATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_TILETENANTCONFIGURATION):
    case REQUEST(ACTION_TYPES.UPDATE_TILETENANTCONFIGURATION):
    case REQUEST(ACTION_TYPES.DELETE_TILETENANTCONFIGURATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_TILETENANTCONFIGURATION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TILETENANTCONFIGURATION):
    case FAILURE(ACTION_TYPES.CREATE_TILETENANTCONFIGURATION):
    case FAILURE(ACTION_TYPES.UPDATE_TILETENANTCONFIGURATION):
    case FAILURE(ACTION_TYPES.DELETE_TILETENANTCONFIGURATION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TILETENANTCONFIGURATION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_TILETENANTCONFIGURATION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_TILETENANTCONFIGURATION):
    case SUCCESS(ACTION_TYPES.UPDATE_TILETENANTCONFIGURATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_TILETENANTCONFIGURATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/tile-tenant-configurations';

// Actions

export const getEntities: ICrudGetAllAction<ITileTenantConfiguration> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_TILETENANTCONFIGURATION_LIST,
    payload: axios.get<ITileTenantConfiguration>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<ITileTenantConfiguration> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TILETENANTCONFIGURATION,
    payload: axios.get<ITileTenantConfiguration>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ITileTenantConfiguration> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TILETENANTCONFIGURATION,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITileTenantConfiguration> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TILETENANTCONFIGURATION,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITileTenantConfiguration> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TILETENANTCONFIGURATION,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
