import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IRateChangeLog, defaultValue } from 'app/shared/model/rate-change-log.model';

export const ACTION_TYPES = {
  FETCH_RATECHANGELOG_LIST: 'rateChangeLog/FETCH_RATECHANGELOG_LIST',
  FETCH_RATECHANGELOG: 'rateChangeLog/FETCH_RATECHANGELOG',
  CREATE_RATECHANGELOG: 'rateChangeLog/CREATE_RATECHANGELOG',
  UPDATE_RATECHANGELOG: 'rateChangeLog/UPDATE_RATECHANGELOG',
  DELETE_RATECHANGELOG: 'rateChangeLog/DELETE_RATECHANGELOG',
  RESET: 'rateChangeLog/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IRateChangeLog>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type RateChangeLogState = Readonly<typeof initialState>;

// Reducer

export default (state: RateChangeLogState = initialState, action): RateChangeLogState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_RATECHANGELOG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_RATECHANGELOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_RATECHANGELOG):
    case REQUEST(ACTION_TYPES.UPDATE_RATECHANGELOG):
    case REQUEST(ACTION_TYPES.DELETE_RATECHANGELOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_RATECHANGELOG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_RATECHANGELOG):
    case FAILURE(ACTION_TYPES.CREATE_RATECHANGELOG):
    case FAILURE(ACTION_TYPES.UPDATE_RATECHANGELOG):
    case FAILURE(ACTION_TYPES.DELETE_RATECHANGELOG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_RATECHANGELOG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_RATECHANGELOG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_RATECHANGELOG):
    case SUCCESS(ACTION_TYPES.UPDATE_RATECHANGELOG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_RATECHANGELOG):
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

const apiUrl = 'api/rate-change-logs';

// Actions

export const getEntities: ICrudGetAllAction<IRateChangeLog> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_RATECHANGELOG_LIST,
    payload: axios.get<IRateChangeLog>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IRateChangeLog> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_RATECHANGELOG,
    payload: axios.get<IRateChangeLog>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IRateChangeLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_RATECHANGELOG,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IRateChangeLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_RATECHANGELOG,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IRateChangeLog> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_RATECHANGELOG,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
