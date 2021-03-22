import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { INtuPort, defaultValue } from 'app/shared/model/ntu-port.model';

export const ACTION_TYPES = {
  FETCH_NTUPORT_LIST: 'ntuPort/FETCH_NTUPORT_LIST',
  FETCH_NTUPORT: 'ntuPort/FETCH_NTUPORT',
  CREATE_NTUPORT: 'ntuPort/CREATE_NTUPORT',
  UPDATE_NTUPORT: 'ntuPort/UPDATE_NTUPORT',
  DELETE_NTUPORT: 'ntuPort/DELETE_NTUPORT',
  RESET: 'ntuPort/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<INtuPort>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type NtuPortState = Readonly<typeof initialState>;

// Reducer

export default (state: NtuPortState = initialState, action): NtuPortState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_NTUPORT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_NTUPORT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_NTUPORT):
    case REQUEST(ACTION_TYPES.UPDATE_NTUPORT):
    case REQUEST(ACTION_TYPES.DELETE_NTUPORT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_NTUPORT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_NTUPORT):
    case FAILURE(ACTION_TYPES.CREATE_NTUPORT):
    case FAILURE(ACTION_TYPES.UPDATE_NTUPORT):
    case FAILURE(ACTION_TYPES.DELETE_NTUPORT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_NTUPORT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_NTUPORT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_NTUPORT):
    case SUCCESS(ACTION_TYPES.UPDATE_NTUPORT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_NTUPORT):
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

const apiUrl = 'api/ntu-ports';

// Actions

export const getEntities: ICrudGetAllAction<INtuPort> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_NTUPORT_LIST,
    payload: axios.get<INtuPort>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<INtuPort> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_NTUPORT,
    payload: axios.get<INtuPort>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<INtuPort> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_NTUPORT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<INtuPort> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_NTUPORT,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<INtuPort> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_NTUPORT,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
