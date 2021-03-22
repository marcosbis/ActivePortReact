import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IServiceConfiguration, defaultValue } from 'app/shared/model/service-configuration.model';

export const ACTION_TYPES = {
  FETCH_SERVICECONFIGURATION_LIST: 'serviceConfiguration/FETCH_SERVICECONFIGURATION_LIST',
  FETCH_SERVICECONFIGURATION: 'serviceConfiguration/FETCH_SERVICECONFIGURATION',
  CREATE_SERVICECONFIGURATION: 'serviceConfiguration/CREATE_SERVICECONFIGURATION',
  UPDATE_SERVICECONFIGURATION: 'serviceConfiguration/UPDATE_SERVICECONFIGURATION',
  DELETE_SERVICECONFIGURATION: 'serviceConfiguration/DELETE_SERVICECONFIGURATION',
  SET_BLOB: 'serviceConfiguration/SET_BLOB',
  RESET: 'serviceConfiguration/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IServiceConfiguration>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type ServiceConfigurationState = Readonly<typeof initialState>;

// Reducer

export default (state: ServiceConfigurationState = initialState, action): ServiceConfigurationState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SERVICECONFIGURATION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SERVICECONFIGURATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_SERVICECONFIGURATION):
    case REQUEST(ACTION_TYPES.UPDATE_SERVICECONFIGURATION):
    case REQUEST(ACTION_TYPES.DELETE_SERVICECONFIGURATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_SERVICECONFIGURATION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SERVICECONFIGURATION):
    case FAILURE(ACTION_TYPES.CREATE_SERVICECONFIGURATION):
    case FAILURE(ACTION_TYPES.UPDATE_SERVICECONFIGURATION):
    case FAILURE(ACTION_TYPES.DELETE_SERVICECONFIGURATION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SERVICECONFIGURATION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_SERVICECONFIGURATION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_SERVICECONFIGURATION):
    case SUCCESS(ACTION_TYPES.UPDATE_SERVICECONFIGURATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_SERVICECONFIGURATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.SET_BLOB: {
      const { name, data, contentType } = action.payload;
      return {
        ...state,
        entity: {
          ...state.entity,
          [name]: data,
          [name + 'ContentType']: contentType,
        },
      };
    }
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/service-configurations';

// Actions

export const getEntities: ICrudGetAllAction<IServiceConfiguration> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SERVICECONFIGURATION_LIST,
    payload: axios.get<IServiceConfiguration>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IServiceConfiguration> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SERVICECONFIGURATION,
    payload: axios.get<IServiceConfiguration>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IServiceConfiguration> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SERVICECONFIGURATION,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IServiceConfiguration> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SERVICECONFIGURATION,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IServiceConfiguration> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SERVICECONFIGURATION,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const setBlob = (name, data, contentType?) => ({
  type: ACTION_TYPES.SET_BLOB,
  payload: {
    name,
    data,
    contentType,
  },
});

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
