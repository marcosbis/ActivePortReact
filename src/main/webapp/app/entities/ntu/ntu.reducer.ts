import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { INtu, defaultValue } from 'app/shared/model/ntu.model';

export const ACTION_TYPES = {
  FETCH_NTU_LIST: 'ntu/FETCH_NTU_LIST',
  FETCH_NTU: 'ntu/FETCH_NTU',
  CREATE_NTU: 'ntu/CREATE_NTU',
  UPDATE_NTU: 'ntu/UPDATE_NTU',
  DELETE_NTU: 'ntu/DELETE_NTU',
  RESET: 'ntu/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<INtu>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type NtuState = Readonly<typeof initialState>;

// Reducer

export default (state: NtuState = initialState, action): NtuState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_NTU_LIST):
    case REQUEST(ACTION_TYPES.FETCH_NTU):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_NTU):
    case REQUEST(ACTION_TYPES.UPDATE_NTU):
    case REQUEST(ACTION_TYPES.DELETE_NTU):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_NTU_LIST):
    case FAILURE(ACTION_TYPES.FETCH_NTU):
    case FAILURE(ACTION_TYPES.CREATE_NTU):
    case FAILURE(ACTION_TYPES.UPDATE_NTU):
    case FAILURE(ACTION_TYPES.DELETE_NTU):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_NTU_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_NTU):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_NTU):
    case SUCCESS(ACTION_TYPES.UPDATE_NTU):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_NTU):
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

const apiUrl = 'api/ntus';

// Actions

export const getEntities: ICrudGetAllAction<INtu> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_NTU_LIST,
    payload: axios.get<INtu>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<INtu> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_NTU,
    payload: axios.get<INtu>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<INtu> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_NTU,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<INtu> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_NTU,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<INtu> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_NTU,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
