import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITileConfiguration, defaultValue } from 'app/shared/model/tile-configuration.model';

export const ACTION_TYPES = {
  FETCH_TILECONFIGURATION_LIST: 'tileConfiguration/FETCH_TILECONFIGURATION_LIST',
  FETCH_TILECONFIGURATION: 'tileConfiguration/FETCH_TILECONFIGURATION',
  CREATE_TILECONFIGURATION: 'tileConfiguration/CREATE_TILECONFIGURATION',
  UPDATE_TILECONFIGURATION: 'tileConfiguration/UPDATE_TILECONFIGURATION',
  DELETE_TILECONFIGURATION: 'tileConfiguration/DELETE_TILECONFIGURATION',
  SET_BLOB: 'tileConfiguration/SET_BLOB',
  RESET: 'tileConfiguration/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITileConfiguration>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type TileConfigurationState = Readonly<typeof initialState>;

// Reducer

export default (state: TileConfigurationState = initialState, action): TileConfigurationState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TILECONFIGURATION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TILECONFIGURATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_TILECONFIGURATION):
    case REQUEST(ACTION_TYPES.UPDATE_TILECONFIGURATION):
    case REQUEST(ACTION_TYPES.DELETE_TILECONFIGURATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_TILECONFIGURATION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TILECONFIGURATION):
    case FAILURE(ACTION_TYPES.CREATE_TILECONFIGURATION):
    case FAILURE(ACTION_TYPES.UPDATE_TILECONFIGURATION):
    case FAILURE(ACTION_TYPES.DELETE_TILECONFIGURATION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TILECONFIGURATION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_TILECONFIGURATION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_TILECONFIGURATION):
    case SUCCESS(ACTION_TYPES.UPDATE_TILECONFIGURATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_TILECONFIGURATION):
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

const apiUrl = 'api/tile-configurations';

// Actions

export const getEntities: ICrudGetAllAction<ITileConfiguration> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_TILECONFIGURATION_LIST,
    payload: axios.get<ITileConfiguration>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<ITileConfiguration> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TILECONFIGURATION,
    payload: axios.get<ITileConfiguration>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ITileConfiguration> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TILECONFIGURATION,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITileConfiguration> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TILECONFIGURATION,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITileConfiguration> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TILECONFIGURATION,
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
