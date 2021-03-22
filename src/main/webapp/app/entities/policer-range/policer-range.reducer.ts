import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPolicerRange, defaultValue } from 'app/shared/model/policer-range.model';

export const ACTION_TYPES = {
  FETCH_POLICERRANGE_LIST: 'policerRange/FETCH_POLICERRANGE_LIST',
  FETCH_POLICERRANGE: 'policerRange/FETCH_POLICERRANGE',
  CREATE_POLICERRANGE: 'policerRange/CREATE_POLICERRANGE',
  UPDATE_POLICERRANGE: 'policerRange/UPDATE_POLICERRANGE',
  DELETE_POLICERRANGE: 'policerRange/DELETE_POLICERRANGE',
  RESET: 'policerRange/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPolicerRange>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type PolicerRangeState = Readonly<typeof initialState>;

// Reducer

export default (state: PolicerRangeState = initialState, action): PolicerRangeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_POLICERRANGE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_POLICERRANGE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_POLICERRANGE):
    case REQUEST(ACTION_TYPES.UPDATE_POLICERRANGE):
    case REQUEST(ACTION_TYPES.DELETE_POLICERRANGE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_POLICERRANGE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_POLICERRANGE):
    case FAILURE(ACTION_TYPES.CREATE_POLICERRANGE):
    case FAILURE(ACTION_TYPES.UPDATE_POLICERRANGE):
    case FAILURE(ACTION_TYPES.DELETE_POLICERRANGE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_POLICERRANGE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_POLICERRANGE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_POLICERRANGE):
    case SUCCESS(ACTION_TYPES.UPDATE_POLICERRANGE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_POLICERRANGE):
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

const apiUrl = 'api/policer-ranges';

// Actions

export const getEntities: ICrudGetAllAction<IPolicerRange> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_POLICERRANGE_LIST,
    payload: axios.get<IPolicerRange>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IPolicerRange> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_POLICERRANGE,
    payload: axios.get<IPolicerRange>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IPolicerRange> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_POLICERRANGE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPolicerRange> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_POLICERRANGE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPolicerRange> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_POLICERRANGE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
