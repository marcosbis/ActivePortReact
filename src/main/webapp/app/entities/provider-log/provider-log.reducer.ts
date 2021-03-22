import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IProviderLog, defaultValue } from 'app/shared/model/provider-log.model';

export const ACTION_TYPES = {
  FETCH_PROVIDERLOG_LIST: 'providerLog/FETCH_PROVIDERLOG_LIST',
  FETCH_PROVIDERLOG: 'providerLog/FETCH_PROVIDERLOG',
  CREATE_PROVIDERLOG: 'providerLog/CREATE_PROVIDERLOG',
  UPDATE_PROVIDERLOG: 'providerLog/UPDATE_PROVIDERLOG',
  DELETE_PROVIDERLOG: 'providerLog/DELETE_PROVIDERLOG',
  SET_BLOB: 'providerLog/SET_BLOB',
  RESET: 'providerLog/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IProviderLog>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type ProviderLogState = Readonly<typeof initialState>;

// Reducer

export default (state: ProviderLogState = initialState, action): ProviderLogState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PROVIDERLOG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PROVIDERLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_PROVIDERLOG):
    case REQUEST(ACTION_TYPES.UPDATE_PROVIDERLOG):
    case REQUEST(ACTION_TYPES.DELETE_PROVIDERLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_PROVIDERLOG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PROVIDERLOG):
    case FAILURE(ACTION_TYPES.CREATE_PROVIDERLOG):
    case FAILURE(ACTION_TYPES.UPDATE_PROVIDERLOG):
    case FAILURE(ACTION_TYPES.DELETE_PROVIDERLOG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROVIDERLOG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROVIDERLOG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_PROVIDERLOG):
    case SUCCESS(ACTION_TYPES.UPDATE_PROVIDERLOG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_PROVIDERLOG):
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

const apiUrl = 'api/provider-logs';

// Actions

export const getEntities: ICrudGetAllAction<IProviderLog> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PROVIDERLOG_LIST,
    payload: axios.get<IProviderLog>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IProviderLog> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PROVIDERLOG,
    payload: axios.get<IProviderLog>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IProviderLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PROVIDERLOG,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IProviderLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PROVIDERLOG,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IProviderLog> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PROVIDERLOG,
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
