import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IJunoCommandLog, defaultValue } from 'app/shared/model/juno-command-log.model';

export const ACTION_TYPES = {
  FETCH_JUNOCOMMANDLOG_LIST: 'junoCommandLog/FETCH_JUNOCOMMANDLOG_LIST',
  FETCH_JUNOCOMMANDLOG: 'junoCommandLog/FETCH_JUNOCOMMANDLOG',
  CREATE_JUNOCOMMANDLOG: 'junoCommandLog/CREATE_JUNOCOMMANDLOG',
  UPDATE_JUNOCOMMANDLOG: 'junoCommandLog/UPDATE_JUNOCOMMANDLOG',
  DELETE_JUNOCOMMANDLOG: 'junoCommandLog/DELETE_JUNOCOMMANDLOG',
  RESET: 'junoCommandLog/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IJunoCommandLog>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type JunoCommandLogState = Readonly<typeof initialState>;

// Reducer

export default (state: JunoCommandLogState = initialState, action): JunoCommandLogState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_JUNOCOMMANDLOG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_JUNOCOMMANDLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_JUNOCOMMANDLOG):
    case REQUEST(ACTION_TYPES.UPDATE_JUNOCOMMANDLOG):
    case REQUEST(ACTION_TYPES.DELETE_JUNOCOMMANDLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_JUNOCOMMANDLOG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_JUNOCOMMANDLOG):
    case FAILURE(ACTION_TYPES.CREATE_JUNOCOMMANDLOG):
    case FAILURE(ACTION_TYPES.UPDATE_JUNOCOMMANDLOG):
    case FAILURE(ACTION_TYPES.DELETE_JUNOCOMMANDLOG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_JUNOCOMMANDLOG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_JUNOCOMMANDLOG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_JUNOCOMMANDLOG):
    case SUCCESS(ACTION_TYPES.UPDATE_JUNOCOMMANDLOG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_JUNOCOMMANDLOG):
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

const apiUrl = 'api/juno-command-logs';

// Actions

export const getEntities: ICrudGetAllAction<IJunoCommandLog> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_JUNOCOMMANDLOG_LIST,
    payload: axios.get<IJunoCommandLog>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IJunoCommandLog> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_JUNOCOMMANDLOG,
    payload: axios.get<IJunoCommandLog>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IJunoCommandLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_JUNOCOMMANDLOG,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IJunoCommandLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_JUNOCOMMANDLOG,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IJunoCommandLog> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_JUNOCOMMANDLOG,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
