import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { INtuConfig, defaultValue } from 'app/shared/model/ntu-config.model';

export const ACTION_TYPES = {
  FETCH_NTUCONFIG_LIST: 'ntuConfig/FETCH_NTUCONFIG_LIST',
  FETCH_NTUCONFIG: 'ntuConfig/FETCH_NTUCONFIG',
  CREATE_NTUCONFIG: 'ntuConfig/CREATE_NTUCONFIG',
  UPDATE_NTUCONFIG: 'ntuConfig/UPDATE_NTUCONFIG',
  DELETE_NTUCONFIG: 'ntuConfig/DELETE_NTUCONFIG',
  RESET: 'ntuConfig/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<INtuConfig>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type NtuConfigState = Readonly<typeof initialState>;

// Reducer

export default (state: NtuConfigState = initialState, action): NtuConfigState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_NTUCONFIG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_NTUCONFIG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_NTUCONFIG):
    case REQUEST(ACTION_TYPES.UPDATE_NTUCONFIG):
    case REQUEST(ACTION_TYPES.DELETE_NTUCONFIG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_NTUCONFIG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_NTUCONFIG):
    case FAILURE(ACTION_TYPES.CREATE_NTUCONFIG):
    case FAILURE(ACTION_TYPES.UPDATE_NTUCONFIG):
    case FAILURE(ACTION_TYPES.DELETE_NTUCONFIG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_NTUCONFIG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_NTUCONFIG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_NTUCONFIG):
    case SUCCESS(ACTION_TYPES.UPDATE_NTUCONFIG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_NTUCONFIG):
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

const apiUrl = 'api/ntu-configs';

// Actions

export const getEntities: ICrudGetAllAction<INtuConfig> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_NTUCONFIG_LIST,
    payload: axios.get<INtuConfig>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<INtuConfig> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_NTUCONFIG,
    payload: axios.get<INtuConfig>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<INtuConfig> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_NTUCONFIG,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<INtuConfig> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_NTUCONFIG,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<INtuConfig> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_NTUCONFIG,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
