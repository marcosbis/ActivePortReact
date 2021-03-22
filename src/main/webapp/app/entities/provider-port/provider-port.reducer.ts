import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IProviderPort, defaultValue } from 'app/shared/model/provider-port.model';

export const ACTION_TYPES = {
  FETCH_PROVIDERPORT_LIST: 'providerPort/FETCH_PROVIDERPORT_LIST',
  FETCH_PROVIDERPORT: 'providerPort/FETCH_PROVIDERPORT',
  CREATE_PROVIDERPORT: 'providerPort/CREATE_PROVIDERPORT',
  UPDATE_PROVIDERPORT: 'providerPort/UPDATE_PROVIDERPORT',
  DELETE_PROVIDERPORT: 'providerPort/DELETE_PROVIDERPORT',
  RESET: 'providerPort/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IProviderPort>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type ProviderPortState = Readonly<typeof initialState>;

// Reducer

export default (state: ProviderPortState = initialState, action): ProviderPortState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PROVIDERPORT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PROVIDERPORT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_PROVIDERPORT):
    case REQUEST(ACTION_TYPES.UPDATE_PROVIDERPORT):
    case REQUEST(ACTION_TYPES.DELETE_PROVIDERPORT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_PROVIDERPORT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PROVIDERPORT):
    case FAILURE(ACTION_TYPES.CREATE_PROVIDERPORT):
    case FAILURE(ACTION_TYPES.UPDATE_PROVIDERPORT):
    case FAILURE(ACTION_TYPES.DELETE_PROVIDERPORT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROVIDERPORT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROVIDERPORT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_PROVIDERPORT):
    case SUCCESS(ACTION_TYPES.UPDATE_PROVIDERPORT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_PROVIDERPORT):
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

const apiUrl = 'api/provider-ports';

// Actions

export const getEntities: ICrudGetAllAction<IProviderPort> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PROVIDERPORT_LIST,
    payload: axios.get<IProviderPort>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IProviderPort> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PROVIDERPORT,
    payload: axios.get<IProviderPort>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IProviderPort> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PROVIDERPORT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IProviderPort> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PROVIDERPORT,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IProviderPort> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PROVIDERPORT,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
