import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IProviderConfiguration, defaultValue } from 'app/shared/model/provider-configuration.model';

export const ACTION_TYPES = {
  FETCH_PROVIDERCONFIGURATION_LIST: 'providerConfiguration/FETCH_PROVIDERCONFIGURATION_LIST',
  FETCH_PROVIDERCONFIGURATION: 'providerConfiguration/FETCH_PROVIDERCONFIGURATION',
  CREATE_PROVIDERCONFIGURATION: 'providerConfiguration/CREATE_PROVIDERCONFIGURATION',
  UPDATE_PROVIDERCONFIGURATION: 'providerConfiguration/UPDATE_PROVIDERCONFIGURATION',
  DELETE_PROVIDERCONFIGURATION: 'providerConfiguration/DELETE_PROVIDERCONFIGURATION',
  RESET: 'providerConfiguration/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IProviderConfiguration>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type ProviderConfigurationState = Readonly<typeof initialState>;

// Reducer

export default (state: ProviderConfigurationState = initialState, action): ProviderConfigurationState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PROVIDERCONFIGURATION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PROVIDERCONFIGURATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_PROVIDERCONFIGURATION):
    case REQUEST(ACTION_TYPES.UPDATE_PROVIDERCONFIGURATION):
    case REQUEST(ACTION_TYPES.DELETE_PROVIDERCONFIGURATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_PROVIDERCONFIGURATION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PROVIDERCONFIGURATION):
    case FAILURE(ACTION_TYPES.CREATE_PROVIDERCONFIGURATION):
    case FAILURE(ACTION_TYPES.UPDATE_PROVIDERCONFIGURATION):
    case FAILURE(ACTION_TYPES.DELETE_PROVIDERCONFIGURATION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROVIDERCONFIGURATION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROVIDERCONFIGURATION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_PROVIDERCONFIGURATION):
    case SUCCESS(ACTION_TYPES.UPDATE_PROVIDERCONFIGURATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_PROVIDERCONFIGURATION):
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

const apiUrl = 'api/provider-configurations';

// Actions

export const getEntities: ICrudGetAllAction<IProviderConfiguration> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PROVIDERCONFIGURATION_LIST,
    payload: axios.get<IProviderConfiguration>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IProviderConfiguration> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PROVIDERCONFIGURATION,
    payload: axios.get<IProviderConfiguration>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IProviderConfiguration> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PROVIDERCONFIGURATION,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IProviderConfiguration> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PROVIDERCONFIGURATION,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IProviderConfiguration> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PROVIDERCONFIGURATION,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
